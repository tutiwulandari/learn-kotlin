package com.example.learnkotlin.list

import android.view.View
import android.widget.AdapterView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.learnkotlin.clickListeners.ItemClickListener
import com.example.learnkotlin.data.models.Item
import com.example.learnkotlin.databinding.CardViewItemBinding


class ListViewHolder(view: View, val itemClickListener: ItemClickListener) : RecyclerView.ViewHolder(view) {
    private val binding = CardViewItemBinding.bind(view)

    fun bind(item: Item) {
        binding.apply {
            nameTv.text = item.name
            quantityTv.text = item.quantity.toString()
            dateTv.text = item.date
            noteTv.text = item.note
            deleteItemButton.setOnClickListener{
                itemClickListener.onDelete(item)
            }
            cardItem.setOnClickListener {
                itemClickListener.onEdit(item)
            }
        }

    }
}

