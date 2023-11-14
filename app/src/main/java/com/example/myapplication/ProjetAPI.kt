package com.example.myapplication

import retrofit2.http.GET

interface ProjetAPI {
    @GET("parties")
    suspend fun getAllMatchs() : ApiResult
}