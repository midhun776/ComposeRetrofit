package com.example.composeretrofit.Service

import com.example.composeretrofit.Model.CreditCard
import retrofit2.http.GET

interface CreditCardService {

    @GET("e12457ca-2948-4b2f-b248-35114e795de0?api_key=eQGfOTUJFdbRBqNyN7zKFw")
    suspend fun getCreditCards(): CreditCard
}