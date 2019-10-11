package com.example.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var data = ArrayList<Float>()
        data.add(3.15f)
        data.add(12f)
        data.add(13f)
        data.add(20f)
        chart.setData(data)
    }
}
