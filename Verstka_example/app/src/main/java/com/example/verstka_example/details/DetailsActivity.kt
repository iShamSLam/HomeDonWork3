package com.example.verstka_example.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.verstka_example.R
import com.example.verstka_example.data.Superhero
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    private val viewModel: DetailsViewModel =
        DetailsViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        viewModel.getArguments(intent)
        observeData()
    }

    private fun observeData() {
        viewModel.superheroLiveData.observe(this, Observer {
            Glide.with(this)
                .load((it as Superhero).photoUrl)
                .into(iv_photo)
            tv_name.text = it.name
        })
    }
}