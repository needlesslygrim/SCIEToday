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

@JvmInline
value class Time private constructor(private val time: UInt) {
	val hour get() = time.shr(16)
	val minute get() = time.and(0xFFFFu)

	constructor(hour: UShort, minute: UShort) : this(hour.toUInt().shl(16) or minute.toUInt())

	override fun toString(): String {
		return "${hour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')}"
	}
}