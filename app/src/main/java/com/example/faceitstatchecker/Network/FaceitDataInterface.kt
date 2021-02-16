package com.example.faceitstatchecker.Network

import com.example.faceitstatchecker.responses.PlayerSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by George Hart on 15/02/2021;
 */
interface FaceitDataInterface {
    /*
    *  @POST ("AddNotes")
    Call<AddNotesResponse> addNotes(@Body AddNotesRequest request);
    * */
    @GET("/search/players")
    fun playerSearch(@Query("nickname") playerName: String) : Call<PlayerSearchResponse>
}