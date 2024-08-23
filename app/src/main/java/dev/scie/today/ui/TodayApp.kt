package dev.scie.today.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.scie.today.navigation.AssessmentsScreen
import dev.scie.today.ui.screens.HomeworkScreen
import dev.scie.today.navigation.TodayAppFunction
import dev.scie.today.navigation.TodayScreen
import dev.scie.today.navigation.TopLevelDestination
import dev.scie.today.ui.components.TodayNavigationScaffold
import dev.scie.today.ui.components.TodayTopAppBar
import dev.scie.today.ui.screens.HomeScreen
import dev.scie.today.ui.screens.HomeworkAssignment
import dev.scie.today.ui.screens.Lesson
import dev.scie.today.ui.screens.Subject
import dev.scie.today.ui.screens.TimetableScreen
import dev.scie.today.ui.screens.WebCMSScreen

/** Sample lessons used for offline testing */
// FIXME: Move elsewhere.
val sampleLessons = listOf(
	listOf(
		Lesson(Subject.MorningRegistration, "B427", "A1.Wood3", "7:50 - 7:57", "FT"),
		Lesson(Subject.Mathematics, "A411", "AL.A.MAT1", "8:00 - 9:20", "MEL"),
		Lesson(Subject.Tutorial, "A707", "TUT.PHY.M5", "11:10 - 11:50", "STW"),
		Lesson(Subject.History, "A318", "AS.D.HIS", "15:10 - 16:30", "ROS"),
	), listOf(
		Lesson(Subject.MorningRegistration, "B427", "A1.Wood3", "7:50 - 7:57", "FT"),
		Lesson(Subject.Physics, "A715", "AS.E.PHY1", "8:00 - 9:20", "JUJ"),
		Lesson(Subject.ComputerScience, "B432", "AS.F.CPU", "9:30 - 10:50", "SHW"),
		Lesson(Subject.Tutorial, "A416", "TUT.MAT.TU5", "11:10 - 11:50", "RYA"),
		Lesson(Subject.Mathematics, "A411", "AL.A.MAT1", "13:40 - 15:00", "MEL"),
		Lesson(Subject.EPQ, "A612", "A1.B.EPQ.PHY", "15:10 - 16:30", "APS")
	), listOf(), listOf(), listOf()
)

/** Sample assignment list used for offline testing */
// FIXME: Move elsewhere.
val sampleAssignments = listOf(
	HomeworkAssignment(
		"standing waves", "A short description about sanding waves", "Phy-AS.E.PHY1", "2024-02-27"
	), HomeworkAssignment(
		"superposition of waves & interference", null, "Phy-AS.E.PHY1", "2024-01-29"
	), HomeworkAssignment(
		"Doppler effect",
		"Pages 16-316 of the assignment booklet, some other long description that should overflow onto the next line",
		"Phy-AS.E.PHY1",
		"2024-01-23"
	)
)

@Composable
fun TodayApp(
	navController: NavHostController = rememberNavController(),
	modifier: Modifier = Modifier
) {
	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val currentDestination = navBackStackEntry?.destination
	val currentScreen = currentDestination?.let {
		when {
			it.hasRoute<HomeScreen>() -> TodayScreen.TopLevel(TopLevelDestination.HOME)
			it.hasRoute<TimetableScreen>() -> TodayScreen.TopLevel(TopLevelDestination.TIMETABLE)
			it.hasRoute<HomeworkScreen>() -> TodayScreen.TopLevel(TopLevelDestination.HOMEWORK)
			it.hasRoute<AssessmentsScreen>() -> TodayScreen.TopLevel(TopLevelDestination.GRADES)
			it.hasRoute<WebCMSScreen>() -> TodayScreen.AppFunction(TodayAppFunction.WEB_CMS)
			else -> TodayScreen.TopLevel(TopLevelDestination.HOME)
		}
	} ?: TodayScreen.TopLevel(TopLevelDestination.HOME)


	val navigateToScreen: (TopLevelDestination) -> Unit = { screen ->
		navController.navigate(
			when (screen) {
				TopLevelDestination.HOME -> HomeScreen
				TopLevelDestination.TIMETABLE -> TimetableScreen
				TopLevelDestination.HOMEWORK -> HomeworkScreen
				TopLevelDestination.GRADES -> AssessmentsScreen()
			}
		) {
			popUpTo<HomeScreen> {
				saveState = true
			}
			launchSingleTop = true
			restoreState = true
		}

	}

	TodayNavigationScaffold(
		currentScreen =
			if (currentScreen is TodayScreen.TopLevel) {
				currentScreen.topLevelDestination
			} else {
				TopLevelDestination.HOME
			},
		navigateToScreen = navigateToScreen,
	) {
		Scaffold(
			topBar = {
				TodayTopAppBar(
					currentScreen = currentScreen,
					navBackStackEntry = navBackStackEntry,
					canNavigateBack = navController.previousBackStackEntry != null,
					navigateUp = { navController.popBackStack() }
				)
			},
			modifier = modifier
		) { innerPadding ->
			val paddingModifier = Modifier.padding(innerPadding)
			NavHost(
				navController = navController,
				startDestination = HomeScreen,
			) {
				composable<HomeScreen> {
					HomeScreen(
						onClickMainCard = {},

						latestNoticeDate = "02-02-2024",
						latestNoticeName = "2024 SCIE Jan Newsletter",
						onClickNoticeCard = {},

						latestBulletinName = "MCS Week Day 5",
						onClickBulletinCard = {},

						nextHomeworkAssignmentLabel = null,
						nextHomeworkAssignmentDueDate = null,
						onClickHomeworkCard = {},

						onClickAppFunction = { appFunction ->
							when (appFunction) {
								TodayAppFunction.WEB_CMS -> navController.navigate(WebCMSScreen)
								else -> Unit
							}
						},

						modifier = paddingModifier
					)
				}
				composable<TimetableScreen> {
					TimetableScreen(
						lessons = sampleLessons,
						modifier = paddingModifier
					)
				}
				composable<HomeworkScreen> {
					HomeworkScreen(
						assignments = sampleAssignments,
						onMarkAssignmentDone = {},
						modifier = Modifier.padding(innerPadding)
					)
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
				composable<WebCMSScreen> {
					WebCMSScreen(22901, modifier = Modifier.padding(innerPadding))
				}
			}
		}
	}


}