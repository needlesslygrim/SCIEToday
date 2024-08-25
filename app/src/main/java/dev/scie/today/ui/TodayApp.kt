package dev.scie.today.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.scie.today.lib.cmsConnector.Timetable
import dev.scie.today.lib.cmsConnector.util.TimeRange
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
import dev.scie.today.ui.screens.UnderConstructionScreen
import dev.scie.today.ui.screens.WebCMSScreen

/** Sample timetable used for offline testing */
// FIXME: Move elsewhere.
val sampleTimetable = Timetable(
	week = Timetable.Week(
		type = Timetable.Week.Type.WeekA,
		mondayTimeslots = listOf(
			Timetable.TimeSlot.Empty(TimeRange(TimeRange.lessonTimes[0].start, TimeRange.lessonTimes[0].end)),
			Timetable.TimeSlot.Empty(TimeRange(TimeRange.lessonTimes[1].start, TimeRange.lessonTimes[1].end)),
			Timetable.TimeSlot.Empty(TimeRange(TimeRange.lessonTimes[2].start, TimeRange.lessonTimes[2].end)),
			Timetable.TimeSlot.Same(
				event = Timetable.Event(
					id = 21278u,
					type = Timetable.Event.Type.Lesson,
					name = "AL.B.CPU",
					subject = Subject.ComputerScience,
					room = "B433",
					teacher = "SHW"
				),
				time = TimeRange(TimeRange.lessonTimes[3].start, TimeRange.lessonTimes[3].end)
			),
			Timetable.TimeSlot.Same(
				event = Timetable.Event(
					id = 21278u,
					type = Timetable.Event.Type.Lesson,
					name = "AL.B.CPU",
					subject = Subject.ComputerScience,
					room = "B433",
					teacher = "SHW"
				),
				time = TimeRange(TimeRange.lessonTimes[4].start, TimeRange.lessonTimes[4].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[5].start, TimeRange.lessonTimes[5].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[6].start, TimeRange.lessonTimes[6].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[7].start, TimeRange.lessonTimes[7].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[8].start, TimeRange.lessonTimes[8].end)
			),
			Timetable.TimeSlot.Same(
				event = Timetable.Event(
					id = 21471u,
					type = Timetable.Event.Type.Lesson,
					name = "AL.C.HIS",
					subject = Subject.History,
					room = "A319",
					teacher= "GOB"
				),
				time = TimeRange(TimeRange.lessonTimes[9].start, TimeRange.lessonTimes[9].end)
			),
			Timetable.TimeSlot.Same(
				event = Timetable.Event(
					id = 21471u,
					type = Timetable.Event.Type.Lesson,
					name = "AL.C.HIS",
					subject = Subject.History,
					room = "A319",
					teacher= "GOB"
				),
				time = TimeRange(TimeRange.lessonTimes[10].start, TimeRange.lessonTimes[10].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[11].start, TimeRange.lessonTimes[11].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[12].start, TimeRange.lessonTimes[12].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[13].start, TimeRange.lessonTimes[13].end)
			),
		),
		tuesdayTimeslots = listOf(
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[0].start, TimeRange.lessonTimes[0].end)
			),
			Timetable.TimeSlot.Same(
				event = Timetable.Event(
					id = 21585u,
					type = Timetable.Event.Type.Lesson,
					name = "AP.E.CPU",
					subject = Subject.ComputerScience,
					room = "B431",
					teacher = "SHW",
				),
				time = TimeRange(TimeRange.lessonTimes[1].start, TimeRange.lessonTimes[1].end)
			),
			Timetable.TimeSlot.Same(
				event = Timetable.Event(
					id = 21585u,
					type = Timetable.Event.Type.Lesson,
					name = "AP.E.CPU",
					subject = Subject.ComputerScience,
					room = "B431",
					teacher = "SHW",
				),
				time = TimeRange(TimeRange.lessonTimes[2].start, TimeRange.lessonTimes[2].end)
			),
			Timetable.TimeSlot.Same(
				event = Timetable.Event(
					id = 21784u,
					type = Timetable.Event.Type.Lesson,
					name = "AL.F.PHY1",
					subject = Subject.Physics,
					room = "A803",
					teacher = "MIR"
				),
				time = TimeRange(TimeRange.lessonTimes[3].start, TimeRange.lessonTimes[3].end)
			),
			Timetable.TimeSlot.Same(
				event = Timetable.Event(
					id = 21784u,
					type = Timetable.Event.Type.Lesson,
					name = "AL.F.PHY1",
					subject = Subject.Physics,
					room = "A803",
					teacher = "MIR"
				),
				time = TimeRange(TimeRange.lessonTimes[4].start, TimeRange.lessonTimes[4].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[5].start, TimeRange.lessonTimes[5].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[6].start, TimeRange.lessonTimes[6].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[7].start, TimeRange.lessonTimes[7].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[8].start, TimeRange.lessonTimes[8].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[9].start, TimeRange.lessonTimes[9].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[10].start, TimeRange.lessonTimes[10].end)
			),
			Timetable.TimeSlot.Same(
				event = Timetable.Event(
					id = 21278u,
					type = Timetable.Event.Type.Lesson,
					name = "AL.B.CPU",
					subject = Subject.ComputerScience,
					room = "B433",
					teacher = "SHW"
				),
				time = TimeRange(TimeRange.lessonTimes[11].start, TimeRange.lessonTimes[11].end)
			),
			Timetable.TimeSlot.Same(
				event = Timetable.Event(
					id = 21278u,
					type = Timetable.Event.Type.Lesson,
					name = "AL.B.CPU",
					subject = Subject.ComputerScience,
					room = "B433",
					teacher = "SHW"
				),
				time = TimeRange(TimeRange.lessonTimes[12].start, TimeRange.lessonTimes[12].end)
			),
			// TODO: Temporary until I find out what the ID is for this ECA.
			Timetable.TimeSlot.Same(
				event = Timetable.Event(
					id = 0u,
					type = Timetable.Event.Type.ECA,
					name = "SCIE DEV - Software Development",
					subject = Subject.ECA,
					room = "A411",
					teacher = "RYA"
				),
				time = TimeRange(TimeRange.lessonTimes[13].start, TimeRange.lessonTimes[13].end)
			),
		),
		wednesdayTimeslots = listOf(
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[0].start, TimeRange.lessonTimes[0].end)
			),
			Timetable.TimeSlot.Same(
				event = Timetable.Event(
					id = 21471u,
					type = Timetable.Event.Type.Lesson,
					name = "AL.C.HIS",
					subject = Subject.History,
					room = "A319",
					teacher= "GOB"
				),
				time = TimeRange(TimeRange.lessonTimes[1].start, TimeRange.lessonTimes[1].end)
			),
			Timetable.TimeSlot.Same(
				event = Timetable.Event(
					id = 21471u,
					type = Timetable.Event.Type.Lesson,
					name = "AL.C.HIS",
					subject = Subject.History,
					room = "A319",
					teacher= "GOB"
				),
				time = TimeRange(TimeRange.lessonTimes[2].start, TimeRange.lessonTimes[2].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[3].start, TimeRange.lessonTimes[3].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[4].start, TimeRange.lessonTimes[4].end)
			),
			Timetable.TimeSlot.Same(
				event = Timetable.Event(
					id = 22042u,
					type = Timetable.Event.Type.Lesson,
					name = "A2.Wood3.PSHE",
					subject = Subject.PSHE,
					room = "A410",
					teacher = "RYA"
				),
				time = TimeRange(TimeRange.lessonTimes[5].start, TimeRange.lessonTimes[5].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[6].start, TimeRange.lessonTimes[6].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[7].start, TimeRange.lessonTimes[7].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[8].start, TimeRange.lessonTimes[8].end)
			),
			Timetable.TimeSlot.Same(
				event = Timetable.Event(
					id = 21585u,
					type = Timetable.Event.Type.Lesson,
					name = "AP.E.CPU",
					subject = Subject.ComputerScience,
					room = "B431",
					teacher = "SHW",
				),
				time = TimeRange(TimeRange.lessonTimes[9].start, TimeRange.lessonTimes[9].end)
			),
			Timetable.TimeSlot.Same(
				event = Timetable.Event(
					id = 21585u,
					type = Timetable.Event.Type.Lesson,
					name = "AP.E.CPU",
					subject = Subject.ComputerScience,
					room = "B431",
					teacher = "SHW",
				),
				time = TimeRange(TimeRange.lessonTimes[10].start, TimeRange.lessonTimes[10].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[11].start, TimeRange.lessonTimes[11].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[12].start, TimeRange.lessonTimes[12].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[13].start, TimeRange.lessonTimes[13].end)
			),
		),
		thursdayTimeslots = listOf(
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[0].start, TimeRange.lessonTimes[0].end)
			),
			Timetable.TimeSlot.Same(
				event = Timetable.Event(
					id = 21471u,
					type = Timetable.Event.Type.Lesson,
					name = "AL.C.HIS",
					subject = Subject.History,
					room = "A319",
					teacher= "GOB"
				),
				time = TimeRange(TimeRange.lessonTimes[1].start, TimeRange.lessonTimes[1].end)
			),
			Timetable.TimeSlot.Same(
				event = Timetable.Event(
					id = 21471u,
					type = Timetable.Event.Type.Lesson,
					name = "AL.C.HIS",
					subject = Subject.History,
					room = "A319",
					teacher= "GOB"
				),
				time = TimeRange(TimeRange.lessonTimes[2].start, TimeRange.lessonTimes[2].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[3].start, TimeRange.lessonTimes[3].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[4].start, TimeRange.lessonTimes[4].end)
			),
			Timetable.TimeSlot.Empty(
				time = TimeRange(TimeRange.lessonTimes[5].start, TimeRange.lessonTimes[5].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[6].start, TimeRange.lessonTimes[6].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[7].start, TimeRange.lessonTimes[7].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[8].start, TimeRange.lessonTimes[8].end)
			),
			Timetable.TimeSlot.Same(
				event = Timetable.Event(
					id = 21585u,
					type = Timetable.Event.Type.Lesson,
					name = "AP.E.CPU",
					subject = Subject.ComputerScience,
					room = "B431",
					teacher = "SHW",
				),
				time = TimeRange(TimeRange.lessonTimes[9].start, TimeRange.lessonTimes[9].end)
			),
			Timetable.TimeSlot.Same(
				event = Timetable.Event(
					id = 21585u,
					type = Timetable.Event.Type.Lesson,
					name = "AP.E.CPU",
					subject = Subject.ComputerScience,
					room = "B431",
					teacher = "SHW",
				),
				time = TimeRange(TimeRange.lessonTimes[10].start, TimeRange.lessonTimes[10].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[11].start, TimeRange.lessonTimes[11].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[12].start, TimeRange.lessonTimes[12].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[13].start, TimeRange.lessonTimes[13].end)
			),
		),
		fridayTimeslots = listOf(
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[0].start, TimeRange.lessonTimes[0].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[1].start, TimeRange.lessonTimes[1].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[2].start, TimeRange.lessonTimes[2].end)
			),
			Timetable.TimeSlot.Same(
				event = Timetable.Event(
					id = 21585u,
					type = Timetable.Event.Type.Lesson,
					name = "AP.E.CPU",
					subject = Subject.ComputerScience,
					room = "B431",
					teacher = "SHW",
				),
				time = TimeRange(TimeRange.lessonTimes[3].start, TimeRange.lessonTimes[3].end)
			),
			Timetable.TimeSlot.Same(
				event = Timetable.Event(
					id = 21585u,
					type = Timetable.Event.Type.Lesson,
					name = "AP.E.CPU",
					subject = Subject.ComputerScience,
					room = "B431",
					teacher = "SHW",
				),
				time = TimeRange(TimeRange.lessonTimes[4].start, TimeRange.lessonTimes[4].end)
			),
			Timetable.TimeSlot.Empty(
				time = TimeRange(TimeRange.lessonTimes[5].start, TimeRange.lessonTimes[5].end)
			),
			Timetable.TimeSlot.Same(
				event = Timetable.Event(
					id = 21784u,
					type = Timetable.Event.Type.Lesson,
					name = "AL.F.PHY1",
					subject = Subject.Physics,
					room = "A803",
					teacher = "MIR"
				),
				time = TimeRange(TimeRange.lessonTimes[6].start, TimeRange.lessonTimes[6].end)
			),
			Timetable.TimeSlot.Same(
				event = Timetable.Event(
					id = 21784u,
					type = Timetable.Event.Type.Lesson,
					name = "AL.F.PHY1",
					subject = Subject.Physics,
					room = "A803",
					teacher = "MIR"
				),
				time = TimeRange(TimeRange.lessonTimes[7].start, TimeRange.lessonTimes[7].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[8].start, TimeRange.lessonTimes[8].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[9].start, TimeRange.lessonTimes[9].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[10].start, TimeRange.lessonTimes[10].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[11].start, TimeRange.lessonTimes[11].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[12].start, TimeRange.lessonTimes[12].end)
			),
			Timetable.TimeSlot.Empty(
				TimeRange(TimeRange.lessonTimes[13].start, TimeRange.lessonTimes[13].end)
			),
		),
	)
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
			it.hasRoute<UnderConstructionScreen>() -> {
				val args = navBackStackEntry?.toRoute<UnderConstructionScreen>()
				TodayScreen.UnderConstruction(args?.appFunction)
			}
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
								else -> navController.navigate(UnderConstructionScreen(appFunction))
							}
						},

						modifier = paddingModifier
					)
				}
				composable<TimetableScreen> {
					TimetableScreen(
						timetable = sampleTimetable,
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
					UnderConstructionScreen()
				}
				composable<WebCMSScreen> {
					WebCMSScreen(modifier = Modifier.padding(innerPadding))
				}
				composable<UnderConstructionScreen> {
					UnderConstructionScreen()
				}
			}
		}
	}
}