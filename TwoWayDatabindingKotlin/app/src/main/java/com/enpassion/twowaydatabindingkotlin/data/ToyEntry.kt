package com.enpassion.twowaydatabindingkotlin.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "toys")
data class ToyEntry(
    var toyName: String,
    var categories: Map<String, Boolean>,
    var gender: Int = 0,
    var state: Int = 0,
    @PrimaryKey(autoGenerate = true) val toyId: Int = 0
){

    /*This function is needed for a healthy comparison of two items,
    particularly for detecting changes in the contents of the map.
    Native copy method of the data class assign a map with same reference
    to the copied item, so equals() method cannot detect changes in the content.*/
    fun copy() : ToyEntry{
        val newCategories = mutableMapOf<String, Boolean>()
        newCategories.putAll(categories)
        return ToyEntry(toyName, newCategories, gender, state, toyId)
    }
}

