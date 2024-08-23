/*
 * Copyright (c) 2024 Erick Howard
 *
 * This file is part of SCIEToday.
 *
 * SCIEToday is free software: you can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * SCIEToday is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with SCIEToday. If not,
 * see <https://www.gnu.org/licenses/>.
 */

package dev.scie.today.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import dev.scie.today.navigation.TodayScreen
import dev.scie.today.navigation.TopLevelDestination

@Composable
fun TodayNavigationScaffold(
	currentScreen: TopLevelDestination,
	navigateToScreen: (TopLevelDestination) -> Unit,
	content: @Composable () -> Unit = {}
) {
	NavigationSuiteScaffold(
		navigationSuiteItems = {
			TopLevelDestination.entries.forEach { topLevelDestination ->
				val selected = currentScreen == topLevelDestination
				item(
					selected = selected,
					onClick = { navigateToScreen(topLevelDestination) },
					icon = {
						Icon(
							painter = painterResource(
								if (selected) {
									topLevelDestination.selectedIcon
								} else {
									topLevelDestination.unselectedIcon
								}
							),
							contentDescription = null
						)
					},
					label = { Text(text = stringResource(topLevelDestination.screenNameId)) },
					modifier = Modifier.testTag(topLevelDestination.testTag)
				)
			}
		},
		content = content
	)
}

class TodayNavigationBarPreviewParameterProvider : PreviewParameterProvider<TopLevelDestination> {
	override val values: Sequence<TopLevelDestination> = TopLevelDestination.entries.asSequence()
}


@Preview
@PreviewScreenSizes
@Composable
private fun TodayNavigationScaffoldPreview(
	@PreviewParameter(TodayNavigationBarPreviewParameterProvider::class) currentScreen: TopLevelDestination
) {
	TodayNavigationScaffold(currentScreen = currentScreen, navigateToScreen = {})
}