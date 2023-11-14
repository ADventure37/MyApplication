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
import okhttp3.*
import java.io.IOException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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

    fun makeGetRequest() : List<Match> {
        val client = OkHttpClient()
        var matchs : List<Match> = emptyList()

        val request = Request.Builder()
            .url("http://localhost:3000/parties") // Remplacez l'URL par celle de votre API
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Gérer les erreurs de requête ici
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseData = response.body?.string()
                    // Traiter les données reçues ici
                    val matchListType = object : TypeToken<List<Match>>(){}.type
                    matchs = Gson().fromJson(responseData, matchListType)
                } else {
                    // Gérer les erreurs de réponse ici
                }
            }
        })
        return matchs
    }
}

