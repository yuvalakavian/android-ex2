package com.example.ex2.Model
import java.io.Serializable

data class Student(
    var id: String,
    var name: String,
    var isChecked: Boolean,
    var address: String,
    var phoneNumber: String
) : Serializable
