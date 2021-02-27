package com.example.learnkotlin.data.models

import java.util.*

data class Item (
    var id: String,
    var date: String,
    var name: String,
    var quantity : Int,
    var note : String
)