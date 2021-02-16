package com.example.faceitstatchecker.ui.main.repositories

import com.example.faceitstatchecker.Network.FSCRetrofit
import com.example.faceitstatchecker.Network.FaceitDataInterface
import com.example.faceitstatchecker.responses.PlayerSearchResponse
import com.example.faceitstatchecker.ui.main.repositories.repository_interfaces.IUserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by George Hart on 15/02/2021;
 */
class UserRepository : IUserRepository {
    override fun searchUsersViaCallback(queryString: String, callback: IUserRepository.callback) {
        FSCRetrofit.retrofitInstance.create(FaceitDataInterface::class.java).playerSearch(queryString).enqueue(
            object : Callback<PlayerSearchResponse>{
                override fun onResponse(call: Call<PlayerSearchResponse>, response:Response<PlayerSearchResponse>) {

                }

                override fun onFailure(call: Call<PlayerSearchResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            }
        )
    }
}