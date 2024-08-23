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

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import dev.scie.today.R
import kotlinx.serialization.Serializable

const val TAG = "WebCMSScreen"

@Serializable
object WebCMSScreen

// TODO: Refactor to take webview out, as well as the webview toolbar.
@Composable
fun WebCMSScreen(
	modifier: Modifier = Modifier
) {
	val homeURL = "https://cms.alevel.com.cn/cms"
	
	var goBack by remember { mutableStateOf({}) }
	var goForward by remember { mutableStateOf({}) }
	var zoomIn by remember { mutableStateOf({}) }
	var zoomOut by remember { mutableStateOf({}) }
	var zoomBy: (Float) -> Unit by remember { mutableStateOf({}) }
	var goHome by remember { mutableStateOf({}) }

	// TODO: Use the cookie manager to set the cookies for CMS, so that the user doesn't need to log
	// in separately.
	var cookieManager: CookieManager? by remember { mutableStateOf(null) }

	var loadingProgress by rememberSaveable { mutableIntStateOf(0) }
	var loadingFinished by rememberSaveable { mutableStateOf(false) }
	val animatedProgress by animateFloatAsState(
		targetValue = loadingProgress / 100f,
		animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
		label = "progress bar animation"
	)

	Column(
		modifier = modifier
			.fillMaxSize()
	) {
		AnimatedVisibility(
			visible = !loadingFinished,
		) {
			LinearProgressIndicator(
				progress = { animatedProgress },

				modifier = Modifier
					.fillMaxWidth()
					.padding(horizontal = 24.dp, vertical = 4.dp)
					.testTag("webcms:loadingbar")
			)
		}

		AndroidView(
			factory = { context ->
				WebView(context).apply {
					@SuppressLint("SetJavaScriptEnabled")
					settings.javaScriptEnabled = true
					settings.builtInZoomControls = true
					settings.displayZoomControls = false
					isVerticalScrollBarEnabled = false
					isHorizontalScrollBarEnabled = false

					webViewClient = object : WebViewClient() {
						override fun shouldOverrideUrlLoading(
							view: WebView?,
							request: WebResourceRequest?
						): Boolean = false

						override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
							loadingFinished = false
						}

						override fun onPageFinished(view: WebView?, url: String?) {
							loadingProgress = 0
							loadingFinished = true
						}
					}

					webChromeClient = object : WebChromeClient() {
						override fun onProgressChanged(view: WebView?, newProgress: Int) {
							loadingProgress = newProgress
						}
					}

					loadUrl(homeURL)

					goBack = {
						if (this.canGoBack()) {
							loadingFinished = false
						}
						this.goBack()
					}
					goForward = {
						if (this.canGoForward()) {
							loadingFinished = false
						}
						this.goForward()
					}
					zoomIn = { this.zoomIn() }
					zoomOut = { this.zoomOut() }
					zoomBy = { this.zoomBy(it) }
					goHome = {
						loadingFinished = false
						this.loadUrl(homeURL)
					}
				}
			},
			update = {
				cookieManager = CookieManager.getInstance()
			},
			modifier = Modifier
				.weight(2f)
				.padding(horizontal = dimensionResource(R.dimen.padding_medium))
				.clip(MaterialTheme.shapes.small)
		)

		Row(
			horizontalArrangement = Arrangement.SpaceBetween,
			modifier = Modifier
				.fillMaxWidth()
				.padding(vertical = 8.dp, horizontal = dimensionResource(R.dimen.padding_medium))
		) {
			IconButton(
				onClick = goBack,
			) {
				Icon(
					painter = painterResource(R.drawable.ic_arrow_back),
					stringResource(R.string.navigate_web_view_back)
				)
			}

			IconButton(
				onClick = zoomOut,
			) {
				Icon(
					painter = painterResource(R.drawable.ic_zoom_out_unfilled),
					stringResource(R.string.zoom_out_web_view)
				)
			}

			IconButton(
				onClick = goHome,
			) {
				Icon(
					painter = painterResource(R.drawable.ic_home_unfilled),
					stringResource(R.string.navigate_web_view_home)
				)
			}
			IconButton(
				onClick = zoomIn,
			) {
				Icon(
					painter = painterResource(R.drawable.ic_zoom_in_unfilled),
					stringResource(R.string.navigate_web_view_home)
				)
			}

			IconButton(
				onClick = goForward,
			) {
				Icon(
					painter = painterResource(R.drawable.ic_arrow_forward),
					contentDescription = stringResource(
						id = R.string.navigate_web_view_forwards
					)
				)
			}
		}
	}
}
