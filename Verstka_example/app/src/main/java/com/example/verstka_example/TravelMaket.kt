package com.example.verstka_example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home.*

class TravelMaket : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        rv_tickets.layoutManager = LinearLayoutManager(this)
        val adapter = TicketsAdapter({ moneyItem: TicketItem -> })
        adapter.submitList(
            mutableListOf(
                TicketItem(
                    "Круиз с безвиззовой\nАнглией – 259€",
                    "7 дней в апреле 2019 года - vandrouki"
                ),
                TicketItem(
                    "Круиз с безвиззовой\nФраницией – 359€",
                    "8 дней в апреле 2019 года - vandrouki"
                ),
                TicketItem(
                    "Круиз с безвиззовой\nРашкой – 9€",
                    "5 дней в апреле 2020 года - vandrouki"
                ),
                TicketItem(
                    "Круиз с безвиззовой\nАнимеляндией – 32259€",
                    "+100500 дней в апреле 2007 года - vandrouki"
                )
            )
        )
        rv_tickets.adapter = adapter
    }
}
