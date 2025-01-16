package com.example.ex2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ex2.Model.Student
import com.example.ex2.Model.StudentRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private var adapter: StudentsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val fab = findViewById<FloatingActionButton>(R.id.fab_add_student)

        val studentsList = StudentRepository.getStudents()
        adapter = StudentsAdapter(studentsList,{ student: Student? ->
            //Open student details
            val intent = Intent(
                this@MainActivity,
                StudentDetailsActivity::class.java
            )

            startActivity(intent)
        }, { updatedStudent: Student ->
            // Handle checkbox click
            Toast.makeText(
                this@MainActivity,
                updatedStudent.name + " check status updated",
                Toast.LENGTH_SHORT
            ).show()
        })

        recyclerView.setLayoutManager(LinearLayoutManager(this))
        recyclerView.setAdapter(adapter)

        fab.setOnClickListener {
            //Navigate to Add Student Activity
            val intent = Intent(
                this@MainActivity,
                AddStudentActivity::class.java,
            )
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val studentsList = StudentRepository.getStudents()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        adapter = StudentsAdapter(studentsList,{ student: Student? ->
        }, { updatedStudent: Student ->
            // Handle checkbox click
            Toast.makeText(
                this@MainActivity,
                updatedStudent.name + " check status updated",
                Toast.LENGTH_SHORT
            ).show()
        })

        recyclerView.setLayoutManager(LinearLayoutManager(this))
        recyclerView.setAdapter(adapter)
    }
}
