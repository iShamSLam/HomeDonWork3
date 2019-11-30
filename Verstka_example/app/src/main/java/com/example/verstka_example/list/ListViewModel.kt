package com.example.verstka_example.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import itis.ru.titlelist.data.DataHelper
import itis.ru.titlelist.data.Superhero

class ListViewModel: ViewModel() {
    val superheroesLiveData = MutableLiveData<MutableList<Superhero>>()

    fun getList(){
        superheroesLiveData.value = DataHelper.getSuperhoresList()
    }
}