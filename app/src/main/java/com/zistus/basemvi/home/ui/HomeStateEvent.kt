package com.zistus.basemvi.home.ui

sealed class HomeStateEvent {
    class FetchFilesEvent: HomeStateEvent()

    class GetUserEvent(
        val userId: String
    ): HomeStateEvent()
}