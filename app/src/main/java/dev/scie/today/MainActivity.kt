package dev.scie.today

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import dev.scie.today.ui.TodayApp
import dev.scie.today.ui.theme.SCIETodayTheme

class MainActivity : ComponentActivity() {
	@OptIn(ExperimentalComposeUiApi::class)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()

		setContent {
			SCIETodayTheme {
				Surface(
					modifier = Modifier.fillMaxSize().semantics {
						testTagsAsResourceId = true
					},
					color = MaterialTheme.colorScheme.background
				) {
					TodayApp()
				}
			}
		}
	}
}



