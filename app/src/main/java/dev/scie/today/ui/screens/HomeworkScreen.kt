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

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import dev.scie.today.R
import dev.scie.today.lib.dotWith
import kotlinx.serialization.Serializable

@Serializable
data object HomeworkScreen

// FIXME: Move out of this file.
data class HomeworkAssignment(
	val title: String,
	val description: String?,
	/** The long name which distinguishes different class groups from each other, e.g. A1 Mathematics
	 * 1 and A1 Mathematics 2.
	 */
	val className: String,
	// FIXME: Use a Date object.
	val dueDate: String
)

@Composable
fun HomeworkScreen(
	assignments: List<HomeworkAssignment>,
	onMarkAssignmentDone: (Int) -> Unit,
	modifier: Modifier = Modifier
) {
	LazyColumn(
		modifier = modifier.padding(
			horizontal = dimensionResource(R.dimen.padding_medium)
		)
	) {
		itemsIndexed(assignments) { index, assignment ->
			AssignmentListItem(assignment, onMarkAssignmentDone, index)

			if (index != assignments.size - 1) {
				HorizontalDivider()
			}
		}
	}
}

@Composable
fun AssignmentListItem(
	assignment: HomeworkAssignment,
	onMarkAssignmentDone: (Int) -> Unit,
	index: Int,
	modifier: Modifier = Modifier
) {
	var checked by remember { mutableStateOf(false) }

		ListItem(
			headlineContent = {
				Text(assignment.title)
			},
			overlineContent = { Text(assignment.dueDate.dotWith(assignment.className)) },
			supportingContent = {
				assignment.description?.let { description ->
					Text(description)
				}
			},
			trailingContent = {
				IconButton(onClick = { checked = !checked }) {
					Checkbox(checked = checked, onCheckedChange = { checked = !checked })
				}
			},
		)
}