package com.example.faceitstatchecker.responses.internal


import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("games")
    val games: List<Game>,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("player_id")
    val playerId: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("verified")
    val verified: Boolean
)