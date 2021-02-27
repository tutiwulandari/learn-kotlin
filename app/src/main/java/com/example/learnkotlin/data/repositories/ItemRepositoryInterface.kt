package com.example.learnkotlin.data.repositories

import com.example.learnkotlin.data.models.Item

interface ItemRepositoryInterface {
//    fun add(item: Item) : Item
//    fun delete(item: Item) : Boolean
//    fun update(item: Item) : Item
    fun list(): List<Item>

}