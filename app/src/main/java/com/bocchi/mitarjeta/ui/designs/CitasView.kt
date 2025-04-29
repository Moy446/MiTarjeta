package com.bocchi.mitarjeta.ui.designs

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bocchi.mitarjeta.Cita
import com.bocchi.mitarjeta.R
import com.bocchi.mitarjeta.database.SQLiteHelperCitas
import com.bocchi.mitarjeta.navigation.NavItemList
import com.bocchi.mitarjeta.notification.Notification
import com.bocchi.mitarjeta.ui.theme.Titulos
import com.bocchi.mitarjeta.ui.theme.backgroud
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CitasView(navController: NavController) {
    var context =LocalContext.current
    val sqLiteHelperCitas = SQLiteHelperCitas(context)

    var selectedRoute = remember { mutableStateOf("citas") }

    //datos del usuario
    var curp by rememberSaveable { mutableStateOf("") }

    //comboBox
    var isExpandedLugar = remember { mutableStateOf(false) }
    var isExpandedHora = remember { mutableStateOf(false) }
    val lugaresList = remember { mutableStateListOf<String>().apply { addAll(getLugares()) } }
    var lugarSelected  by remember { mutableStateOf(lugaresList.firstOrNull() ?: "") }
    val horarioList = remember { mutableStateListOf<String>().apply { addAll(getHorario()) } }
    var horarioSelected by remember { mutableStateOf(horarioList.firstOrNull() ?: "") }

    //var fecha
    val date  = remember { mutableStateOf(LocalDate.now()) }
    Scaffold(bottomBar = {
        //Impresion del Menu
        menuView(
            navItemList = NavItemList.navItemList,
            selectedView = selectedRoute.value,
            onItemSelected = { titulo ->
                selectedRoute.value = titulo
                navController.navigate(titulo)
            },
        )
    }) {


        Box(modifier = Modifier
            .fillMaxSize()
            .background(backgroud)
            .padding(top = 40.dp, bottom = 50.dp)) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 80.dp, start = 10.dp, end = 10.dp)
                    .align(Alignment.Center)
                    .verticalScroll(rememberScrollState())
            ) {
                Row{
                    botonBack(
                        Modifier
                            .padding(30.dp, 15.dp)
                            .clickable {
                                navController.popBackStack()
                            })

                    textTittle(
                        Modifier
                            .width(150.dp)
                            .height(52.dp),
                        "Citas"
                    )
                }

                textDescription(
                    Modifier
                        .width(329.dp)
                        .height(26.dp)
                        .align(Alignment.CenterHorizontally),
                    "Seleccione un dia para su cita"
                )

                //CURP
                OutlinedTextField(
                    modifier = Modifier
                        .padding(20.dp,20.dp,20.dp,10.dp)
                        .fillMaxWidth()
                        .height(45.dp),
                    value = curp,
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.relay_niramit_medium)),
                        fontWeight = FontWeight(500),
                    ),
                    onValueChange = { curp = it },
                    placeholder = {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(25.dp),
                            text = "CURP",
                            color = Titulos,
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.relay_niramit_medium)),
                            ),
                        )
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color.White,
                        focusedBorderColor = Color.Blue,   // Borde activo
                        unfocusedBorderColor = Color.Gray, // Borde inactivo
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        keyboardType = KeyboardType.Number,
                    )
                )

                //combo box lugares
                ExposedDropdownMenuBox(
                    expanded = isExpandedLugar.value,
                    onExpandedChange = { isExpandedLugar.value = !isExpandedLugar.value }) {
                    OutlinedTextField(
                        modifier = Modifier.menuAnchor().fillMaxWidth().padding(horizontal = 20.dp, vertical = 10.dp),
                        value = lugarSelected,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedLugar.value) },
                        shape = RoundedCornerShape(20.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White,
                            focusedBorderColor = Color.Blue,
                            unfocusedBorderColor = Color.Gray,
                        )
                    )
                    ExposedDropdownMenu(
                        expanded = isExpandedLugar.value,
                        onDismissRequest = { isExpandedHora.value = false }) {
                        lugaresList.forEach { lugar ->
                            DropdownMenuItem(
                                text = { Text(lugar) },
                                onClick = {
                                    lugarSelected = lugar
                                    isExpandedLugar.value = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                            )
                        }
                    }
                }

                //comboBox horario
                ExposedDropdownMenuBox(
                    expanded = isExpandedHora.value,
                    onExpandedChange = { isExpandedHora.value = !isExpandedHora.value }) {
                    OutlinedTextField(
                        modifier = Modifier.menuAnchor().fillMaxWidth().padding(horizontal = 20.dp, vertical = 10.dp),
                        value = horarioSelected,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedHora.value) },
                        shape = RoundedCornerShape(20.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White,
                            focusedBorderColor = Color.Blue,
                            unfocusedBorderColor = Color.Gray,
                        )
                    )
                    ExposedDropdownMenu(
                        expanded = isExpandedHora.value,
                        onDismissRequest = { isExpandedHora.value = false }) {
                        horarioList.forEach { hora ->
                            DropdownMenuItem(
                                text = { Text(hora) },
                                onClick = {
                                    horarioSelected = hora
                                    isExpandedHora.value = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                            )
                        }
                    }
                }

                CustomCalendar(
                    onDateSelected = { selectedDate ->
                        date.value = selectedDate
                        curp =  date.value.toString()
                    }
                )

                //boton agendar cita
                Button(modifier = Modifier
                    .padding(vertical = 5.dp)
                    .width(301.dp)
                    .height(42.dp)
                    .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(20.dp),
                    enabled = curp != "" && lugarSelected != "lugares" && horarioSelected != "horario",
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE9762B)
                    ),
                    onClick = {
                        sqLiteHelperCitas.insertCita(Cita(date.toString(), horarioSelected,lugarSelected,curp))
                        agendarCita(Cita(date.toString(), horarioSelected,lugarSelected,curp),context)
                    }) {
                    Text(
                        text = "Agendar cita",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.relay_niramit_medium)),
                            fontWeight = FontWeight(500),
                            color = Color(0xFF3058B6),
                        )
                    )
                }
            }
        }
    }
}

@SuppressLint("ScheduleExactAlarm")
fun agendarCita(cita: Cita, context: Context){
    var fechaCita = cita.fecha.split("-")
    var horaCita = cita.horario.split(":")
    var calendar = Calendar.getInstance().apply {
        set(Calendar.YEAR,fechaCita[0].toInt())
        set(Calendar.MONTH, fechaCita[1].toInt())
        set(Calendar.DAY_OF_MONTH,fechaCita[2].toInt())
        set(Calendar.HOUR,horaCita[0].toInt())
        set(Calendar.MINUTE,horaCita[1].toInt())
    }

        val intent = Intent(context, Notification::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
}

fun getLugares():MutableList<String>{
    var lugaresList: MutableList<String> = mutableListOf("lugares")
    /*TODO
    *  REALIZAR EL SCRACCHIG PARA OBTENER LOS    LUGARES*/
    return lugaresList
}

fun getHorario():MutableList<String>{
    var horarioList: MutableList<String> = mutableListOf("horario")
    /*TODO
    *  REALIZAR EL SCRACCHIG PARA OBTENER LOS HORARIOS*/
    return horarioList
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun PreviewCalendarioView() {
    CitasView(rememberNavController())
}

