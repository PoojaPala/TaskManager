package com.example.taskmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class ViewTasksFragment : Fragment() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view, container, false)

        taskViewModel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)

        val tasksListView: ListView = view.findViewById(R.id.lv_tasks)
        val tasks = mutableListOf<String>()
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, tasks)
        tasksListView.adapter = adapter

        // Observe the tasks LiveData and update the ListView when tasks change
        taskViewModel.tasks.observe(viewLifecycleOwner, Observer { updatedTasks ->
            tasks.clear()
            tasks.addAll(updatedTasks)
            adapter.notifyDataSetChanged()
        })

        return view
    }
}
