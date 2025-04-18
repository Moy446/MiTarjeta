package com.bocchi.mitarjeta


data class CalendarInput(
    val day: Int,
    val toDos: List<String> = emptyList())