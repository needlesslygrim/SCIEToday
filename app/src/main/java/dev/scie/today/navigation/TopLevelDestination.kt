package dev.scie.today.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import dev.scie.today.R
import kotlinx.serialization.Serializable

/** A 'top level destination' of the app, i.e. all screens shown in the navigatin bar. */
enum class TopLevelDestination(
	/** The icon used by the navigation bar when a screen is active. */
	@DrawableRes val selectedIcon: Int,
	/** The icon used by the navigation bar when a screen is inactive. */
	@DrawableRes val unselectedIcon: Int,
	/** The name of the screen shown to the user. */
	@StringRes val screenNameId: Int,
	/** A test tag to allow for easily getting the navigation button in unit tests/benchmarks */
	val testTag: String,
) {
	HOME(
		selectedIcon = R.drawable.ic_home_filled,
		unselectedIcon = R.drawable.ic_home_unfilled,
		screenNameId = R.string.home_screen,
		testTag = "navigation:home"
	),
	TIMETABLE(
		selectedIcon = R.drawable.ic_schedule_filled,
		unselectedIcon = R.drawable.ic_schedule_unfilled,
		screenNameId = R.string.timetable_screen,
		testTag = "navigation:timetable"
	),
	HOMEWORK(
		selectedIcon = R.drawable.ic_assignment_filled,
		unselectedIcon = R.drawable.ic_assignment_unfilled,
		screenNameId = R.string.homework_screen,
		testTag = "navigation:homework"
	),
	GRADES(
		selectedIcon = R.drawable.ic_assessment_filled,
		unselectedIcon = R.drawable.ic_assessment_unfilled,
		screenNameId = R.string.grades_screen,
		testTag = "navigation:grades"
	)
}

@Serializable
data class AssessmentsScreen(val subject: String? = null)
