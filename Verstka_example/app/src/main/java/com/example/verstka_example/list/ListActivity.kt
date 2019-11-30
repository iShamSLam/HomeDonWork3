package com.example.verstka_example.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.verstka_example.R
import com.example.verstka_example.data.Superhero
import com.example.verstka_example.details.DetailsActivity
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.item_superhero.view.*

class ListActivity : AppCompatActivity() {
    private val viewModel = ListViewModel()
    private lateinit var adapter: SupeheroesAdapter
    private var animateDown = true
    private var animateUp = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        rv_superheroes.layoutManager = LinearLayoutManager(this)
        addOnScrollListener()
        adapter = SupeheroesAdapter { superhero, view ->
            val activityOptionsCompatActivity = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                Pair(view.iv_photo as View, "ivPhoto"), Pair(view.tv_name as View, "tvName")
            )
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("tvName", superhero.name)
            intent.putExtra("ivPhoto", superhero.photoUrl)
            startActivity(intent, activityOptionsCompatActivity.toBundle())
        }
        viewModel.getList()
        observeList()
    }

    private fun addOnScrollListener() {
        rv_superheroes.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    animateDown = true
                    animateUp = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && animateDown) {
                    tv_title.apply {
                        alpha = 0f
                        visibility = View.VISIBLE
                        animate()
                            .alpha(1f)
                            .setListener(null)
                            .duration =
                            resources.getInteger(android.R.integer.config_shortAnimTime)
                                .toLong()

                    }
                    animateDown = false
                } else if (dy < 0 && animateUp) {
                    tv_title.apply {
                        alpha = 1f
                        animate()
                            .alpha(0f)
                            .setListener(null)
                            .duration =
                            resources.getInteger(android.R.integer.config_shortAnimTime)
                                .toLong()
                    }
                    animateUp = false

                }
            }
        })
    }

    private fun observeList() {
        viewModel.superheroesLiveData.observe(this, Observer {
            adapter.submitList(it as List<Superhero>?)
            rv_superheroes.adapter = adapter
        })
    }
}
