package com.example.faceitstatchecker.ui.UserSearch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.faceitstatchecker.Network.repositories.UserRepository
import com.example.faceitstatchecker.Network.repositories.repository_interfaces.IUserRepository
import com.example.faceitstatchecker.responses.PlayerSearchResponse
import com.example.faceitstatchecker.responses.internal.Player

/**
 * Created by George Hart on 16/02/2021;
 */
//TODO set up hilt DI
class UserSearchViewModel : ViewModel() , IUserRepository.callback {
    val searchResultsList = MutableLiveData<List<Player>>()

    val userSearchRepository: IUserRepository = UserRepository()
    //todo add indexing to the input function to account for the user scrolling to the end of the result list. (onScrollComplete -> get more results)
    fun userInputChanged(userInput: String) {
        //TODO need to delay this to allow for user continuing to type
        if(userInput.length > 3){
            userSearchRepository.searchUsersViaCallback(userInput, this)
        }
    }

    override fun userSearchResults(playerSearchResponse: PlayerSearchResponse) {
        searchResultsList.postValue(playerSearchResponse.players)
    }
}