package dev.scie.today.ui.navigation

/** A sealed class to represent a screen in the app. Stores either a [TopLevelDestination] or a
 * [TodayAppFunction]. Mainly used by the top app bar and navigation bar to determine which page is
 * currently being shown.
 */
sealed interface TodayScreen {
	data class TopLevel(val topLevelDestination: TopLevelDestination) : TodayScreen
	data class AppFunction(val appFunction: TodayAppFunction) : TodayScreen
}