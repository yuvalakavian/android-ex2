package com.example.ex2

object StudentRepository {
    private val students = mutableListOf<Student>()

    fun getStudents(): List<Student> = students

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun removeStudent(studentId: String): Boolean {
        val student = students.find { it.id == studentId }
        return if (student != null) {
            students.remove(student)
            true
        } else {
            false
        }
    }

    fun updateStudent(oldId: String, updatedStudent: Student): Boolean {
        val index = students.indexOfFirst { it.id == oldId }
        return if (index != -1) {
            students[index] = updatedStudent
            true
        } else {
            false
        }
    }
}