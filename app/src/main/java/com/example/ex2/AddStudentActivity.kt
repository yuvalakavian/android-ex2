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
        val button_cancel = findViewById<Button>(R.id.edit_student_details_activity_button_cancel)
        val edit_text_id = findViewById<EditText>(R.id.edit_student_details_activity_edittext_id)
        val edit_text_name = findViewById<EditText>(R.id.edit_student_details_activity_edittext_name)
        val edit_text_address = findViewById<EditText>(R.id.edit_student_details_activity_edittext_address)
        val edit_text_phone = findViewById<EditText>(R.id.edit_student_details_activity_edittext_phone)
        val checkbox_checked = findViewById<CheckBox>(R.id.student_details_activity_checkbox_checked)

        button_add_student.setOnClickListener {
            val id = edit_text_id.text.toString().trim()
            val name = edit_text_name.text.toString().trim()
            val address = edit_text_address.text.toString().trim()
            val phone = edit_text_phone.text.toString().trim()
            val checkboxCheckedVal = checkbox_checked.isChecked

            if (id.isEmpty() || name.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            StudentRepository.addStudent(Student(id, name, checkboxCheckedVal, address, phone))
            Toast.makeText(this, "Student added successfully!", Toast.LENGTH_SHORT).show()
            finish()
        }

        button_cancel.setOnClickListener {
            finish()
        }
    }
}
