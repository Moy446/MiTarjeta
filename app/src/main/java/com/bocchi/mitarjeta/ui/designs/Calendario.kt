package com.bocchi.mitarjeta.ui.designs

import android.graphics.Color.WHITE
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bocchi.mitarjeta.CalendarInput
import com.bocchi.mitarjeta.R
import com.bocchi.mitarjeta.ui.theme.stroke
import org.intellij.lang.annotations.JdkConstants.CalendarMonth
private const val CALENDAR_ROWS = 5
private const val CALENDAR_COLUMNS = 7

@Composable
fun Calendar(modifier: Modifier = Modifier,
             calendarImput: List<CalendarInput>,
             onDayClick : (Int)->Unit,
             strokeWidth:Float = 15f,
             month: String){
    Column (
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        Text(
            text = month,
            fontWeight = FontWeight.SemiBold,
            color = Color(WHITE),
            fontSize = 40.sp
        )
        Canvas(
            modifier = Modifier.fillMaxSize()
        ){
            val canvasHeigth= size.height
            val canvasWeight = size.width
            val ysteps = canvasHeigth/ CALENDAR_ROWS
            val xsteps = canvasWeight/ CALENDAR_COLUMNS

            drawRoundRect(
                color = Color(0xFF3058B6),//Color
                cornerRadius = CornerRadius(25f, 25f),
                style = Stroke(
                    width = strokeWidth
                )
            )
        }
    }
}