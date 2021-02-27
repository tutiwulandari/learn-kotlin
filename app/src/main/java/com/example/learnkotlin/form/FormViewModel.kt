package com.example.learnkotlin.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.learnkotlin.data.models.Item
import com.example.learnkotlin.data.repositories.ItemRepository
import com.example.learnkotlin.data.repositories.ItemRepositoryInterface

class FormViewModel(val repository: ItemRepository) : ViewModel() {

    private var _itemLiveData = MutableLiveData<Item>()

    val itemLiveData : LiveData<Item>
    get() {
        return _itemLiveData
    }

     fun save(item: Item) {
        _itemLiveData.value = repository.save(item)
    }
}