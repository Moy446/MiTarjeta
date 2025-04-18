package com.bocchi.mitarjeta.ui.designs

import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.bocchi.mitarjeta.ui.theme.backgroud
import kotlinx.coroutines.withContext

//@Preview (showBackground = true)
@Composable
fun Calendario(navController: NavController) {
    Column {
        Text(text = "Citas")
        var curp by rememberSaveable { mutableStateOf("") }
        var usuario by rememberSaveable { mutableStateOf("") }
        //  var horario

        var date by rememberSaveable {
            mutableStateOf("")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCalendarioView() {
    Box(modifier = Modifier.background(color = backgroud)) {
        Column {
            Text(text = "Citas")
            var x by remember { mutableStateOf(0) }
            var curp by rememberSaveable { mutableStateOf("") }
            var usuario by rememberSaveable { mutableStateOf("") }
            //  var horario

            var date by rememberSaveable {
                mutableStateOf("")
            }

            Scaffold(content = {
                AndroidView(factory = CalendarView(it), update = {
                    it.setOnDateChangeListener { calendarView:CalendarView, year, month, day ->
                        day = "$date - ${month + 1} -$year"
                    }
                })
            })
        }
    }
}
