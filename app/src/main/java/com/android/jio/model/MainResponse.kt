package com.android.jio.model

import com.android.jio.cache.ResponseEntity

data class MainResponse(
    val data: List<Data> = emptyList(),
    val page: Int = 0,
    val per_page: Int = 0,
    val support: Support? = null,
    val total: Int = 0,
    val total_pages: Int = 0
)

fun MainResponse.toResponseEntity(): ResponseEntity {
    return ResponseEntity(
        data = this.data,
        page = this.page
    )
}