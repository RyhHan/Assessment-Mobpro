package com.andimuhammadraihansyamsu607062330113.asesment1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.andimuhammadraihansyamsu607062330113.asesment1.ui.screen.MainScreen
import com.andimuhammadraihansyamsu607062330113.asesment1.ui.theme.Asesment1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Asesment1Theme {
                MainScreen()
            }
        }
    }
}

