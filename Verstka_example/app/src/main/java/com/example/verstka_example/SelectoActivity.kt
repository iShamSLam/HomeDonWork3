package com.example.verstka_example

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_selecto.*

class SelectoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selecto)
        var intent = Intent(this, MainActivity::class.java)
        iv_white.setOnClickListener {
            intent.putExtra("color", "white")
            startActivity(intent)
            finish()
        }
        iv_black.setOnClickListener {
            intent.putExtra("color", "black")
            startActivity(intent)
            finish()
        }
    }
}
