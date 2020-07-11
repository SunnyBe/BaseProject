package com.zistus.basemvi.ui.main.state

import kotlin.random.Random

sealed class MainStateEvent {

    class GetRandomNumberInfo(
        val number: Long ? = Random.nextLong(0, 1000)
    ): MainStateEvent()

    class GetTriviaNumberInfo(
        val number: Long ? = Random.nextLong(0, 1000)
    ): MainStateEvent()

    class GetYearNumberInfo(
        val number: Long = Random.nextLong(0, 2020)
    ): MainStateEvent()

    class GetDateNumberInfo(
        val number: Long = Random.nextLong(100, 2020)
    ): MainStateEvent()

    class None: MainStateEvent()


}