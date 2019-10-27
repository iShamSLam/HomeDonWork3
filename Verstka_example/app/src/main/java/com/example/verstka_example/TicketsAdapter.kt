package com.example.verstka_example

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_money_income.view.tv_title
import kotlinx.android.synthetic.main.item_ticket.view.*

class TicketsAdapter(private val clickListener: (TicketItem) -> Unit) :
    ListAdapter<TicketItem, TicketsAdapter.Holder>(TicketDiffCallback()) {
    lateinit var list: MutableList<TicketItem>

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(
            inflater.inflate(
                R.layout.item_ticket,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, pos: Int) {
        holder.bind(getItem(pos), clickListener)
    }

    override fun submitList(list: MutableList<TicketItem>?) {
        super.submitList(list)
        if (list != null) {
            this.list = list
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: TicketItem, clickListener: (TicketItem) -> Unit) {
            itemView.tv_title.text = item.title
            itemView.tv_subtitle.text = item.subTitle
        }
    }
}

class TicketDiffCallback : DiffUtil.ItemCallback<TicketItem>() {
    override fun areItemsTheSame(oldItem: TicketItem, newItem: TicketItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: TicketItem, newItem: TicketItem): Boolean {
        return oldItem == newItem
    }
}
