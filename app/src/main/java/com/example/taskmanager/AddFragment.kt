package com.example.taskmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class AddTaskFragment : Fragment() {

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        taskViewModel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)

        val tkNameET: EditText = view.findViewById(R.id.et_task_name)
        val prioritySpinner: Spinner = view.findViewById(R.id.spinner_priority)
        val statusRadioGroup: RadioGroup = view.findViewById(R.id.radio_group_status)
        val addTaskButton: Button = view.findViewById(R.id.btn_add_task)

        val priorities = arrayOf("Low", "Medium", "High")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, priorities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        prioritySpinner.adapter = adapter

        addTaskButton.setOnClickListener {
            val taskName = tkNameET.text.toString()
            val priority = prioritySpinner.selectedItem.toString()
            val status = if (statusRadioGroup.checkedRadioButtonId == R.id.radio_pending) "Pending" else "Completed"

            // Add task to ViewModel
            taskViewModel.addTask("$taskName - $priority - $status")

            Toast.makeText(requireContext(), "Task Added", Toast.LENGTH_SHORT).show()

            // Clear fields
            tkNameET.text.clear()
            prioritySpinner.setSelection(0)
            statusRadioGroup.clearCheck()
        }

        return view
    }
}
