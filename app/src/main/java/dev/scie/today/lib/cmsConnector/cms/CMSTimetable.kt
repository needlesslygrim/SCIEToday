/*
 * Copyright (c) 2024 Erick Howard
 *
 * This file is part of SCIEToday.
 *
 * SCIEToday is free software: you can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * SCIEToday is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with SCIEToday. If not,
 * see <https://www.gnu.org/licenses/>.
 */

package dev.scie.today.lib.cmsConnector.cms

import dev.scie.today.lib.cmsConnector.cms.CMSType
import dev.scie.today.lib.cmsConnector.Timetable
import dev.scie.today.lib.cmsConnector.util.TimeRange
import dev.scie.today.ui.screens.Subject
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/** The CMS timetable type, which stores timetable information. */
@Serializable
internal data class CMSTimetable(
	@SerialName("week_type") val weekType: WeekType,
	@SerialName("week_a_periods") val weekAPeriods: UInt,
	@SerialName("week_b_periods") val weekBPeriods: UInt,
	@SerialName("duty_periods") val dutyPeriods: UInt,
	@SerialName("contract_periods") val contractPeriods: UInt,
	val weekdays: List<WeekDay>
) : CMSType<Timetable> {
	@Serializable
	enum class WeekType : CMSType<Timetable.Week.Type> {
		A, B;

		override fun toTodayType(): Timetable.Week.Type = when (this) {
			A -> Timetable.Week.Type.WeekA
			B -> Timetable.Week.Type.WeekB
		}
	}

	@Serializable
	data class Event(
		@SerialName("type") val type: EventType?,
		val id: UInt?,
		val name: String?,
		val room: String?,
		val teacher: String?,
		@SerialName("week_type") val weekType: String?
	) : CMSType<Timetable.Event> {
		sealed class EventConversionException private constructor(message: String, cause: Throwable? = null) : Exception(message, cause) {
			class NullField(fieldName: String) : EventConversionException("Field `$fieldName` is null")
		}

		// FIXME: More exception types.
		override fun toTodayType(): Timetable.Event {
			var subject: String = ""
			var name: String = ""
			this.name?.split('-', limit = 2)?.apply {
				subject = this[0]
				name = this[1]
			} ?: throw Exception("Invalid class name")

			return Timetable.Event(
				id = this.id ?: throw EventConversionException.NullField("id"),
				type = this.type?.toTodayType() ?: throw EventConversionException.NullField("type"),
				name = name,
				subject = if (this.type.toTodayType() == Timetable.Event.Type.Lesson) {
					when (subject) {
						"Computer Science" -> Subject.ComputerScience
						"Mathematics" -> Subject.Mathematics
						"Physics" -> Subject.Physics
						"Chemistry" -> Subject.Chemistry
						"Biology" -> Subject.Biology

						"English" -> Subject.English
						"Chinese" -> Subject.Mandarin
						"Spanish" -> Subject.Spanish
						"Japanese" -> Subject.Japanese
						"French" -> Subject.French

						"History" -> Subject.History
						"Geography" -> Subject.Geography
						"Psychology" -> Subject.Psychology

						"PE" -> Subject.PE

						"EPQ" -> Subject.EPQ

						"PSHE" -> Subject.PSHE
						"Tutorial" -> Subject.Tutorial
						"UCO" -> Subject.UCO
						else -> throw Exception("Invalid subject")
					}
				} else {
					Subject.ECA
				},
				room = this.room ?: throw EventConversionException.NullField("room"),
				teacher = this.teacher ?: throw EventConversionException.NullField("teacher")
			)
		}
	}

	@Serializable
	data class WeekDay(val periods: List<Period>)

	@Serializable
	data class Period(val events: List<Event>)

	@Serializable(with = EventType.Serialiser::class)
	enum class EventType(val discriminant: Byte) : CMSType<Timetable.Event.Type> {
		Lesson(1), ECA(2);

		override fun toTodayType(): Timetable.Event.Type {
			return when (this) {
				Lesson -> Timetable.Event.Type.Lesson
				ECA -> Timetable.Event.Type.ECA
			}
		}

		object Serialiser : KSerializer<EventType> {
			override val descriptor: SerialDescriptor
				get() = PrimitiveSerialDescriptor("EventType", PrimitiveKind.BYTE)

			override fun serialize(encoder: Encoder, value: EventType) {
				encoder.encodeByte(value.discriminant)
			}

			override fun deserialize(decoder: Decoder): EventType {
				val eventType = decoder.decodeByte()
				EventType.entries.find { it.discriminant == eventType }?.let { return it }
					?: throw IllegalArgumentException("invalid event type")
			}
		}
	}

	sealed class TimetableConversionException private constructor(message: String, cause: Throwable? = null) : Exception(message, cause) {
		class InvalidWeekTypeConfiguration(val firstPeriodType: String?, val secondPeriodType: String?) : TimetableConversionException("Invalid configuration of week types in a single period")
		class InvalidEventCount(count: Int) : TimetableConversionException("Invalid event count for one period: $count")
	}

	override fun toTodayType(): Timetable {
		val events = this.weekdays.map {
			it.periods.mapIndexed { index, period ->
				val time = TimeRange.lessonTimes[index]
				val timeSlot: Timetable.TimeSlot
				when (period.events.size) {
					0 -> timeSlot = Timetable.TimeSlot.Empty(time)
					1 -> {
						timeSlot = when (period.events[0].weekType) {
							"A" -> {
								Timetable.TimeSlot.OneWeekOnly(
									Timetable.Week.Type.WeekA,
									period.events[0].toTodayType(),
									time
								)
							}
							"B" -> {
								Timetable.TimeSlot.OneWeekOnly(
									Timetable.Week.Type.WeekB,
									period.events[0].toTodayType(),
									time
								)
							}
							else -> {
								Timetable.TimeSlot.Same(period.events[0].toTodayType(), time)
							}
						}

					}
					2 -> {
						val weekAEvent: Timetable.Event
						val weekBEvent: Timetable.Event

						val firstPeriod = period.events[0]
						val secondPeriod = period.events[1]
						when {
							firstPeriod.weekType == "A" && secondPeriod.weekType == "B" -> {
								weekAEvent = firstPeriod.toTodayType()
								weekBEvent = secondPeriod.toTodayType()
							}

							secondPeriod.weekType == "A" && firstPeriod.weekType == "B" -> {
								weekAEvent = secondPeriod.toTodayType()
								weekBEvent = firstPeriod.toTodayType()
							}

							else -> throw TimetableConversionException.InvalidWeekTypeConfiguration(
								firstPeriod.weekType,
								secondPeriod.weekType
							)
						}
						timeSlot = Timetable.TimeSlot.Different(
							weekAEvent = weekAEvent,
							weekBEvent = weekBEvent,
							time = time
						)
					}
					else -> throw TimetableConversionException.InvalidEventCount(period.events.size)
				}

				timeSlot
			}
		}

		return Timetable(
			Timetable.Week(
				type = weekType.toTodayType(),
				mondayTimeslots = events[0],
				tuesdayTimeslots = events[1],
				wednesdayTimeslots = events[2],
				thursdayTimeslots = events[3],
				fridayTimeslots = events[3]
			)
		)
	}
}