package com.bocchi.mitarjeta.ui.designs

import android.graphics.Color.WHITE
import android.graphics.Color.toArgb
//import android.graphics.Paint
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bocchi.mitarjeta.CalendarInput
import com.bocchi.mitarjeta.R
import com.bocchi.mitarjeta.ui.theme.stroke
import org.intellij.lang.annotations.JdkConstants.CalendarMonth
private const val CALENDAR_ROWS = 5
private const val CALENDAR_COLUMNS = 7


class Calendario : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContent{
            val calendarInputList by  remember{
                mutableStateOf(createCalendarList())
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x57AD96)), //REVISAR
                contentAlignment = Alignment.TopCenter
            ){
                Calendar(
                    calendarImput = calendarInputList,
                    onDayClick = {

                    },
                    month = "Abril", //MODIFICAR
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .aspectRatio(1.3f)
                )
            }
        }
    }
    private fun createCalendarList() : List<CalendarInput>{
        val calendarImputs = mutableListOf<CalendarInput>()
        for (i in 1 .. 31){
            calendarImputs.add(
                CalendarInput(
                    i,
                    toDos = listOf(
                        "Day $i:",
                        "2p.m. Cita"
                    )
                )
            )
        }
        return calendarImputs
    }
}
@Composable
fun Calendar(modifier: Modifier = Modifier,
             calendarImput: List<CalendarInput>,
             onDayClick : (Int)->Unit,
             strokeWidth:Float = 15f,
             month: String) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = month,
            fontWeight = FontWeight.SemiBold,
            color = Color(WHITE),
            fontSize = 40.sp
        )
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val canvasHeigth = size.height
            val canvasWeight = size.width
            val ySteps = canvasHeigth / CALENDAR_ROWS
            val xSteps = canvasWeight / CALENDAR_COLUMNS

            drawRoundRect(
                color = Color(0xFF3058B6),//Color
                cornerRadius = CornerRadius(25f, 25f),
                style = Stroke(
                    width = strokeWidth
                )
            )
            for (i in 1 until CALENDAR_ROWS) {
                drawLine(
                    color = Color(0xFF3058B6),//Color
                    start = Offset(0f, ySteps*i),
                    end = Offset(canvasWeight, ySteps*i),
                    strokeWidth = strokeWidth
                )
            }
            for (i in 1 until CALENDAR_COLUMNS) {
                drawLine(
                    color = Color(0xFF3058B6),//Color
                    start = Offset(xSteps*i, 0f),
                    end = Offset(xSteps*i, canvasHeigth),
                    strokeWidth = strokeWidth
                )
            }
            val textHeight = 17.dp.toPx()
            for(i in calendarImput.indices){
                val textPositionX = xSteps * (i% CALENDAR_COLUMNS) + strokeWidth
                val textPositionY = (i / CALENDAR_COLUMNS) * ySteps + textHeight + strokeWidth/2
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        "${i+1}",
                        textPositionX,
                        textPositionY,
                        android.graphics.Paint().apply {
                            textSize = textHeight
                            //color = Color.White//Color
                           // color = white.toArgb
                            isFakeBoldText = true
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun createCalendarList() : List<CalendarInput>{
    val calendarImputs = mutableListOf<CalendarInput>()
    for (i in 1 .. 31){
        calendarImputs.add(
            CalendarInput(
                i,
                toDos = listOf(
                    "Day $i:",
                    "2p.m. Cita"
                )
            )
        )
    }
    return calendarImputs
}