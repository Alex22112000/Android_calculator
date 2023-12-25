package com.example.calculator.infrastructure

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculator.R
import com.example.calculator.infrastructure.ui.widget.InputSection
import com.example.calculator.infrastructure.ui.widget.OutputSection
import com.example.calculator.infrastructure.ui.widget.SwitchSection

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Calculator()
        }
    }
}

@Preview
@Composable
fun Calculator(){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.scaffoldBgColor)),
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            SwitchSection()
            OutputSection()
            InputSection()
        }
}