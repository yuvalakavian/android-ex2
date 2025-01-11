package com.example.ex2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class AddStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val button_add_student = findViewById<Button>(R.id.button_add_student)
        val edit_text_id = findViewById<EditText>(R.id.editText_id)
        val edit_text_name = findViewById<EditText>(R.id.editText_name)


        button_add_student.setOnClickListener {
            val id = edit_text_id.text.toString().trim()
            val name = edit_text_name.text.toString().trim()

            if (name.isEmpty() || id.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            StudentRepository.addStudent(Student(name, id, false))
            Toast.makeText(this, "Student added successfully!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
