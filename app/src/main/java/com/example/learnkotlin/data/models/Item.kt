package com.example.learnkotlin.data.models

import java.util.*

data class Item (
    var id: String,
    var date: Date,
    var itemName: String,
    var quantity : Int,
    var note : String
)