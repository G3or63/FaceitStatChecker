package com.example.faceitstatchecker.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.faceitstatchecker.Network.FSCRetrofit
import com.example.faceitstatchecker.responses.PlayerSearchResponse
import com.example.faceitstatchecker.Network.repositories.UserRepository
import com.example.faceitstatchecker.Network.repositories.repository_interfaces.IUserRepository

private const val TAG = "MainViewModel"
class MainViewModel : ViewModel() {
    fun test(){
        UserRepository().searchUsersViaCallback("G3o", object : IUserRepository.callback {
            override fun userSearchResults(playerSearchResponse: PlayerSearchResponse) {
                for (item in playerSearchResponse.players) {
                    Log.d(TAG, "userSearchResults: ${item.nickname}; ${item.playerId}")
                }
            }
        })
    }
}