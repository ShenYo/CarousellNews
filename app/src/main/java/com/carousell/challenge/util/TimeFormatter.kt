package com.carousell.challenge.util

import org.threeten.bp.Period

/**
 * Description:
 *
 * @author Shenyo
 * @version 1.0, 2019/4/1
 */


object TimeFormatter {

    fun format(period: Period): String {
        return when {
            period.years > 0 -> {
                "${period.years} ${if (period.years > 1) "years" else "year"} ago"
            }
            period.months > 0 -> {
                "${period.months} ${if (period.months > 1) "months" else "month"} ago"
            }
            period.days / 7 < 1 -> {
                "${period.days} ${if (period.days > 1) "days" else "day"} ago"
            }
            else -> {
                "${period.days / 7} ${if ((period.days / 7) > 1) "weeks" else "week"} ago"
            }
        }
    }

}