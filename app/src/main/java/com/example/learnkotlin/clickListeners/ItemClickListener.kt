package com.example.learnkotlin.clickListeners

import com.example.learnkotlin.data.models.Item

interface ItemClickListener {
    fun onDelete(item: Item)
    fun onEdit(item: Item)

}