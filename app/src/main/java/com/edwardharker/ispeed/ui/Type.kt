package com.edwardharker.ispeed.ui

import androidx.ui.material.Typography
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.text.font.FontFamily.Companion.Default
import androidx.ui.text.font.FontFamily.Companion.Monospace
import androidx.ui.text.font.FontWeight
import androidx.ui.text.font.FontWeight.Companion.Normal
import androidx.ui.unit.sp

// Set of Material typography styles to start with
val typography = Typography(
    defaultFontFamily = Monospace,
    body1 = TextStyle(
        fontFamily = Default,
        fontWeight = Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
button = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W500,
    fontSize = 14.sp
),
caption = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp
)
*/
)
