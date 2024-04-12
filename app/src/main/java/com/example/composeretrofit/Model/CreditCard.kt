package com.example.composeretrofit.Model

data class CreditCard(
    val id: Int,
    val uid: String,
    val name: String,
    val credit_card_number: String,
    val credit_card_expiry_date: String,
    val credit_card_type: String
) {
}