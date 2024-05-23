package dev.scie.today

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.scie.today.ui.components.TodayNavigationBar
import dev.scie.today.ui.screens.TodayHomeScreen
import dev.scie.today.ui.theme.SCIETodayTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		enableEdgeToEdge()
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
		topBar = {
			TodayTopAppBar(navBackStackEntry)
		},
		modifier = modifier
	) { innerPadding ->
		NavHost(
			navController = navController,
			startDestination = TodayScreen.HomeScreen,
		) {
			composable<TodayScreen.HomeScreen> {
				TodayHomeScreen(
					onClickMainCard = {},

					latestNoticeDate = "02-02-2024",
					latestNoticeName = "2024 SCIE Jan Newsletter",
					onClickNoticeCard = {},

					latestBulletinName = "MCS Week Day 5",
					onClickBulletinCard = {},

					nextHomeworkAssignmentLabel = null,
					nextHomeworkAssignmentDueDate = null,
					onClickHomeworkCard = {},

					modifier = Modifier.padding(innerPadding)
				)
			}

			composable<TodayScreen.TimetableScreen> {

			}
			composable<TodayScreen.HomeworkScreen> {
			}
			composable<TodayScreen.AssessmentsScreen> {
				val args = it.toRoute<TodayScreen.AssessmentsScreen>()
				if (args.subject == null) {
					Button(onClick = { navController.navigate(TodayScreen.AssessmentsScreen("History")) }) {
						Text(text = "Go to history")
					}
				} else {
					Text("NONONO")
				}
			}
		}
	}

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodayTopAppBar(
	backStackEntry: NavBackStackEntry?
) {
	val currentScreen = backStackEntry?.destination
	if (currentScreen?.hasRoute<TodayScreen.HomeScreen>() != false) {
		CenterAlignedTopAppBar(title = { Text(stringResource(R.string.app_name)) })
	} else {
		MediumTopAppBar(
			title = {
				val headline = when {
					currentScreen.hasRoute<TodayScreen.HomeScreen>() -> stringResource(R.string.home_screen)
					currentScreen.hasRoute<TodayScreen.TimetableScreen>() -> stringResource(R.string.timetable_screen)
					currentScreen.hasRoute<TodayScreen.HomeworkScreen>() -> stringResource(R.string.homework_screen)
					currentScreen.hasRoute<TodayScreen.AssessmentsScreen>() -> {
						val args = backStackEntry.toRoute<TodayScreen.AssessmentsScreen>()
						args?.subject ?: stringResource(R.string.assessments_screen)
					}

					else -> throw Exception("This shouldn't be possible")
				}

				Text(text = headline)
			}
		)
	}
}

data class TodayAppFunction(val name: String, @DrawableRes val icon: Int)

val allAppFunctions = listOf(
	TodayAppFunction("Web CMS", R.drawable.ic_web_cms_unfilled),
	TodayAppFunction("Exam Timetable", R.drawable.ic_hourglass_empty_unfilled),
	TodayAppFunction("Attendance", R.drawable.ic_attendance_unfilled),
	TodayAppFunction("Announcements", R.drawable.ic_breaking_news_unfilled),
	TodayAppFunction("Calendar", R.drawable.ic_calendar_month_unfilled),
	TodayAppFunction("Report", R.drawable.ic_assessment_unfilled),
	TodayAppFunction("ECA", R.drawable.ic_eca_unfilled),
	TodayAppFunction("Leave", R.drawable.ic_leave_unfilled)
)


