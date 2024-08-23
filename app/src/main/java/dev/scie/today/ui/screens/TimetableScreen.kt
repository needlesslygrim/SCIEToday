package dev.scie.today.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import dev.scie.today.R
import dev.scie.today.dotWith
import kotlinx.serialization.Serializable

@Serializable
data object TimetableScreen

/** A subject, e.g. Computer Science.*/
// FIXME: Move out of this file.
enum class Subject(@DrawableRes val icon: Int, @StringRes val label: Int) {
	Mathematics(R.drawable.ic_calculate_unfilled, R.string.subject_mathematics),
	ComputerScience(R.drawable.ic_code_unfilled, R.string.subject_computer_science),
	Physics(R.drawable.ic_rocket_unfilled, R.string.subject_physics),
	Chemistry(R.drawable.ic_experiment_unfilled, R.string.subject_chemistry),
	Biology(R.drawable.ic_microbiology_unfilled, R.string.subject_biology),

	History(R.drawable.ic_castle_unfilled, R.string.subject_history),
	Geography(R.drawable.ic_volcano_unfilled, R.string.subject_geography),
	Psychology(R.drawable.ic_psychology_unfilled, R.string.subject_psychology),

	English(R.drawable.ic_language_unfilled, R.string.subject_english),
	Mandarin(R.drawable.ic_language_unfilled, R.string.subject_mandarin),
	Spanish(R.drawable.ic_language_unfilled, R.string.subject_spanish),
	Japanese(R.drawable.ic_language_unfilled, R.string.subject_japanese),
	French(R.drawable.ic_language_unfilled, R.string.subject_french),


	EPQ(R.drawable.ic_epq_unfilled, R.string.subject_eca),

	/* NOTE: Morning registration isn't a subject, so it doesn't have an icon, as it won't have any
	 * assessments associated with it, therefore, it won't be shown on the assessments page with an
	 * icon
	 */
	MorningRegistration(0, R.string.subject_morning_registration),

	/* NOTE: Tutorials aren't subjects, so they also don't have icons. */
	// TODO: It may be worth making `Subject` into a sealed interface/class at some point, so that
	// the type of tutorial can be stored as well.
	Tutorial(0, R.string.subject_tutorial),
}

// FIXME: Use a better type to store the time of the class and classroom.
// FIXME: Move out of this file into a `data` folder.
/** An individual lesson, which is associated with a subject, teacher, time, and classroom */
data class Lesson(
	/** The subject of the lesson, e.g. [Mathematics][Subject.Mathematics]. */
	val subject: Subject,
	/** The classroom in which the lesson takes place. */
	val classroom: String,
	/** The long name which distinguishes different class groups from each other, e.g. A1 Mathematics
	 * 1 and A1 Mathematics 2.
	*/
	val className: String,
	/** The time at which the lesson starts */
	val startTime: String,
	/** The teacher of the lesson */
	val teacher: String
)

/** Days of the week */
val days = arrayOf(
	R.string.timetable_monday,
	R.string.timetable_tuesday,
	R.string.timetable_wednesday,
	R.string.timetable_thursday,
	R.string.timetable_friday
)

// TODO: Merch with `days`.
/** Test tags for the segmented buttons for each day of the week */
val dayTestTags = arrayOf(
	"days:mon",
	"days:tue",
	"days:wed",
	"days:thur",
	"days:fri"
)

@Composable
fun TimetableScreen(
	// FIXME: Don't use nested list, create new type to hold each day.
	lessons: List<List<Lesson>>,
	modifier: Modifier = Modifier
) {
	var selectedDay by rememberSaveable { mutableIntStateOf(0) }

	Column(
		verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
		modifier = modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium))
	) {
		SingleChoiceSegmentedButtonRow(
			modifier = Modifier.fillMaxWidth()
		) {
			days.forEachIndexed { index, label ->
				SegmentedButton(
					selected = index == selectedDay,
					onClick = { selectedDay = index },
					shape = SegmentedButtonDefaults.itemShape(
						index = index,
						count = days.size
					),
					icon = {},
					modifier = Modifier.testTag(dayTestTags[index])
				) {
					Text(stringResource(label))
				}
			}
		}

		// FIXME: Use a key for the items in this list. The key could potentially be the class name,
		// since I (needlesslygrim) don't believe it's possible to have the same class more than once
		// in a single day.
		LazyColumn {
			itemsIndexed(lessons[selectedDay]) { index, it ->
				ListItem(
					headlineContent = { Text(stringResource(it.subject.label)) },
					overlineContent = { Text(it.startTime.dotWith(it.className)) },
					supportingContent = { Text(it.teacher) },
					trailingContent = { Text(it.classroom) }
				)

				if (index < lessons[selectedDay].size - 1) {
					HorizontalDivider()
				}
			}
		}
	}
}