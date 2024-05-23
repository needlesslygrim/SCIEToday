package dev.scie.today.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.rememberNavController
import androidx.navigation.serialization.generateRouteWithArgs
import dev.scie.today.R
import dev.scie.today.TodayScreen
import dev.scie.today.ui.theme.SCIETodayTheme


@Composable
fun TodayNavigationBar(
	navigateToScreen: (TodayScreen) -> Unit,
	currentScreen: NavDestination?,
) {
	val onHomeScreen = currentScreen?.hasRoute<TodayScreen.HomeScreen>() ?: true
	val onTimetableScreen = currentScreen?.hasRoute<TodayScreen.TimetableScreen>() ?: false
	val onHomeworkScreen = currentScreen?.hasRoute<TodayScreen.HomeworkScreen>() ?: false
	val onAssessmentScreen = currentScreen?.hasRoute<TodayScreen.AssessmentsScreen>()
		?: false
	NavigationBar {
		NavigationBarItem(
			selected = onHomeScreen,
			onClick = { navigateToScreen(TodayScreen.HomeScreen) },
			icon = {
				Icon(
					painter = painterResource(
						if (onHomeScreen) {
							R.drawable.ic_home_filled
						} else {
							R.drawable.ic_home_unfilled
						}
					),
					contentDescription = null
				)
			},
			label = { Text(text = stringResource(id = R.string.home_screen)) }
		)
		NavigationBarItem(
			selected = onTimetableScreen,
			onClick = { navigateToScreen(TodayScreen.TimetableScreen) },
			icon = {
				Icon(
					painter = painterResource(
						if (onTimetableScreen) {
							R.drawable.ic_schedule_filled
						} else {
							R.drawable.ic_schedule_unfilled
						}
					),
					contentDescription = null
				)
			},
			label = { Text(text = stringResource(id = R.string.timetable_screen)) }
		)
		NavigationBarItem(
			selected = onHomeworkScreen,
			onClick = { navigateToScreen(TodayScreen.HomeworkScreen) },
			icon = {
				Icon(
					painter = painterResource(
						if (onHomeworkScreen) {
							R.drawable.ic_assignment_filled
						} else {
							R.drawable.ic_assignment_unfilled
						}
					),
					contentDescription = null
				)
			},
			label = { Text(text = stringResource(id = R.string.homework_screen)) }
		)
		NavigationBarItem(
			selected = onAssessmentScreen,
			onClick = { navigateToScreen(TodayScreen.AssessmentsScreen(null)) },
			icon = {
				Icon(
					painter = painterResource(
						if (onAssessmentScreen) {
							R.drawable.ic_assessment_filled
						} else {
							R.drawable.ic_assessment_unfilled
						}
					),
					contentDescription = null
				)
			},
			label = { Text(text = stringResource(id = R.string.assessments_screen)) }
		)
	}
}

