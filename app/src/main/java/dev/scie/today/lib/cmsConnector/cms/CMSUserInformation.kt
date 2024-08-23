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
import dev.scie.today.lib.cmsConnector.Gender
import dev.scie.today.lib.cmsConnector.House
import dev.scie.today.lib.cmsConnector.UserInformation
import dev.scie.today.lib.cmsConnector.YearGroup
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
internal data class CMSUserInformation(
	@SerialName("has_more_info") val hasMoreInfo: Boolean,
	@SerialName("general_info") val generalInfo: GeneralInfo,
	@SerialName("basic_info") val basicInfo: BasicInfo,
	/** I have no idea what this stores, as for me it is `null` */
	@SerialName("more_info") val moreInfo: JsonElement?,
	/** I have no idea what this stores, as for me it is `null` */
	val relatives: JsonElement?
) : CMSType<UserInformation> {
	@Serializable
	data class GeneralInfo(
		val id: UInt,
		val name: String,
		@SerialName("en_name") val englishName: String,
		@SerialName("full_name") val fullName: String,
		@SerialName("form_group") val formGroup: String,
		val photo: String
	)

	@Serializable
	data class BasicInfo(
		val gender: Gender,
		@SerialName("grade") val yearGroup: YearGroup,
		val house: House,
		val dormitory: String,
		// FIXME: Make this an enum.
		@SerialName("dormitory_kind") val dormitoryKind: String,
		// TODO: Consider using some kind of `Date` class or a custom data class that allows for a cleaner way of
		//   getting this. The problem is that CMS returns this data in the format `YYYY.MM`, so I'm unsure whether
		//   any standard date type's serialisation logic would work by default.
		@SerialName("enrollment") val enrollment: String,
		@SerialName("mobile") val mobileNumber: String,
		@SerialName("school_email") val schoolEmail: String,
		@SerialName("student_email") val studentEmail: String,
	)

	override fun toTodayType(): UserInformation =
		UserInformation(
			id = generalInfo.id,
			name = generalInfo.name,
			house = basicInfo.house,
			formGroup = generalInfo.formGroup
		)
}