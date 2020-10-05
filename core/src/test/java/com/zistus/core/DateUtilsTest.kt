package com.zistus.core

import com.zistus.core.utils.DateUtils
import org.junit.Assert
import org.junit.Test

class DateUtilsTest {

    @Test
    fun todaysDateIsValid() {
        val expected = "2020-10-04T23:56"
        val actualDate = DateUtils.fetchMonthYear()

        Assert.assertEquals(expected, actualDate)
    }
}