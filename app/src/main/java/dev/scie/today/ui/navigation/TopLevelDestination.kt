package dev.scie.today.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import dev.scie.today.R
import kotlinx.serialization.Serializable

enum class TopLevelDestination(
	@DrawableRes val selectedIcon: Int,
	@DrawableRes val unselectedIcon: Int,
	@StringRes val screenNameId: Int
) {
	HOME(
		selectedIcon = R.drawable.ic_home_filled,
		unselectedIcon = R.drawable.ic_home_unfilled,
		screenNameId = R.string.home_screen
	),
	TIMETABLE(
		selectedIcon = R.drawable.ic_schedule_filled,
		unselectedIcon = R.drawable.ic_schedule_unfilled,
		screenNameId = R.string.timetable_screen
	),
	HOMEWORK(
		selectedIcon = R.drawable.ic_assignment_filled,
		unselectedIcon = R.drawable.ic_assignment_unfilled,
		screenNameId = R.string.homework_screen
	),
	ASSESSMENTS(
		selectedIcon = R.drawable.ic_assessment_filled,
		unselectedIcon = R.drawable.ic_assessment_unfilled,
		screenNameId = R.string.assessments_screen
	)
}

@Serializable
data object TimetableScreen

@Serializable
data object HomeworkScreen

@Serializable
data class AssessmentsScreen(val subject: String? = null)
