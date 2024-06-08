package com.rolando.casapaico.laboratoriocalificado03

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rolando.casapaico.laboratoriocalificado03.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var listTeachers: List<TeachersResponse> = emptyList()

    private val adapter by lazy {TeacherAdapter(listTeachers)}

    private lateinit var binding : ActivityMainBinding

    private val viewModel by lazy { MainViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.rvTeachers.adapter = adapter
        setContentView(binding.root)
        observeValues()
    }


    private fun observeValues() {

        viewModel.teachersList.observe(this) { teachersList ->
            adapter.list = teachersList
            adapter.notifyDataSetChanged()
        }

        viewModel.errorApi.observe(this) { errorMessage ->
            showMessage(errorMessage)
        }
    }

    private fun showMessage(message: String) {
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }



}