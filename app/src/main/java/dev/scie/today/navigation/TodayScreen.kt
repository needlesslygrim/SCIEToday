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

/** A sealed class to represent a screen in the app. Stores either a [TopLevelDestination] or a
 * [TodayAppFunction]. Mainly used by the top app bar and navigation bar to determine which page is
 * currently being shown.
 */
sealed interface TodayScreen {
	data class TopLevel(val topLevelDestination: TopLevelDestination) : TodayScreen
	data class AppFunction(val appFunction: TodayAppFunction) : TodayScreen
	data class UnderConstruction(val appFunction: TodayAppFunction?) : TodayScreen
}