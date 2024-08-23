package dev.scie.today.baselineprofile

import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This test class generates a basic startup baseline profile for the target package.
 *
 * We recommend you start with this but add important user flows to the profile to improve their performance.
 * Refer to the [baseline profile documentation](https://d.android.com/topic/performance/baselineprofiles)
 * for more information.
 *
 * You can run the generator with the "Generate Baseline Profile" run configuration in Android Studio or
 * the equivalent `generateBaselineProfile` gradle task:
 * ```
 * ./gradlew :app:generateReleaseBaselineProfile
 * ```
 * The run configuration runs the Gradle task and applies filtering to run only the generators.
 *
 * Check [documentation](https://d.android.com/topic/performance/benchmarking/macrobenchmark-instrumentation-args)
 * for more information about available instrumentation arguments.
 *
 * After you run the generator, you can verify the improvements running the [StartupBenchmarks] benchmark.
 *
 * When using this class to generate a baseline profile, only API 33+ or rooted API 28+ are supported.
 *
 * The minimum required version of androidx.benchmark to generate a baseline profile is 1.2.0.
 **/
@RunWith(AndroidJUnit4::class)
@LargeTest
class BaselineProfileGenerator {

	@get:Rule
	val rule = BaselineProfileRule()

	@Test
	fun generate() {
		// The application id for the running build variant is read from the instrumentation arguments.
		rule.collect(
			packageName = InstrumentationRegistry.getArguments().getString("targetAppId")
				?: throw Exception("targetAppId not passed as instrumentation runner arg"),

			// See: https://d.android.com/topic/performance/baselineprofiles/dex-layout-optimizations
			includeInStartupProfile = true
		) {
			pressHome()
			startActivityAndWait()

			device.wait(Until.hasObject(By.res("navigation:home")), 30_000)
			val homeNavItem = device.findObject(By.res("navigation:home"))
			val timetableNavItem = device.findObject(By.res("navigation:timetable"))
			val homeworkNavItem = device.findObject(By.res("navigation:homework"))
			val gradesNavItem = device.findObject(By.res("navigation:grades"))

			timetableNavItem.click()

			device.wait(Until.hasObject(By.res("days:mon")), 30_000)
			interactWithTimetablePage(device)

			homeworkNavItem.click()
			device.waitForIdle()
			gradesNavItem.click()
			device.waitForIdle()
			homeNavItem.click()

			interactWithWebCMS(device)

			interactWithHomePage(device)
		}
	}

	private fun interactWithHomePage(device: UiDevice) {
		device.wait(Until.hasObject(By.res("home:main")), 30_000)
		val homePage = device.findObject(By.res("home:main"))
		homePage.setGestureMargin(device.displayWidth / 5)

		homePage.fling(Direction.DOWN)
		device.waitForIdle()
		homePage.fling(Direction.UP)
	}

	private fun interactWithWebCMS(device: UiDevice) {
		device.wait(Until.hasObject(By.text("Web CMS")), 30_000)
		device.findObject(By.text("Web CMS")).click()
		device.wait(Until.gone(By.res("webcms:loadingbar")), 50_000)
		device.findObject(By.res("navigation:back")).click()
	}

	private fun interactWithTimetablePage(device: UiDevice) {
		val monSegmentedButton = device.findObject(By.res("days:mon"))
		val tueSegmentedButton = device.findObject(By.res("days:tue"))

		tueSegmentedButton.click()
		monSegmentedButton.click()
	}
}