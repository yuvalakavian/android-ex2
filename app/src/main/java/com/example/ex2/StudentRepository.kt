package com.example.ex2

object StudentRepository {
    private val students = mutableListOf<Student>()

    fun getStudents(): List<Student> = students

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun removeStudent(student: Student) {
        students.remove(student)
    }
}