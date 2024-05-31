package dev.scie.today


/** Combine a string with another by inserting an interpunct (·) between the two strings.
 *
 * Example:
 * "12:30".dotWith("Maths") => "12:30 · Maths"
*/
fun String.dotWith(other: String): String {
	return "$this · $other"
}