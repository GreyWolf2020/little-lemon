package com.example.littlelemon.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R

val karlaFamilyRegular = FontFamily(
    Font(R.font.karla_regular)
)

val karlaFamilyXtrBold = FontFamily(
    Font(R.font.karla_extra_bold)
)

val karlaFamilyBold = FontFamily(
    Font(R.font.karla_bold)
)


val markaziFamilyNormal = FontFamily(
    Font(R.font.markazitext_regular)
)

val markaziFamilyMedium = FontFamily(
    Font(R.font.markazi_text_medium)
)


// Set of Material typography styles to start with
val Typography = Typography(

    titleLarge = TextStyle(
        fontFamily = markaziFamilyMedium,
        fontWeight = FontWeight.Medium,
        fontSize = 64.sp,
        lineHeight = 64.sp

    ),
    titleMedium = TextStyle(
        fontFamily = markaziFamilyNormal,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp,
        lineHeight = 40.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = karlaFamilyBold,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = karlaFamilyRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    bodySmall = TextStyle(
        fontFamily = karlaFamilyRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    ),
    labelMedium = TextStyle(
        fontFamily = karlaFamilyXtrBold,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp
    ),
    labelSmall = TextStyle(
        fontFamily = karlaFamilyXtrBold,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp
    )
)