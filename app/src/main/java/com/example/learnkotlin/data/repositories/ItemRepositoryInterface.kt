package com.example.learnkotlin.data.repositories

import com.example.learnkotlin.data.models.Item

interface ItemRepositoryInterface {
    fun save(item: Item) : Item
    fun delete(item: Item) : Item
    fun findByItem(item: Item) : Item
    fun list(): List<Item>
    fun list(page: Int?): List<Item>
}