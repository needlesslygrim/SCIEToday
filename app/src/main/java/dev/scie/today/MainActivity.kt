package dev.scie.today

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.scie.today.ui.theme.SCIETodayTheme

class MainActivity : ComponentActivity() {
	@OptIn(ExperimentalMaterial3Api::class)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			SCIETodayTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
				) {
					LoginScreen(modifier = Modifier.fillMaxSize())
				}
			}
		}
	}
}

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
	Column(
		verticalArrangement = Arrangement.SpaceBetween,
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = modifier
			.padding(24.dp)
			.fillMaxHeight()
	) {
		// Logo, name of the app, and a short tagline.
		Column(
			horizontalAlignment = Alignment.Start,
			modifier = Modifier.fillMaxWidth()
		) {
			Text(
				text = stringResource(R.string.app_name),
				style = MaterialTheme.typography.displayMedium
			)
			Text(
				text = stringResource(R.string.app_tagline),
				style = MaterialTheme.typography.headlineMedium
			)
		}

		// Username and password text fields.
		Column(
			verticalArrangement = Arrangement.spacedBy(4.dp),
		) {
			var username by remember { mutableStateOf("") }
			var password by remember { mutableStateOf("") }
			var showPassword by remember { mutableStateOf(false) }
			OutlinedTextField(
				value = username,
				singleLine = true,
				prefix = { Text(text = "s") },
				label = { Text(text = "Username") },
				onValueChange = { username = it },
				keyboardOptions = KeyboardOptions(
					imeAction = ImeAction.Next,
					keyboardType = KeyboardType.Number
				),
				modifier = Modifier.fillMaxWidth()
			)

			OutlinedTextField(
				value = password,
				onValueChange = { password = it },
				singleLine = true,
				label = { Text(text = "Password") },
				keyboardOptions = KeyboardOptions(
					imeAction = ImeAction.Done,
					keyboardType = KeyboardType.Password
				),
				trailingIcon = {
					IconButton(
						onClick = { showPassword = !showPassword },
					) {
						Icon(
							painter = painterResource(
								id = if (showPassword) {
									R.drawable.ic_visibility_filled
								} else {
									R.drawable.ic_visibility_off_filled
								}
							),
							contentDescription = null,
						)
					}
				},
				visualTransformation = if (!showPassword) {
					PasswordVisualTransformation()
				} else {
					VisualTransformation.None
				},
				modifier = Modifier.fillMaxWidth()
			)
		}
		Column {
			Row(
				horizontalArrangement = Arrangement.SpaceBetween,
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier.fillMaxWidth()
			) {
				var rememberMe by remember { mutableStateOf(false) }
				Text(text = stringResource(id = R.string.remember_me))
				Checkbox(checked = rememberMe, onCheckedChange = { rememberMe = it })
			}
			Button(
				onClick = { /*TODO*/ },
				modifier = Modifier.fillMaxWidth()
			) {
				Text(text = stringResource(id = R.string.login))
			}
		}
	}
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun LoginScreenPreview() {
	LoginScreen()
}