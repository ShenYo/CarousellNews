package com.carousell.challenge.util

import org.junit.Assert
import org.junit.Test
import org.threeten.bp.Period

/**
 * Description:
 *
 * @author Shenyo
 * @version 1.0, 2019/4/1
 */


class TimeFormatterTest {

    @Test
    fun `format when period above 1 year`() {
        val period = Period.ofYears(1)
        Assert.assertEquals("1 year ago", TimeFormatter.format(period))
    }

    @Test
    fun `format when period above 2 years`() {
        val period = Period.ofYears(2)
        Assert.assertEquals("2 years ago", TimeFormatter.format(period))
    }

    @Test
    fun `format when period above 4 weeks`() {
        val period = Period.ofMonths(1)
        Assert.assertEquals("1 month ago", TimeFormatter.format(period))
    }

    @Test
    fun `format when period above 2 months`() {
        val period = Period.ofMonths(2)
        Assert.assertEquals("2 months ago", TimeFormatter.format(period))
    }

    @Test
    fun `format when period above 1 week`() {
        val period = Period.ofDays(7)
        Assert.assertEquals("1 week ago", TimeFormatter.format(period))
    }

    @Test
    fun `format when period above 2 weeks`() {
        val period = Period.ofWeeks(2)
        Assert.assertEquals("2 weeks ago", TimeFormatter.format(period))
    }

    @Test
    fun `format when period below 7 days`() {
        val period = Period.ofDays(5)
        Assert.assertEquals("5 days ago", TimeFormatter.format(period))
    }

}