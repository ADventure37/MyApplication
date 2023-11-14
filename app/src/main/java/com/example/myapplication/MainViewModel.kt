package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

class MainViewModel : ViewModel() {
    val matchs = MutableStateFlow<List<Match>>(listOf())

    val okHttpClient = OkHttpClient.Builder()
        .callTimeout(20, TimeUnit.SECONDS) // Temps d'attente pour l'ensemble de la requête
        .build()

    val service = Retrofit.Builder()
        .baseUrl("http://localhost:3000/").client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create()).build().create(ProjetAPI::class.java)

    fun getAllMatch(){
        viewModelScope.launch {
            matchs.value = service.getAllMatchs()
        }
    }
}