package com.example.ex2

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity

class StudentDetailsActivity : AppCompatActivity() {
    private lateinit var studentId: String
    private lateinit var textViewName: TextView
    private lateinit var textViewId: TextView
    private lateinit var textViewPhone: TextView
    private lateinit var textViewAddress: TextView
    private lateinit var checkboxChecked: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        val toolbar: Toolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)

        // Enable the back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Handle navigation click
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed() // Go back to the previous screen
        }

        // Retrieve intent extras
        studentId = intent.getStringExtra("id") ?: return

        // Find views by ID
        textViewName = findViewById(R.id.student_details_activity_textview_name)
        textViewId = findViewById(R.id.student_details_activity_textview_id)
        textViewPhone = findViewById(R.id.student_details_activity_textview_phone)
        textViewAddress = findViewById(R.id.student_details_activity_textview_address)
        checkboxChecked = findViewById(R.id.student_details_activity_checkbox_checked)

        // handle edit button
        val editButton: Button = findViewById(R.id.student_details_activity_button_edit)
        editButton.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("name", textViewName.text)
            intent.putExtra("id", studentId)
            intent.putExtra("phoneNumber", textViewPhone.text)
            intent.putExtra("address", textViewAddress.text)
            intent.putExtra("is_checked", checkboxChecked.isChecked)

            startActivity(intent)
        }
    }

    // Refreshes the student details displayed in the UI.
    private fun refreshDetails(id: String = studentId ) {
        val student = StudentRepository.getStudents().find { it.id == id }
        student?.let {
            textViewName.text = "Name: ${it.name}"
            textViewId.text = "ID: ${it.id}"
            textViewPhone.text = "Phone Number: ${it.phoneNumber}"
            textViewAddress.text = "Address: ${it.address}"
            checkboxChecked.isChecked = it.isChecked

        }
    }

    override fun onResume() {
        super.onResume()
        refreshDetails()
    }
}