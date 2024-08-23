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

package dev.scie.today.lib.cmsConnector

import dev.scie.today.lib.cmsConnector.util.Time
import kotlinx.serialization.Serializable


/**
 * Holds information about a students timetable.
 * @see Timetable.Week
 */
data class Timetable(val week: Week) {
	/** Represents a school-week, with a list of Events on Monday, Tuesday, etc, and the week type, A or B. */
	data class Week(
		val mondayEvents: List<TimeSlot>,
		val tuesdayEvents: List<TimeSlot>,
		val wednesdayEvents: List<TimeSlot>,
		val thursdayEvents: List<TimeSlot>,
		val fridayEvents: List<TimeSlot>,
		val type: Type
	) {
		/** Type of school-week, A or B. */
		enum class Type {
			WeekA, WeekB
		}
	}

	/**
	 * Represents a time slot on the timetable. Required to determine whether the lesson in this time slot is different
	 * between week A & B.
	 * @see TimeSlot.Different
	 * @see TimeSlot.Same
	 */
	sealed class TimeSlot(
		val startTime: Time, val endTime: Time
	) {
		/** Used when this timeslot is empty */
		class Empty(startTime: Time, endTime: Time) : TimeSlot(startTime, endTime)

		/**
		 * Used when the lesson in this time slot is not the same in both week A & B.
		 */
		class Different(
			val weekAEvent: Event, val weekBEvent: Event, startTime: Time, endTime: Time
		) : TimeSlot(startTime, endTime)

		/**
		 * Used when the lesson in this time slot is the same in both week A & B.
		 */
		class Same(val event: Event, startTime: Time, endTime: Time) : TimeSlot(startTime, endTime)
	}

	/** A timetable event, as far as I know, these can only either be lessons or ECAs. */
	data class Event(
		val id: UInt,
		val type: Type,
		val name: String,
		val room: String,
	) {
		/** The types of event, lesson and ECA. */
		enum class Type {
			Lesson, ECA
		}
	}
}

data class UserInformation(val id: UInt, val name: String, val house: House, val formGroup: String)

data class Attendance(val attendance: List<AttendanceRecord>) {
	data class AttendanceRecord(val courseId: UInt, val courseName: String, val kind: Kind, val reason: String, val date: String) {
		enum class Kind {
			Late,
			Unapproved,
			Sick,
			Approved,
			SchoolReason,
			EarlyLeave
		}

	}
}

/**
 * Most types from the CMS API should implement this interface, to provide a consistent conversion method. This is
 * "required" because the CMS API doesn't always have very good type definitions. However, if the original type is
 * sane, like [UserCredentials], then it is not necessary.
 * @see CMSType.toTodayType
 *
 */
interface CMSType<T> {
	/**
	 * Converts this CMS type into its equivalent SCIEToday type.
	 */
	fun toTodayType(): T
}

@Serializable
data class UserCredentials(val username: String, val password: String)
