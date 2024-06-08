package com.rolando.casapaico.laboratoriocalificado03

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel: ViewModel() {

    val teachersList= MutableLiveData<List<TeachersResponse>>()


    val errorApi = MutableLiveData<String>()

    init {
        getAllTeachers()
    }

    private fun getAllTeachers() {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = getRetrofit().create(TeacherApi::class.java).getTeachers()
                if(call.isSuccessful) {
                    call.body()?.let {

                        teachersList.postValue(it.teachers)
                    }
                }
            } catch (e: Exception) {
                errorApi.postValue(e.message)

            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://private-effe28-tecsup1.apiary-mock.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}
