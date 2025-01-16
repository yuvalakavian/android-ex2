package com.example.ex2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ex2.Model.Student

class StudentsAdapter(
    private val students: List<Student>,
    private val onRowClick: (Student) -> Unit,
    private val onCheckClick: (Student) -> Unit
) : RecyclerView.Adapter<StudentsAdapter.StudentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.bind(student)
    }

    override fun getItemCount(): Int = students.size

    inner class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val item_student_textview_name: TextView = view.findViewById(R.id.item_student_textview_name)
        private val item_student_textview_id: TextView = view.findViewById(R.id.item_student_textview_id)
        private val item_student_checkbox_checked: CheckBox = view.findViewById(R.id.item_student_checkbox_checked)

        fun bind(student: Student) {
            item_student_textview_name.text = student.name
            item_student_textview_id.text = student.id
            item_student_checkbox_checked.isChecked = student.isChecked

            itemView.setOnClickListener {
                onRowClick(student)
                val context = itemView.context
                val intent = Intent(context, StudentDetailsActivity::class.java).apply {
                    putExtra("id", student.id)
                }
                context.startActivity(intent)
            }

            item_student_checkbox_checked.setOnCheckedChangeListener(null) // Avoid triggering onCheckClick during bind
            item_student_checkbox_checked.isChecked = student.isChecked
            item_student_checkbox_checked.setOnCheckedChangeListener { _, isChecked ->
                student.isChecked = isChecked
                onCheckClick(student)
            }
        }
    }

}
