package dev.scie.today

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.scie.today.ui.theme.SCIETodayTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			SCIETodayTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
				) {
					TodayApp()
				}
			}
		}
	}
}


sealed interface TodayScreen {
	val labelId: Int

	@Serializable
	data object HomeScreen : TodayScreen {
		@StringRes
		override val labelId: Int = R.string.home_screen
	}

	@Serializable
	data object TimetableScreen : TodayScreen {
		@StringRes
		override val labelId: Int = R.string.timetable_screen
	}

	@Serializable
	data object HomeworkScreen : TodayScreen {
		@StringRes
		override val labelId: Int = R.string.homework_screen
	}

	@Serializable
	data class AssessmentsScreen(val subject: String?) : TodayScreen {
		@StringRes
		override val labelId: Int = R.string.assessments_screen
	}
}

@Composable
fun TodayApp(
	navController: NavHostController = rememberNavController(),
	modifier: Modifier = Modifier
) {
	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val currentDestination = navBackStackEntry?.destination
	Scaffold(
		bottomBar = {
			TodayNavigationBar(
				navigateToScreen = { page ->
					when (page) {
						is TodayScreen.HomeScreen -> navController.navigate(page) {
							popUpTo<TodayScreen.HomeScreen> {
								saveState = true
							}
							launchSingleTop = true
							restoreState = true
						}

						is TodayScreen.AssessmentsScreen -> navController.navigate(page) {
							popUpTo<TodayScreen.HomeScreen> {
								saveState = true
							}
							launchSingleTop = true
							restoreState = true
						}

						is TodayScreen.HomeworkScreen -> navController.navigate(page) {
							popUpTo<TodayScreen.HomeScreen> {
								saveState = true
							}
							launchSingleTop = true
							restoreState = true
						}

						is TodayScreen.TimetableScreen -> navController.navigate(page) {
							popUpTo<TodayScreen.HomeScreen> {
								saveState = true
							}
							launchSingleTop = true
							restoreState = true
						}
					}
				},
				currentScreen = currentDestination,
			)
		},
		modifier = modifier
	) { innerPadding ->
		NavHost(
			navController = navController,
			startDestination = TodayScreen.HomeScreen,
			modifier = Modifier.padding(innerPadding)
		) {
			composable<TodayScreen.HomeScreen> {
				TodayHomeScreen()
			}

			composable<TodayScreen.TimetableScreen> {

			}
			composable<TodayScreen.HomeworkScreen> {
			}
			composable<TodayScreen.AssessmentsScreen> {
				val args = it.toRoute<TodayScreen.AssessmentsScreen>()
			}
		}
	}

}

@Composable
fun TodayNavigationBar(
	navigateToScreen: (TodayScreen) -> Unit,
	currentScreen: NavDestination?,
	modifier: Modifier = Modifier
) {
	val onHomeScreen = currentScreen?.hasRoute<TodayScreen.HomeScreen>() ?: false
	val onTimetableScreen = currentScreen?.hasRoute<TodayScreen.TimetableScreen>() ?: false
	val onHomeworkScreen = currentScreen?.hasRoute<TodayScreen.HomeworkScreen>() ?: false
	val onAssessmentScreen = currentScreen?.hasRoute<TodayScreen.AssessmentsScreen>()
		?: false
	NavigationBar(modifier = modifier) {
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

@Composable
fun TodayHomeScreen(modifier: Modifier = Modifier) {
	Text(text = "Hello, World!")
}