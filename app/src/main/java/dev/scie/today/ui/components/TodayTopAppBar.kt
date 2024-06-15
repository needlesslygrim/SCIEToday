package dev.scie.today.ui.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.toRoute
import dev.scie.today.R
import dev.scie.today.navigation.AssessmentsScreen
import dev.scie.today.navigation.TodayScreen
import dev.scie.today.navigation.TopLevelDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodayTopAppBar(
	currentScreen: TodayScreen,
	navigateUp: () -> Unit,
	canNavigateBack: Boolean,
	navBackStackEntry: NavBackStackEntry?
) {
	when (currentScreen) {
		is TodayScreen.TopLevel -> {
			if (currentScreen.topLevelDestination == TopLevelDestination.GRADES) {
				// Shouldn't be null
				val args = navBackStackEntry?.toRoute<AssessmentsScreen>()!!
				if (args.subject != null) {
					TodaySmallTopAppBar(
						title = args.subject,
						canNavigateBack = canNavigateBack,
						navigateUp = navigateUp
					)

					return
				}
			}

			CenterAlignedTopAppBar(
				title = {
					Text(
						stringResource(
							if (currentScreen.topLevelDestination == TopLevelDestination.HOME) {
								R.string.app_name
							} else {
								currentScreen.topLevelDestination.screenNameId
							}
						)
					)
				}
			)
		}

		is TodayScreen.AppFunction -> {
			TodaySmallTopAppBar(
				title = stringResource(currentScreen.appFunction.nameId),
				canNavigateBack = canNavigateBack,
				navigateUp = navigateUp
			)
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodaySmallTopAppBar(
	title: String,
	canNavigateBack: Boolean,
	navigateUp: () -> Unit,
	modifier: Modifier = Modifier
) {
	TopAppBar(
		title = { Text(title) },
		navigationIcon = {
			if (canNavigateBack) {
				IconButton(onClick = navigateUp) {
					Icon(
						painter = painterResource(R.drawable.ic_arrow_back),
						contentDescription = stringResource(R.string.go_back)
					)
				}
			}
		},
		modifier = modifier
	)
}