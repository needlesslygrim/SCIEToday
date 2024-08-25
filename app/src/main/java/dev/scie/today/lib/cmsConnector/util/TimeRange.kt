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

package dev.scie.today.lib.cmsConnector.util

// TOOD: Consider making this an inline value class like Time is, using a ULong to directly store the two times.
/**
 * A range from one time of day to another, e.g. 09:30 to 10:10.
 * @see Time
 */
data class TimeRange(val start: Time, val end: Time) {
	override fun toString(): String = "$start - $end"

	companion object {
		/** 2024 - 2025 lesson times. */
		val lessonTimes = arrayOf(
			Time(7u, 50u) to Time(8u, 0u),
			Time(8u, 10u) to Time(8u, 50u),
			Time(8u, 50u) to Time(9u, 30u),
			Time(9u, 40u) to Time(10u, 20u),
			Time(10u, 20u) to Time(11u, 0u),
			Time(11u, 20u) to Time(12u, 0u),
			Time(12u, 0u) to Time(12u, 40u),
			Time(12u, 40u) to Time(13u, 10u),
			Time(13u, 10u) to Time(13u, 30u),
			Time(13u, 40u) to Time(14u, 20u),
			Time(14u, 20u) to Time(15u, 0u),
			Time(15u, 10u) to Time(15u, 50u),
			Time(15u, 50u) to Time(16u, 30u),
			Time(16u, 30u) to Time(17u, 30u),
			Time(17u, 30u) to Time(18u, 30u),
			Time(19u, 0u) to Time(20u, 0u),
			Time(20u, 0u) to Time(21u, 0u),
		)
	}
}

/** Creates a [TimeRange] from a [start time][this] and [end time][end]. */
inline infix fun Time.to(end: Time) = TimeRange(this, end)
