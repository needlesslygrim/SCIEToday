package dev.scie.today.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import dev.scie.today.ui.navigation.TopLevelDestination
import dev.scie.today.ui.theme.SCIETodayTheme


@Composable
fun TodayNavigationBar(
	navigateToScreen: (TopLevelDestination) -> Unit,
	currentScreen: TopLevelDestination,
) {
	NavigationBar {
		TopLevelDestination.entries.forEach { topLevelDestination ->
			val selected = currentScreen == topLevelDestination
			NavigationBarItem(
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
				label = { Text(text = stringResource(topLevelDestination.screenNameId)) }
			)
		}
	}
}

class TodayNavigationBarPreviewParameterProvider : PreviewParameterProvider<TopLevelDestination> {
	override val values: Sequence<TopLevelDestination> = TopLevelDestination.entries.asSequence()
}

@Preview
@PreviewLightDark
@PreviewScreenSizes
@Composable
private fun TodayNavigationBarPreview(
	@PreviewParameter(TodayNavigationBarPreviewParameterProvider::class) currentScreen: TopLevelDestination
) {
	SCIETodayTheme {
		TodayNavigationBar(navigateToScreen = {}, currentScreen = currentScreen)
	}
}

