package com.timplifier.karsyhkyrremastered.data.utils

interface DataMapper<T> {
    fun toDomain(): T
}