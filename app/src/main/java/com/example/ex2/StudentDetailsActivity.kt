package com.example.ex2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class StudentDetailsActivity : AppCompatActivity() {
    private lateinit var studentId: String
    private lateinit var nameTextView: TextView
    private lateinit var idTextView: TextView
    private lateinit var phoneNumberTextView: TextView
    private lateinit var addressTextView: TextView
    private lateinit var checkboxChecked: CheckBox
    private var REQUEST_CODE_EDIT = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        studentId = intent.getStringExtra("id") ?: return

        nameTextView = findViewById(R.id.student_details_activity_textview_name)
        idTextView = findViewById(R.id.student_details_activity_textview_id)
        phoneNumberTextView = findViewById(R.id.student_details_activity_textview_phone)
        addressTextView = findViewById(R.id.student_details_activity_textview_address)
        checkboxChecked = findViewById(R.id.student_details_activity_checkbox_checked)

        val editButton: Button = findViewById(R.id.student_details_activity_button_edit)
        editButton.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_EDIT)
            intent.putExtra("id", studentId)
            intent.putExtra("name", nameTextView.text)
            intent.putExtra("phoneNumber", phoneNumberTextView.text)
            intent.putExtra("address", addressTextView.text)
            intent.putExtra("is_checked", checkboxChecked.isChecked)

            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        refreshDetails()
    }

    private fun refreshDetails(id: String = studentId ) {
        val student = StudentRepository.getStudents().find { it.id == id }
        student?.let {
            nameTextView.text = "Name: ${it.name}"
            idTextView.text = "ID: ${it.id}"
            phoneNumberTextView.text = "Phone Number: ${it.phoneNumber}"
            addressTextView.text = "Address: ${it.address}"
            checkboxChecked.isChecked = it.isChecked

        }
    }
}