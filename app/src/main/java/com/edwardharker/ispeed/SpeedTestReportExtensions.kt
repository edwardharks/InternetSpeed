package com.edwardharker.ispeed

import fr.bmartel.speedtest.SpeedTestReport
import java.math.BigDecimal

private val MEGA_BITS_PER_BIT = BigDecimal(1e+6)

val SpeedTestReport.transferRateMegaBit: BigDecimal
    get() = transferRateBit / MEGA_BITS_PER_BIT