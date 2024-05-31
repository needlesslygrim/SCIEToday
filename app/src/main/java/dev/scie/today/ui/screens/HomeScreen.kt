package dev.scie.today.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import dev.scie.today.R
import dev.scie.today.navigation.TodayAppFunction
import dev.scie.today.ui.theme.SCIETodayTheme
import kotlinx.serialization.Serializable

@Serializable
data object HomeScreen


@Composable
fun HomeScreen(
	onClickMainCard: () -> Unit,

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
	Column(
		verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
		modifier = modifier
			.verticalScroll(rememberScrollState())
			.padding(horizontal = dimensionResource(R.dimen.padding_medium))

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
			verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
			horizontalAlignment = Alignment.CenterHorizontally,
		) {
			for (appFunctionPair in TodayAppFunction.entries.chunked(2)) {
				AppFunctionRow(appFunctionPair, onClickAppFunction)
			}
		}

		Spacer(modifier = Modifier.height(4.dp))
	}
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