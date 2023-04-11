package com.github.v43d3rm4k4r.kotlinutils

import kotlin.LazyThreadSafetyMode.NONE

fun <T> fastLazy(initializer: () -> T): Lazy<T> = lazy(NONE, initializer)