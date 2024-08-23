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

import kotlinx.serialization.Serializable

@Serializable
internal data class CMSAssembly(
	val title: String,
	val location: String,
	// TODO: Consider using kotlinx-datetime to get a proper date here, as the date is returned in the format
	//  `YYYY-MM-DD`, which is probably supported out of the box
	val date: String,
	val classes: String
)