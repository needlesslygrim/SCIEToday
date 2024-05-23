package dev.scie.today

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
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
import dev.scie.today.ui.navigation.AssessmentsScreen
import dev.scie.today.ui.navigation.HomeworkScreen
import dev.scie.today.ui.navigation.TimetableScreen
import dev.scie.today.ui.navigation.TopLevelDestination
import dev.scie.today.ui.screens.HomeScreen
import dev.scie.today.ui.screens.TodayHomeScreen
import dev.scie.today.ui.theme.SCIETodayTheme

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

@Composable
fun TodayApp(
	navController: NavHostController = rememberNavController(),
	modifier: Modifier = Modifier
) {
	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val currentDestination = navBackStackEntry?.destination
	val currentScreen = currentDestination?.let {
		when {
			it.hasRoute<HomeScreen>() -> TopLevelDestination.HOME
			it.hasRoute<TimetableScreen>() -> TopLevelDestination.TIMETABLE
			it.hasRoute<HomeworkScreen>() -> TopLevelDestination.HOMEWORK
			it.hasRoute<AssessmentsScreen>() -> TopLevelDestination.ASSESSMENTS
			else -> TopLevelDestination.HOME
		}
	} ?: TopLevelDestination.HOME
	Scaffold(
		bottomBar = {
			TodayNavigationBar(
				navigateToScreen = { screen ->
					navController.navigate(
						when (screen) {
							TopLevelDestination.HOME -> HomeScreen
							TopLevelDestination.TIMETABLE -> TimetableScreen
							TopLevelDestination.HOMEWORK -> HomeworkScreen
							TopLevelDestination.ASSESSMENTS -> AssessmentsScreen()
						}
					) {
						popUpTo<HomeScreen> {
							saveState = true
						}
						launchSingleTop = true
						restoreState = true
					}

				},
				currentScreen = currentScreen,
			)
		},
		topBar = {
			TodayTopAppBar(currentScreen, navBackStackEntry)
		},
		modifier = modifier
	) { innerPadding ->
		NavHost(
			navController = navController,
			startDestination = HomeScreen,
		) {
			composable<HomeScreen> {
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

			composable<TimetableScreen> {

			}
			composable<HomeworkScreen> {
			}
			composable<AssessmentsScreen> {
				val args = it.toRoute<AssessmentsScreen>()
				if (args.subject == null) {
					Button(onClick = { navController.navigate(AssessmentsScreen("History")) }) {
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
	currentScreen: TopLevelDestination,
	navBackStackEntry: NavBackStackEntry?
) {
	when (currentScreen) {
		TopLevelDestination.HOME -> CenterAlignedTopAppBar(title = { Text(stringResource(R.string.app_name)) })
		else -> {
			MediumTopAppBar(
				title = {
					Text(
						when (currentScreen) {
							TopLevelDestination.ASSESSMENTS -> {
								// This shouldn't be null, because we are definitely on a page. I think this is the
								// correct assumption of how it works. :^)
								val args = navBackStackEntry?.toRoute<AssessmentsScreen>()!!
								args.subject ?: stringResource(currentScreen.screenNameId)
							}

							else -> stringResource(currentScreen.screenNameId)
						}
					)
				}
			)
		}
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


