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

import dev.scie.today.lib.cmsConnector.Attendance
import dev.scie.today.lib.cmsConnector.CMSType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/** The CMS attendance type, storing information about a student's attendance record. */
@Serializable
internal data class CMSAttendance(
	@SerialName("student_name") val studentName: String,
	@SerialName("student_id") val studentId: UInt,
	@SerialName("total_absent_count") val totalAbsentCount: Double,
	@SerialName("record_of_days") val recordOfDays: List<Day>
) : CMSType<Attendance> {
	@Serializable
	data class Day(
		// TODO: Consider using some sort of date class, as CMS provides this in YYYY-MM-DD.
		val date: String,
		@SerialName("attendances") val attendance: List<AttendanceRecord>,
		@SerialName("absent_count") val absentCount: Double,
	)

	/** An individual attendance record. */
	@Serializable
	data class AttendanceRecord(
		@SerialName("course_id") val courseId: UInt,
		@SerialName("course_name") val courseName: String,
		val kind: AttendanceKind,
		val reason: String
	) {
		/** The type of attendance record, present, late, etc. */
		/*
		 * The discriminants here are reverse-engineered from a switch statement in the original code which sets the
		 * colours of the key at the top-right of the attendance page on CMS. This the code for reference:
		 * style: {
		 *     background: function(e, t) {
		 *         switch (t) {
		 *         case 0:
		 *             return e ? "DarkGreen" : "White";
		 *         case 1:
		 *             return "Black";
		 *         case 2:
		 *             return "SaddleBrown;
		 *         case 4:
		 *             return "Gold";
		 *         case 5:
		 *             return "DarkBlue";
		 *         case 6:
		 *             return "Pink";
		 *         case 7:
		 *             return "Grey";
		 *         default:
		 *             return "White";
		 *         }
		 *     }(t[e].course_name, t[e].kind)
		 * }
		*/
		@Serializable(with = AttendanceKind.Serialiser::class)
		enum class AttendanceKind(val discriminant: Byte) {
			Present(0),
			Late(1),
			Unapproved(2),
			Sick(4),
			Approved(5),
			SchoolReason(6),
			EarlyLeave(7),
			// There seems to be some kind of phantom kind, which sees to correspond with my free periods.
			Unknown(8);

			object Serialiser : KSerializer<AttendanceKind> {
				override val descriptor: SerialDescriptor
					get() = PrimitiveSerialDescriptor("AttendanceKind", PrimitiveKind.BYTE)

				override fun serialize(encoder: Encoder, value: AttendanceKind) {
					encoder.encodeByte(value.discriminant)
				}

				override fun deserialize(decoder: Decoder): AttendanceKind {
					val attendanceKind = decoder.decodeByte()
					entries.find { it.discriminant == attendanceKind }?.let { return it }
						?: throw IllegalArgumentException("invalid attendance kind $attendanceKind")
				}
			}
		}
	}

	override fun toTodayType(): Attendance {
		return Attendance(
			recordOfDays.flatMap { day ->
				day.attendance.filterNot {
					it.kind == AttendanceRecord.AttendanceKind.Unknown || it.kind == AttendanceRecord.AttendanceKind.Present
				}.map {
					Attendance.AttendanceRecord(
						courseId = it.courseId, courseName = it.courseName, kind = when (it.kind) {
							AttendanceRecord.AttendanceKind.Late -> Attendance.AttendanceRecord.Kind.Late
							AttendanceRecord.AttendanceKind.Unapproved -> Attendance.AttendanceRecord.Kind.Unapproved
							AttendanceRecord.AttendanceKind.Sick -> Attendance.AttendanceRecord.Kind.Sick
							AttendanceRecord.AttendanceKind.Approved -> Attendance.AttendanceRecord.Kind.Approved
							AttendanceRecord.AttendanceKind.SchoolReason -> Attendance.AttendanceRecord.Kind.SchoolReason
							AttendanceRecord.AttendanceKind.EarlyLeave -> Attendance.AttendanceRecord.Kind.EarlyLeave
							else -> throw Exception("the `filterNot` didn't work")
						},
						reason = it.reason,
						date = day.date
					)
				}
			}
		)
	}
}
