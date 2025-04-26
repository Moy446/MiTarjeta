package com.bocchi.mitarjeta.ui.designs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bocchi.mitarjeta.database.SQLiteHelperCitas
import com.bocchi.mitarjeta.ui.theme.backgroud
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


@Composable
fun CustomCalendar(
    modifier: Modifier = Modifier,
    onDateSelected: (LocalDate) -> Unit = {}
) {
    val sqLiteHelperCitas = SQLiteHelperCitas(LocalContext.current)

    val today = remember { LocalDate.now() }
    val selectedDay  = remember { mutableStateOf(today) }
    var currentMonth by remember { mutableStateOf(LocalDate.now().withDayOfMonth(1)) }

    //obtener fechas
    val citas = remember { mutableStateListOf<String>().apply { addAll((sqLiteHelperCitas.getCitas())) } }

    val firstDayOfMonth = currentMonth.withDayOfMonth(1)
    val daysInMonth = currentMonth.lengthOfMonth()
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7 // saber si es domingo o sabado

    val days = mutableListOf<LocalDate?>()

    repeat(firstDayOfWeek) {
        days.add(null)
    }

    for (day in 1..daysInMonth) {
        days.add(firstDayOfMonth.withDayOfMonth(day))
    }
    repeat(35-days.size){
        days.add(null)
    }

    Column(modifier = modifier.padding(16.dp).background(Color.White)) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                currentMonth = currentMonth.minusMonths(1)
            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Mes anterior")
            }

            Text(
                text = currentMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale("es"))),
                style = MaterialTheme.typography.titleMedium
            )

            IconButton(onClick = {
                currentMonth = currentMonth.plusMonths(1)
            }) {
                Icon(Icons.Default.ArrowForward, contentDescription = "Mes siguiente")
            }
        }
        Row(Modifier.fillMaxWidth().padding(vertical = 10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            listOf("D", "L", "M", "X", "J", "V", "S").forEach {
                Text(it, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Filas de días
        days.chunked(7).forEach { week ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                week.forEach { day ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(4.dp)
                            .background(
                                if (day == selectedDay.value) backgroud else if (citas.contains(day.toString())) Color(0xFFE9762B) else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable(enabled = day != null && day>today) {
                                day?.let { onDateSelected(it)
                                selectedDay.value = it}
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = day?.dayOfMonth?.toString() ?: "")
                    }
                }
            }
        }
    }
}