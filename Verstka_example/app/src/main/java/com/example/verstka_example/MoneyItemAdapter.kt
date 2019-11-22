package com.example.verstka_example

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_date.view.*
import kotlinx.android.synthetic.main.item_money_income.view.*
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class MoneyItemAdapter(private val clickListener: (MoneyItem) -> Unit) :
    ListAdapter<MoneyItem, MoneyItemAdapter.Holder>(DiffCallback()) {
    lateinit var list: MutableList<MoneyItem>

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(
            inflater.inflate(
                R.layout.item_money_income,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, pos: Int) {
        holder.bind(getItem(pos), clickListener)
    }

    override fun submitList(list: MutableList<MoneyItem>?) {
        super.submitList(list)
        if (list != null) {
            this.list = list
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MoneyItem, clickListener: (MoneyItem) -> Unit) {
            itemView.tv_title.text = item.title
            itemView.tv_money_count.text = " - ${item.count}"
            itemView.item_date.visibility = View.VISIBLE
            itemView.item_date.tv_date.text = getDate(item.date)
        }
    }
}

fun getDate(time: Long): String {
    try {
        val stamp = Timestamp(time)
        val date = Date(stamp.time)
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        return sdf.format(date)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return ""
}

class DiffCallback : DiffUtil.ItemCallback<MoneyItem>() {
    override fun areItemsTheSame(oldItem: MoneyItem, newItem: MoneyItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MoneyItem, newItem: MoneyItem): Boolean {
        return oldItem == newItem
    }
}
