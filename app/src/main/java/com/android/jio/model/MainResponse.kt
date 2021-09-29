package com.android.jio.model

data class MainResponse(
    val data: List<Data> = emptyList(),
    val page: Int = 0,
    val per_page: Int = 0,
    val support: Support? = null,
    val total: Int = 0,
    val total_pages: Int = 0
)