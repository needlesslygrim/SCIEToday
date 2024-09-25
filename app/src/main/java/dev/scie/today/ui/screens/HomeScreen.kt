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

package dev.scie.today.ui.screens

import android.icu.util.Calendar
import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
import dev.scie.today.R
import dev.scie.today.lib.cmsConnector.House
import dev.scie.today.lib.cmsConnector.UserInformation
import dev.scie.today.navigation.TodayAppFunction
import dev.scie.today.ui.theme.SCIETodayTheme
import kotlinx.serialization.Serializable

@Serializable
data object HomeScreen


// TODO: Add more granularity, e.g. at 05:30 - 05:59 show something like "No point ging to sleep now"
@Composable
fun chooseWelcomeMessage(): String {
	return stringResource(
		when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
			in 6..11 -> R.string.good_morning
			in 18..21 -> R.string.good_evening
			in 21..23 -> R.string.good_night
			in 23..24, in 0..<6 -> R.string.go_to_sleep
			else -> R.string.welcome
		}
	)
}

@Composable
fun chooseWelcomeIcon(): Painter? {
	return painterResource(
		when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
			in 6..11 -> R.drawable.ic_clear_day_filled_40dp
			in 18..21 -> R.drawable.ic_twilight_filled_40dp
			in 21..23 -> R.drawable.ic_night_filled_40dp
			in 23..24, in 0..<6 -> R.drawable.ic_bed_filled_40dp
			else -> return null
		}
	)
}

@Composable
fun HomeScreen(
	onClickMainCard: () -> Unit,
	userInformation: UserInformation,

	onClickBulletinCard: () -> Unit,
	latestBulletinName: String,

	latestNoticeName: String,
	latestNoticeDate: String,
	onClickNoticeCard: () -> Unit,

	nextHomeworkAssignmentLabel: String?,
	nextHomeworkAssignmentDueDate: String?,
	onClickHomeworkCard: () -> Unit,

	onClickAppFunction: (TodayAppFunction) -> Unit,

	modifier: Modifier = Modifier,
) {
	val mediumPadding = dimensionResource(R.dimen.padding_medium)
	Column(
		verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
		modifier = modifier
			.verticalScroll(rememberScrollState())
			.padding(horizontal = mediumPadding)
			.testTag("home:main")
	) {
		if (currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED) {
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.fillMaxHeight()
			) {
				WelcomeCard(onClickMainCard, mediumPadding, userInformation, modifier = Modifier.weight(1f))
				Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_small)))
				AppFunctionGrid(
					onClickAppFunction,
					modifier = Modifier
						.weight(1f)
						.fillMaxHeight(),
				)
			}

			OverviewCard(
				latestBulletinName,
				onClickBulletinCard,
				latestNoticeName,
				latestNoticeDate,
				onClickNoticeCard,
				nextHomeworkAssignmentLabel,
				nextHomeworkAssignmentDueDate,
				onClickHomeworkCard,
			)
		} else {
			WelcomeCard(onClickMainCard, mediumPadding, userInformation)
			OverviewCard(
				latestBulletinName,
				onClickBulletinCard,
				latestNoticeName,
				latestNoticeDate,
				onClickNoticeCard,
				nextHomeworkAssignmentLabel,
				nextHomeworkAssignmentDueDate,
				onClickHomeworkCard
			)
			AppFunctionGrid(
				onClickAppFunction,
			)
		}
	}
}

@Composable
private fun WelcomeCard(
	onClickMainCard: () -> Unit,
	mediumPadding: Dp,
	userInformation: UserInformation,
	modifier: Modifier = Modifier,
) {
	OutlinedCard(
		onClick = onClickMainCard,
		modifier = modifier
			.height(172.dp)
			.fillMaxWidth()
	) {
		Row(
			horizontalArrangement = Arrangement.SpaceBetween,
			modifier = Modifier
				.padding(
					top = mediumPadding,
					start = mediumPadding,
					end = mediumPadding,
					bottom = 4.dp
				)
				.fillMaxWidth()
		) {
			Text(
				text = chooseWelcomeMessage(),
				style = MaterialTheme.typography.headlineMedium,
			)
			chooseWelcomeIcon()?.let {
				Icon(
					painter = it,
					contentDescription = null,
				)
			}
		}

		Text(
			text = userInformation.name,
			style = MaterialTheme.typography.titleLarge,
			modifier = Modifier.padding(
				bottom = mediumPadding,
				start = mediumPadding,
				end = mediumPadding
			)
		)

	}
}


@Composable
private fun OverviewCard(
	latestBulletinName: String,
	onClickBulletinCard: () -> Unit,
	latestNoticeName: String,
	latestNoticeDate: String,
	onClickNoticeCard: () -> Unit,
	nextHomeworkAssignmentLabel: String?,
	nextHomeworkAssignmentDueDate: String?,
	onClickHomeworkCard: () -> Unit,
	modifier: Modifier = Modifier
) {
	OutlinedCard(
		modifier = modifier
	) {
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
}


@Composable
private fun AppFunctionGrid(
	onClickAppFunction: (TodayAppFunction) -> Unit,
	modifier: Modifier = Modifier,
) {
	Column(
		verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = modifier.fillMaxSize()
	) {
		for (appFunctionPair in TodayAppFunction.entries.chunked(2)) {
			AppFunctionRow(appFunctionPair, onClickAppFunction)
		}
	}

	Spacer(modifier = Modifier.height(4.dp))
}

@Composable
fun AppFunctionRow(
	appFunctionPair: List<TodayAppFunction>,
	onClickAppFunction: (TodayAppFunction) -> Unit,
	modifier: Modifier = Modifier
) {
	val left = appFunctionPair.first()
	val right = appFunctionPair[1]
	Row(
		horizontalArrangement = Arrangement.spacedBy(8.dp),
		modifier = modifier
	) {
		AppFunctionCard(
			functionName = stringResource(left.nameId),
			functionIcon = left.icon,
			onClick = { onClickAppFunction(left) },
			modifier = Modifier.weight(1f)
		)
		AppFunctionCard(
			functionName = stringResource(right.nameId),
			functionIcon = right.icon,
			onClick = { onClickAppFunction(right) },
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
		modifier = modifier.height(52.dp)
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
@PreviewLightDark
@PreviewScreenSizes
@PreviewDynamicColors
@Composable
private fun TodayHomeScreenPreview() {
	SCIETodayTheme {
		HomeScreen(
			onClickMainCard = {},
			userInformation = UserInformation(
				id = 22901u,
				name = "Erick Howard",
				formGroup = "A1.Wood3",
				house = House.Wood
			),

			latestNoticeDate = "02-02-2024",
			latestNoticeName = "2024 SCIE Jan Newsletter",
			onClickNoticeCard = {},

			latestBulletinName = "MCS Week Day 5",
			onClickBulletinCard = {},

			nextHomeworkAssignmentLabel = null,
			nextHomeworkAssignmentDueDate = null,
			onClickHomeworkCard = {},

			onClickAppFunction = {}
		)
	}

}