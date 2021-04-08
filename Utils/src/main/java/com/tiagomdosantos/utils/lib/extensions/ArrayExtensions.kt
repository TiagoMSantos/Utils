package com.tiagomdosantos.utils.lib.extensions

import java.util.Random

fun <T> Array<T?>.anyNullItem(): Boolean = any { it == null }

fun <T> Array<T?>.allItemsAreNull(): Boolean = all { it == null }

fun <T> Array<Array<T?>>.anyNullItem(): Boolean = any { it.anyNullItem() }

fun <T> Array<Array<T?>>.allItemsAreNull(): Boolean = all { it.allItemsAreNull() }

fun <T> Array<T>?.isBlank(): Boolean = this == null || isEmpty()

fun <T> Array<T>.getRandomItem(generator: Random = Random()): T = get(generator.nextInt(size))
