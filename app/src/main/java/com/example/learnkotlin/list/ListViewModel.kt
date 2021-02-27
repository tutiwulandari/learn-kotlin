package com.example.learnkotlin.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.learnkotlin.data.models.Item
import com.example.learnkotlin.data.repositories.ItemRepositoryInterface

class ListViewModel(private val repositoryInterface: ItemRepositoryInterface): ViewModel() {
    private var _itemsLiveData = MutableLiveData<List<Item>>()

    val itemsLiveData : LiveData<List<Item>>
    get() {
        loadItemData()
        return _itemsLiveData
    }
    private fun loadItemData() {
        _itemsLiveData.value = repositoryInterface.list()
    }

}