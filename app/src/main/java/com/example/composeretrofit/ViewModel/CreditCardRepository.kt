package com.example.composeretrofit.ViewModel

import com.example.composeretrofit.Model.CreditCard
import com.example.composeretrofit.Service.CreditCardService
import com.example.composeretrofit.Service.RetrofitInstance

class CreditCardRepository {
    private val creditCardService:CreditCardService = RetrofitInstance.creditCardService

    suspend fun getCreditCards(): CreditCard {
        return creditCardService.getCreditCards()
    }
}