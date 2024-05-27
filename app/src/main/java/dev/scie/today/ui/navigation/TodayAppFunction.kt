package dev.scie.today.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import dev.scie.today.R

/** A function of the app, e.g. showing CMS in a WebView. These are displayed in a grid on the
 * home screen, and can be opened.
 */
enum class TodayAppFunction(
	/** Resource ID of the icon shown on the home screen grid. */
	@DrawableRes val icon: Int,
	/** The name of a function, shown to the user on both the home screen and in app bars. */
	@StringRes val nameId: Int
) {
	/** Show CMS in a web view. */
	WEB_CMS(R.drawable.ic_web_cms_unfilled, R.string.web_cms),
	EXAM_TIMETABLE(R.drawable.ic_hourglass_empty_unfilled, R.string.exam_timetable),
	ATTENDANCE(R.drawable.ic_attendance_unfilled, R.string.attendance),
	ANNOUNCEMENTS(R.drawable.ic_breaking_news_unfilled, R.string.announcements),
	CALENDAR(R.drawable.ic_calendar_month_unfilled, R.string.calendar),
	REPORT(R.drawable.ic_assessment_unfilled, R.string.report),
	ECA(R.drawable.ic_eca_unfilled, R.string.subject_eca),
	LEAVE(R.drawable.ic_leave_unfilled, R.string.leave);
}