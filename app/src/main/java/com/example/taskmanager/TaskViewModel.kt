package com.example.taskmanager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    private val _tasks = MutableLiveData<MutableList<String>>(mutableListOf())
    val tasks: LiveData<MutableList<String>> get() = _tasks

    fun addTask(task: String) {
        _tasks.value?.add(task)
        _tasks.value = _tasks.value
    }
}
