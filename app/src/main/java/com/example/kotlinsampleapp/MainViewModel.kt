package com.example.kotlinsampleapp

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinsampleapp.models.Card
import com.example.kotlinsampleapp.utils.Utils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainViewModel(private val utils: Utils): ViewModel() {
    private val mutableCards = MutableLiveData<List<Card>>()
    val cards: LiveData<List<Card>> get() = mutableCards

    fun fetchCards(context: Context) {
        val jsonString = utils.getJson(context, "cards.json")

        val gson = Gson()
        val listCardType = object: TypeToken<List<Card>>() {}.type

        val cards = gson.fromJson<List<Card>>(jsonString, listCardType)
        mutableCards.value = cards
    }
}