package com.example.ex2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EditStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        val REQUEST_CODE_EDIT = 100

        // Retrieve intent extras
        val studentId = intent.getStringExtra("id") ?: ""
        val studentName = intent.getStringExtra("name") ?: ""
        val studentPhoneNumber = intent.getStringExtra("phoneNumber") ?: ""
        val studentAddress = intent.getStringExtra("address") ?: ""

        // Find views by ID
        val buttonCancel = findViewById<Button>(R.id.button_cancel)
        val buttonDelete = findViewById<Button>(R.id.button_delete)
        val buttonUpdateStudent = findViewById<Button>(R.id.button_save)
        val editTextId = findViewById<EditText>(R.id.editText_id)
        val editTextName = findViewById<EditText>(R.id.editText_name)
        val editTextAddress = findViewById<EditText>(R.id.editText_address)
        val editTextPhone = findViewById<EditText>(R.id.editText_phone)
        val checkboxChecked = findViewById<CheckBox>(R.id.checkbox_checked)

        // Fetch student data from repository or fallback to intent extras
        val student = StudentRepository.getStudents().find { it.id == studentId }
        if (student != null) {
            // Populate data from the repository
            editTextId.setText(student.id)
            editTextName.setText(student.name)
            editTextAddress.setText(student.address)
            editTextPhone.setText(student.phoneNumber)
        } else {
            // Populate fallback data from intent
            editTextId.setText(studentId)
            editTextName.setText(studentName)
            editTextAddress.setText(studentAddress)
            editTextPhone.setText(studentPhoneNumber)
        }

        // Cancel button
        buttonCancel.setOnClickListener {
            finish()
        }

        // Remove student functionality
        buttonDelete.setOnClickListener {
            if (student != null) {
                StudentRepository.removeStudent(student.id)
                Toast.makeText(this, "Student removed successfully!", Toast.LENGTH_SHORT).show()

                // Navigate to MainActivity and clear the back stack
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            } else {
                Toast.makeText(this, "Student not found!", Toast.LENGTH_SHORT).show()
            }
        }

        // Update student functionality
        buttonUpdateStudent.setOnClickListener {
            val id = editTextId.text.toString().trim()
            val name = editTextName.text.toString().trim()
            val address = editTextAddress.text.toString().trim()
            val phone = editTextPhone.text.toString().trim()
            val checkbox_checked_val =  checkboxChecked.isChecked()

            if (id.isEmpty() || name.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            StudentRepository.updateStudent(studentId, Student(id, name, checkbox_checked_val, address, phone))
            Toast.makeText(this, "Student added successfully!", Toast.LENGTH_SHORT).show()

            val resultIntent = Intent()
            resultIntent.putExtra("updatedStudentId", id)
            setResult(RESULT_OK, resultIntent)

            // Finish the activity
            finish()        }
    }
}