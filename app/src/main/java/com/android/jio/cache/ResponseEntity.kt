package com.android.jio.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.jio.model.Data

@Entity(tableName = "response_table")
data class ResponseEntity(
    @PrimaryKey(autoGenerate = true) val _id: Long? = null,
    val data: List<Data> = emptyList(),
    val page: Int
)
