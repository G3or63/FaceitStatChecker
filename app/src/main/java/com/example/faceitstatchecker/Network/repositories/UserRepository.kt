package com.example.faceitstatchecker.Network.repositories

import android.util.Log
import com.example.faceitstatchecker.Network.FSCRetrofit
import com.example.faceitstatchecker.Network.FaceitDataInterface
import com.example.faceitstatchecker.responses.PlayerSearchResponse
import com.example.faceitstatchecker.Network.repositories.repository_interfaces.IUserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by George Hart on 15/02/2021;
 */
class UserRepository : IUserRepository {
    private val TAG = "UserRepository"
    override fun searchUsersViaCallback(queryString: String, callback: IUserRepository.callback) {
        FSCRetrofit.retrofitInstance.create(FaceitDataInterface::class.java).playerSearch(queryString).enqueue(
            object : Callback<PlayerSearchResponse> {
                override fun onResponse(
                    call: Call<PlayerSearchResponse>,
                    response: Response<PlayerSearchResponse>
                ) {
                    val resBod = response.body()
                    if (response.isSuccessful && resBod != null) {
                        callback.userSearchResults(resBod)
                    }
                }

                override fun onFailure(call: Call<PlayerSearchResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: api failure", t)
                }
            }
        )
    }
}