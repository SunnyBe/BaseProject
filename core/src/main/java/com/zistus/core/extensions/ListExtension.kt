package com.zistus.core.extensions

import com.zistus.core.utils.ComparablePair

//private fun<T> List<T>.applySort(customSortOrder: List<String>): List<T> {
//    return sortedBy { item ->
//        val positionForItem = customSortOrder.indexOf(item).let { order ->
//            if (order > -1) order else Int.MAX_VALUE
//        }
//        ComparablePair(positionForItem, item.name)
//    }
//}