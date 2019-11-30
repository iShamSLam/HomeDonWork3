package com.example.verstka_example.details

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import itis.ru.titlelist.data.Superhero

class DetailsViewModel {
    val superheroLiveData = MutableLiveData<Superhero>()

    fun getArguments(intent: Intent) {
        val superhero = Superhero(
            intent.getStringExtra("tvName"),
            intent.getStringExtra("ivPhoto")
        )
        superheroLiveData.value = superhero
    }
}