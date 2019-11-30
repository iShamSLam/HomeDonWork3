package com.example.verstka_example.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.verstka_example.R
import com.example.verstka_example.data.Superhero
import kotlinx.android.synthetic.main.item_superhero.view.*

class SupeheroesAdapter(private val clickListener: (Superhero, View) -> Unit) :
    ListAdapter<Superhero, SupeheroesAdapter.Holder>(SuperheroDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(
            inflater.inflate(
                R.layout.item_superhero,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(superhero: Superhero, clickListener: (Superhero, View) -> Unit) {
            itemView.tv_name.text = superhero.name
            Glide.with(itemView)
                .load(superhero.photoUrl)
                .into(itemView.iv_photo)

            itemView.setOnClickListener {
                clickListener(superhero, it)
            }
        }
    }

    class SuperheroDiffCallback : DiffUtil.ItemCallback<Superhero>() {
        override fun areItemsTheSame(oldItem: Superhero, newItem: Superhero): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Superhero, newItem: Superhero): Boolean {
            return oldItem == newItem
        }
    }
}



