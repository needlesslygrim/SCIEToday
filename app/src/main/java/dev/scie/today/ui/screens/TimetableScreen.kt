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

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.scie.today.R
import dev.scie.today.lib.cmsConnector.Timetable
import dev.scie.today.lib.cmsConnector.util.TimeRange
import dev.scie.today.lib.dotWith
import kotlinx.serialization.Serializable
import java.util.Calendar

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

	PE(R.drawable.ic_run_unfilled, R.string.subject_pe),

	EPQ(R.drawable.ic_epq_unfilled, R.string.subject_epq),

	/* NOTE: This is just used so that ECAs don't need to make `Event.subject` nullable. */
	ECA(0, R.string.subject_eca),

	/* NOTE: PSHE isn't a subject, so it doesn't have an icon. */
	PSHE(0, R.string.subject_pshe),

	/* NOTE: UCO sessions aren't a subject, so it doesn't have an icon. */
	UCO(0, R.string.UCO),

	/* NOTE: Tutorials aren't subjects, so they also don't have an icon. */
	// TODO: It may be worth making `Subject` into a sealed interface/class at some point, so that
	// the type of tutorial can be stored as well.
	Tutorial(0, R.string.subject_tutorial);
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
	"days:mon", "days:tue", "days:wed", "days:thur", "days:fri"
)

/** Stores a nullable [Timetable.Event], a [TimeRange], and whether or not it is a double
 * period. Used so that double periods can be collapsed into one listing.
*/
data class TimetableListing(val event: Timetable.Event?, val time: TimeRange, val doublePeriod: Boolean = false)

@Composable
fun TimetableScreen(
	// FIXME: Don't use nested list, create new type to hold each day.
	timetable: Timetable,
	modifier: Modifier = Modifier
) {
	val today = rememberSaveable {
		Calendar.getInstance().get(Calendar.DAY_OF_WEEK).let {
			if (it == Calendar.SUNDAY || it == Calendar.SATURDAY) {
				Calendar.MONDAY
			} else {
				it
			}
		}
	}
	var selectedDay by rememberSaveable { mutableIntStateOf(today - 2) }
	val selectedDayTimetableListings by remember {
		derivedStateOf {
			val timeSlots = when (selectedDay) {
				0 -> timetable.week.mondayTimeslots
				1 -> timetable.week.tuesdayTimeslots
				2 -> timetable.week.wednesdayTimeslots
				3 -> timetable.week.thursdayTimeslots
				4 -> timetable.week.fridayTimeslots
				else -> throw Exception("not possible")
			}

			var skip = false
			val timetableListings = mutableListOf<TimetableListing>()
			for (i in 0..<(timeSlots.size - 1)) {
				if (skip) { skip = false; continue; }
				val timeSlot = timeSlots[i]
				val nextTimeSlot = timeSlots[i + 1]
				val event = timeSlot.getEventByWeekType(timetable.week.type)
				val nextEvent = nextTimeSlot.getEventByWeekType(timetable.week.type)

				if (event == null || event.id != nextEvent?.id) {
					timetableListings.add(TimetableListing(event, timeSlot.time))
				} else  {
					skip = true
					timetableListings.add(TimetableListing(
						event = event,
						time = TimeRange(timeSlot.time.start, nextTimeSlot.time.end),
						doublePeriod = true
					))
				}
			}
			timetableListings.add(TimetableListing(
				event = timeSlots.last().getEventByWeekType(timetable.week.type),
				time = timeSlots.last().time)
			)

			timetableListings
		}
	}

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
						index = index, count = days.size
					),
					icon = {},
					modifier = Modifier.testTag(dayTestTags[index])
				) {
					Text(stringResource(label))
				}
			}
		}

		LazyColumn {
			itemsIndexed(selectedDayTimetableListings) { index, period ->
				if (period.event == null) { return@itemsIndexed }
				PeriodListing(period.event, period.time, period.doublePeriod)

				if (index < selectedDayTimetableListings.size - 1) {
					HorizontalDivider()
				}

			}
		}
	}
}

@Composable
private fun PeriodListing(
	event: Timetable.Event,
	time: TimeRange,
	doublePeriod: Boolean = false
) {
	if (event.subject != Subject.ECA) {
		ListItem(
			headlineContent = { Text(stringResource(event.subject.label)) },
			overlineContent = { Text(event.teacher.dotWith(event.name)) },
			supportingContent = { Text(time.toString()) },
			trailingContent = {
				Column(
					verticalArrangement = Arrangement.SpaceBetween,
					modifier = Modifier.height(56.dp)
				) {
					Text(event.room)
					Text(if (doublePeriod) { "2" } else { "1" })
				}
			},
		)
	} else {
		ListItem(
			headlineContent = { Text(event.name) },
			overlineContent = { Text(event.teacher.dotWith(stringResource(event.subject.label))) },
			supportingContent = { Text(time.toString()) },
			trailingContent = {
				Column(
					verticalArrangement = Arrangement.SpaceBetween,
					modifier = Modifier.height(56.dp)
				) {
					Text(event.room)
					Text(if (doublePeriod) { "2" } else { "1" })
				}
			},
		)
	}
}