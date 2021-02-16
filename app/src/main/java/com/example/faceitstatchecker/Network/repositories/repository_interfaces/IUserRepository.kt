package com.example.faceitstatchecker.Network.repositories.repository_interfaces

import com.example.faceitstatchecker.responses.PlayerSearchResponse

/**
 * Created by George Hart on 15/02/2021;
 */
interface IUserRepository {
    fun searchUsersViaCallback(queryString: String, callback: callback)
    interface callback {
        fun userSearchResults(playerSearchResponse: PlayerSearchResponse)
    }
}