package com.example.ex2
import java.io.Serializable

data class Student(
    var name: String,
    var id: String,
    var isChecked: Boolean
) : Serializable