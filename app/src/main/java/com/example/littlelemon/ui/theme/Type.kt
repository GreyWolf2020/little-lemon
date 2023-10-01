package com.example.littlelemon.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R

val karlaFamily = FontFamily(
    Font(R.font.karla_regular)
)


val markaziTextFamily = FontFamily(
    Font(R.font.markazitext_regular)
)

// Set of Material typography styles to start with
val Typography = Typography(

    titleLarge = TextStyle(
        fontFamily = markaziTextFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 64.sp,
        lineHeight = 64.sp

    ),
    titleMedium = TextStyle(
        fontFamily = markaziTextFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp,
        lineHeight = 40.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = karlaFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = karlaFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    bodySmall = TextStyle(
        fontFamily = karlaFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    ),
    labelMedium = TextStyle(
        fontFamily = karlaFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp
    ),
    labelSmall = TextStyle(
        fontFamily = karlaFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp
    )
)