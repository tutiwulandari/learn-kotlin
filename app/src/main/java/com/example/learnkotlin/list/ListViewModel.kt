package com.example.learnkotlin.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.learnkotlin.clickListeners.ItemClickListener
import com.example.learnkotlin.data.models.Item
import com.example.learnkotlin.data.repositories.ItemRepositoryInterface

class ListViewModel(private val repository: ItemRepositoryInterface): ViewModel(), ItemClickListener {
    private var _itemsLiveData = MutableLiveData<List<Item>>()
    private var _itemLiveData = MutableLiveData<Item>()

    val itemsLiveData : LiveData<List<Item>>
    get() {
        loadItemData()
        return _itemsLiveData
    }

    val itemLiveData: LiveData<Item>
        get() {
            return _itemLiveData
        }

    private fun loadItemData() {
        _itemsLiveData.value = repository.list()
    }


    override fun onDelete(item: Item) {
        repository.delete(item)
        loadItemData()
    }

    private fun getItemData(item: Item) {
        _itemLiveData.value = repository.findByItem(item)
    }

    override fun onEdit(item: Item) {
      getItemData(item)
    }


}