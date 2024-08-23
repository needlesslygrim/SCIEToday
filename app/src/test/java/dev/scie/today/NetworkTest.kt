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

import dev.scie.today.lib.cmsConnector.UserCredentials
import dev.scie.today.lib.cmsConnector.cms.CMSAssembly
import dev.scie.today.lib.cmsConnector.cms.CMSAttendance
import dev.scie.today.lib.cmsConnector.cms.CMSTimetable
import dev.scie.today.lib.cmsConnector.cms.CMSUserInformation
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import kotlin.test.BeforeTest

class NetworkTest {
	@Test
	fun testTimetable() = runTest {
		val timetableResponse = client.request("/api/legacy/students/my/timetable") {
			url {
				parameters.append("year", "2024")
			}
		}

		assertEquals(timetableResponse.status, HttpStatusCode.OK)

		timetableResponse
			.body<CMSTimetable>()
			.toTodayType()
	}

	@Test
	fun testUserInformation(): Unit = runTest {
		val userInformationResponse = client.request("/api/legacy/students/my")
		assertEquals(HttpStatusCode.OK, userInformationResponse.status)
		userInformationResponse
			.body<CMSUserInformation>()
	}

	@Test
	fun testAssemblies() = runTest {
		val assembliesResponse = client.request("/api/legacy/students/my/assembly")
		assertEquals(HttpStatusCode.OK, assembliesResponse.status)
		assembliesResponse
			.body<List<CMSAssembly>>()
	}

	@Test
	fun testAttendance() = runTest {
		val attendanceResponse = client.request("/api/legacy/students/my/attendance")
		assertEquals(HttpStatusCode.OK, attendanceResponse.status)
		attendanceResponse
			.body<CMSAttendance>()
			.toTodayType()
	}

	companion object {
		private val client = HttpClient(OkHttp) {
			install(HttpCookies)
			install(DefaultRequest) {
				contentType(ContentType.Application.Json)
				accept(ContentType.Application.Json)
				url("https://cms.alevel.com.cn")
			}
			install(ContentNegotiation) {
				json()
			}
		}

		// TODO: Use gradle local properties or something similar to get the password.
		private val password = System.getenv().getOrDefault("CMS_PASSWORD", "")

		@JvmStatic
		@BeforeClass
		fun authenticate(): Unit {
			runTest {
				val response = client.request("/api/token/") {
					method = HttpMethod.Post
					setBody(UserCredentials("s22901", password))
				}

				assertEquals(HttpStatusCode.OK, response.status)
			}
		}
	}

}
