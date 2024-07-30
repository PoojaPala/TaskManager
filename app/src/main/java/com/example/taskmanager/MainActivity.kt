package com.example.taskmanager

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up the Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Show the default fragment (AddTaskFragment)
        if (savedInstanceState == null) {
            loadFragment(AddTaskFragment())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val fragment: Fragment = when (item.itemId) {
            R.id.action_add -> AddTaskFragment()
            R.id.action_view -> ViewTasksFragment()
            else -> AddTaskFragment()  // Default to AddTaskFragment
        }
        loadFragment(fragment)
        return true
    }


    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}



