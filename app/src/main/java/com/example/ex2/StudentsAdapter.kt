package com.example.ex2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

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
        private val nameTextView: TextView = view.findViewById(R.id.textView_name)
        private val idTextView: TextView = view.findViewById(R.id.textView_id)
        private val checkBox: CheckBox = view.findViewById(R.id.checkBox_checked)

        fun bind(student: Student) {
            nameTextView.text = student.name
            idTextView.text = student.id
            checkBox.isChecked = student.isChecked

            itemView.setOnClickListener {
                onRowClick(student)
                val context = itemView.context
                val intent = Intent(context, StudentDetailsActivity::class.java).apply {
                    putExtra("id", student.id)
                }
                context.startActivity(intent)
            }

            checkBox.setOnCheckedChangeListener(null) // Avoid triggering onCheckClick during bind
            checkBox.isChecked = student.isChecked
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                student.isChecked = isChecked
                onCheckClick(student)
            }
        }
    }

}
