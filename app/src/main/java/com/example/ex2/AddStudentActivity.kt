package com.example.ex2

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class AddStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val button_add_student = findViewById<Button>(R.id.button_add_student)
        val button_cancel = findViewById<Button>(R.id.button_cancel)
        val activity_add_student_edittext_id = findViewById<EditText>(R.id.activity_add_student_edittext_id)
        val activity_add_student_edittext_name = findViewById<EditText>(R.id.activity_add_student_edittext_name)
        val activity_add_student_edittext_address = findViewById<EditText>(R.id.activity_add_student_edittext_address)
        val activity_add_student_edittext_phone = findViewById<EditText>(R.id.activity_add_student_edittext_phone)
        val activity_add_student_checkbox_checked = findViewById<CheckBox>(R.id.activity_add_student_checkbox_checked)

        button_add_student.setOnClickListener {
            val id = activity_add_student_edittext_id.text.toString().trim()
            val name = activity_add_student_edittext_name.text.toString().trim()
            val address = activity_add_student_edittext_address.text.toString().trim()
            val phone = activity_add_student_edittext_phone.text.toString().trim()
            val checkbox_checked_val = activity_add_student_checkbox_checked.isChecked

            if (id.isEmpty() || name.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            StudentRepository.addStudent(Student(id, name, checkbox_checked_val, address, phone))
            Toast.makeText(this, "Student added successfully!", Toast.LENGTH_SHORT).show()
            finish()
        }

        button_cancel.setOnClickListener {
            finish()
        }
    }
}
