package com.example.composeretrofit.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeretrofit.Model.CreditCard
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CreditCardViewModel: ViewModel() {
    private val repository = CreditCardRepository()

    private val  _creditCards = MutableLiveData<List<CreditCard>>()

    val creditCard: LiveData<List<CreditCard>> = _creditCards

    var tempList = mutableListOf<CreditCard>()

    fun fetchCreditCards() {
        viewModelScope.launch {
            try {
                for (i in 1..5) {
                    val cards = repository.getCreditCards()
                    tempList.add(cards)
                    delay(4000)
                }

                _creditCards.value = tempList.toList()
            } catch (e: Exception) {
                Log.e("ComposeRetrofit", e.toString())
                e.printStackTrace()
            }
        }
    }
}