package com.github.v43d3rm4k4r.besthotels.domain.contracts

import java.util.*

/**
 * Repository contract implemented by data layer.
 */
// TODO: remove?
interface Repository<T> {

    suspend fun getItems(): List<T>
    suspend fun getItem(id: UUID): T?

    suspend fun updateItem(item: T)
    suspend fun insertItem(item: T)
    suspend fun deleteItem(item: T)
}