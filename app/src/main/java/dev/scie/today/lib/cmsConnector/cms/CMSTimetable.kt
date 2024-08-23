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

import dev.scie.today.lib.cmsConnector.CMSType
import dev.scie.today.lib.cmsConnector.Timetable
import dev.scie.today.lib.cmsConnector.util.TimeRange
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

		override fun toTodayType(): Timetable.Event {
			return Timetable.Event(
				id = this.id ?: throw EventConversionException.NullField("id"),
				type = this.type?.toTodayType() ?: throw EventConversionException.NullField("type"),
				name = this.name ?: throw EventConversionException.NullField("name"),
				room = this.room ?: throw EventConversionException.NullField("room"),
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
				val times = TimeRange.lessonTimes[index]
				val timeSlot: Timetable.TimeSlot
				when (period.events.size) {
					0 -> timeSlot = Timetable.TimeSlot.Empty(times.start, times.end)
					1 -> timeSlot =
						Timetable.TimeSlot.Same(period.events[0].toTodayType(), times.start, times.end)
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
							startTime = times.start,
							endTime = times.end
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
				mondayEvents = events[0],
				tuesdayEvents = events[1],
				wednesdayEvents = events[2],
				thursdayEvents = events[3],
				fridayEvents = events[3]
			)
		)
	}
}