package dev.scie.today.baselineprofile

import androidx.benchmark.macro.BaselineProfileMode
import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
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
 * This test class benchmarks the speed of app startup.
 * Run this benchmark to verify how effective a Baseline Profile is.
 * It does this by comparing [CompilationMode.None], which represents the app with no Baseline
 * Profiles optimizations, and [CompilationMode.Partial], which uses Baseline Profiles.
 *
 * Run this benchmark to see startup measurements and captured system traces for verifying
 * the effectiveness of your Baseline Profiles. You can run it directly from Android
 * Studio as an instrumentation test, or run all benchmarks for a variant, for example benchmarkRelease,
 * with this Gradle task:
 * ```
 * ./gradlew :baselineprofile:connectedBenchmarkReleaseAndroidTest
 * ```
 *
 * You should run the benchmarks on a physical device, not an Android emulator, because the
 * emulator doesn't represent real world performance and shares system resources with its host.
 *
 * For more information, see the [Macrobenchmark documentation](https://d.android.com/macrobenchmark#create-macrobenchmark)
 * and the [instrumentation arguments documentation](https://d.android.com/topic/performance/benchmarking/macrobenchmark-instrumentation-args).
 **/
@RunWith(AndroidJUnit4::class)
@LargeTest
class StartupBenchmarks {

	@get:Rule
	val rule = MacrobenchmarkRule()

	@Test
	fun startupCompilationNone() =
		benchmark(CompilationMode.None())

	@Test
	fun startupCompilationBaselineProfiles() =
		benchmark(CompilationMode.Partial(BaselineProfileMode.Require))

	private fun benchmark(compilationMode: CompilationMode) {
		// The application id for the running build variant is read from the instrumentation arguments.
		rule.measureRepeated(
			packageName = InstrumentationRegistry.getArguments().getString("targetAppId")
				?: throw Exception("targetAppId not passed as instrumentation runner arg"),
			metrics = listOf(StartupTimingMetric(), FrameTimingMetric()),
			compilationMode = compilationMode,
			startupMode = StartupMode.COLD,
			iterations = 5,
			setupBlock = {
				pressHome()
			},
			measureBlock = {
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
		)
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
