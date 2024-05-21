package dev.scie.today

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
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

					padding = innerPadding,
					modifier = Modifier.padding(innerPadding)
/*
					modifier = Modifier.padding(top = innerPadding.calculateTopPadding())
*/
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

@Composable
fun TodayNavigationBar(
	navigateToScreen: (TodayScreen) -> Unit,
	currentScreen: NavDestination?,
) {
	val onHomeScreen = currentScreen?.hasRoute<TodayScreen.HomeScreen>() ?: false
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

@Composable
fun TodayHomeScreen(
	onClickMainCard: () -> Unit,

	onClickBulletinCard: () -> Unit,
	latestBulletinName: String,

	latestNoticeName: String,
	latestNoticeDate: String,
	onClickNoticeCard: () -> Unit,

	nextHomeworkAssignmentLabel: String?,
	nextHomeworkAssignmentDueDate: String?,
	onClickHomeworkCard: () -> Unit,

	padding: PaddingValues,
	modifier: Modifier = Modifier,
) {
	Column(
		verticalArrangement = Arrangement.spacedBy(8.dp),
		modifier = modifier
			.verticalScroll(rememberScrollState())
			.padding(horizontal = 16.dp)

	) {
		OutlinedCard(
			onClick = onClickMainCard,
			modifier = Modifier
				.height(140.dp)
				.fillMaxWidth()
		) { }


		OutlinedCard {
			ListItem(
				headlineContent = { Text(text = latestBulletinName) },
				overlineContent = { Text(text = "Bulletin") },
				supportingContent = { Text(text = "Activities and Events") },
				modifier = Modifier.clickable(onClick = onClickBulletinCard)
			)
			HorizontalDivider()
			ListItem(
				headlineContent = { Text(text = latestNoticeName) },
				overlineContent = { Text("Notice") },
				trailingContent = { Text(text = latestNoticeDate) },
				modifier = Modifier.clickable(onClick = onClickNoticeCard)
			)
			HorizontalDivider()
			ListItem(
				headlineContent = {
					Text(text = nextHomeworkAssignmentLabel ?: "All homework completed or overdue")
				},
				overlineContent = { Text("Homework") },
				trailingContent = {
					nextHomeworkAssignmentDueDate?.let { Text(text = it) }
				},
				modifier = Modifier.clickable(onClick = onClickHomeworkCard)
			)
		}


		Column(
			verticalArrangement = Arrangement.spacedBy(8.dp),
			horizontalAlignment = Alignment.CenterHorizontally,
		) {
			for (appFunctionPair in allAppFunctions.chunked(2)) {
				AppFunctionRow(appFunctionPair)
			}
		}

		Spacer(modifier = Modifier.height(4.dp))
	}
}


@Composable
fun AppFunctionRow(
	appFunctionPair: List<TodayAppFunction>,
	modifier: Modifier = Modifier
) {
	val left = appFunctionPair.first()
	val right = appFunctionPair[1]
	Row(
		horizontalArrangement = Arrangement.spacedBy(8.dp),
		modifier = modifier
	) {
		AppFunctionCard(
			functionName = left.name,
			functionIcon = left.icon,
			onClick = { /*TODO*/ },
			modifier = Modifier.weight(1f)
		)
		AppFunctionCard(
			functionName = right.name,
			functionIcon = right.icon,
			onClick = { /*TODO*/ },
			modifier = Modifier.weight(1f)
		)
	}
}

@Composable
fun AppFunctionCard(
	functionName: String,
	@DrawableRes functionIcon: Int,
	onClick: () -> Unit,
	modifier: Modifier = Modifier
) {
	OutlinedCard(
		onClick = onClick,
		modifier = modifier.height(56.dp)
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = modifier
				.fillMaxSize()
				.padding(vertical = 8.dp)
		) {
			Icon(
				painter = painterResource(functionIcon),
				contentDescription = null,
				modifier = Modifier.padding(horizontal = 16.dp)
			)
			Text(text = functionName, style = MaterialTheme.typography.bodyMedium)
		}
	}
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TodayHomeScreenPreview() {
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

		padding = PaddingValues.Absolute(0.dp)
	)
}