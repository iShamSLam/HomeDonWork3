package com.example.verstka_example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        if (intent.getStringExtra("color") == "black") {
            setTheme(R.style.SecondaryTheme)
            setTheme(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            setTheme(R.style.AppTheme)
            setTheme(AppCompatDelegate.MODE_NIGHT_NO)
        }

        setContentView(R.layout.activity_main)
        rv_money_items.layoutManager = LinearLayoutManager(this)
        val adapter = MoneyItemAdapter { }
        adapter.submitList(
            mutableListOf(
                MoneyItem("Манга", 100, 0),
                MoneyItem("Игры", 3000, 1),
                MoneyItem("Аниме", 100500, 2),
                MoneyItem("Рыбий жир", 1000, 3),
                MoneyItem("Еда", 5000, 4),
                MoneyItem("Адажио призываетель", 10, 5),
                MoneyItem("Сладости", 1000, 5)
            )
        )
        rv_money_items.adapter = adapter
    }
}
