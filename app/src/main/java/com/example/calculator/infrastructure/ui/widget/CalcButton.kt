package com.example.calculator.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalcButton (
    color: Color,
    textColor: Color,
    text: String,
    buttonTapped: () -> Unit
){
    Box(modifier = Modifier
        .padding(8.dp)
        //.width(40.dp),
        .background(color, RoundedCornerShape(30.dp))
        .clickable { buttonTapped() },
        contentAlignment = Alignment.Center
    ){
        Text(
            text = text,
            color = textColor,
            fontSize = 19.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
    }
}