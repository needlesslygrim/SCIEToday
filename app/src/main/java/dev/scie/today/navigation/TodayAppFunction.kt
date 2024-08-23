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

package dev.scie.today.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import dev.scie.today.R

/** A function of the app, e.g. showing CMS in a WebView. These are displayed in a grid on the
 * home screen, and can be opened.
 */
enum class TodayAppFunction(
	/** Resource ID of the icon shown on the home screen grid. */
	@DrawableRes val icon: Int,
	/** The name of a function, shown to the user on both the home screen and in app bars. */
	@StringRes val nameId: Int
) {
	/** Show CMS in a web view. */
	WEB_CMS(R.drawable.ic_web_cms_unfilled, R.string.web_cms),
	EXAM_TIMETABLE(R.drawable.ic_calendar_month_unfilled, R.string.exam_timetable),
	ATTENDANCE(R.drawable.ic_attendance_unfilled, R.string.attendance),
	ANNOUNCEMENTS(R.drawable.ic_breaking_news_unfilled, R.string.announcements),
	ECA(R.drawable.ic_eca_unfilled, R.string.subject_eca),
	LEAVE(R.drawable.ic_leave_unfilled, R.string.leave);
}