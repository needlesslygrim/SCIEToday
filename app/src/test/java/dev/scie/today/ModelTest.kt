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

package dev.scie.today

import dev.scie.today.ModelJson.assemblyJson
import dev.scie.today.ModelJson.attendanceJson
import dev.scie.today.ModelJson.timetableJson
import dev.scie.today.ModelJson.userInformationJson
import dev.scie.today.lib.cmsConnector.cms.CMSAssembly
import dev.scie.today.lib.cmsConnector.cms.CMSAttendance
import dev.scie.today.lib.cmsConnector.cms.CMSTimetable
import dev.scie.today.lib.cmsConnector.cms.CMSUserInformation
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Test

class ModelTest {
	@Test
	fun testAssembly() = runTest {
		Json
			.decodeFromString<List<CMSAssembly>>(assemblyJson)
	}

	@Test
	fun testAttendance() = runTest {
		Json
			.decodeFromString<CMSAttendance>(attendanceJson)
			.toTodayType()
	}

	@Test
	fun testTimetable() = runTest {
		Json
			.decodeFromString<CMSTimetable>(timetableJson)
			.toTodayType()
	}

	@Test
	fun testUserInformation() = runTest {
		Json
			.decodeFromString<CMSUserInformation>(userInformationJson)
			.toTodayType()
		}
}
