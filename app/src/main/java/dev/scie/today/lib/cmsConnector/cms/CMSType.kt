package dev.scie.today.lib.cmsConnector.cms

import dev.scie.today.lib.cmsConnector.UserCredentials

/**
 * Most types from the CMS API should implement this interface, to provide a consistent conversion method. This is
 * "required" because the CMS API doesn't always have very good type definitions. However, if the original type is
 * sane, like [UserCredentials], then it is not necessary.
 * @see CMSType.toTodayType
 *
 */
internal interface CMSType<T> {
	/**
	 * Converts this CMS type into its equivalent SCIEToday type.
	 */
	fun toTodayType(): T
}