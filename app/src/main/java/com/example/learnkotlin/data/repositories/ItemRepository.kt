package com.example.learnkotlin.data.repositories

import com.example.learnkotlin.data.models.Item
import java.util.*

class ItemRepository : ItemRepositoryInterface {
    companion object {
        var itemList = arrayListOf(
            Item(
                UUID.randomUUID().toString(),
                "1/1/2020",
                "123",
                123,
                "note"
            ),
            Item(
                UUID.randomUUID().toString(),
                "2/1/2020",
                "123",
                123,
                "note"
            ),
            Item(
                UUID.randomUUID().toString(),
                "1/3/2020",
                "123",
                123,
                "note"
            )
        )
    }

    override fun save(items: Item): Item {
        if (items.id == "") {
            items.id = UUID.randomUUID().toString()
            itemList.add(items)
        } else {
            val item = itemList.filter {
                it.id == items.id
            }
            val index = itemList.indexOf(item.single())
            itemList[index] = items
        }
        return items
    }

    override fun delete(item: Item): Item {
       val index = itemList.indexOf(item)
        itemList.removeAt(index)
        return item
    }

    override fun findByItem(item: Item): Item {
        itemList?.get(itemList.indexOf(item))
        return item
    }

    override fun list(): List<Item> = itemList
}
