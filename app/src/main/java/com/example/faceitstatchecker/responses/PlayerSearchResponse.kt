package com.example.faceitstatchecker.responses


import com.google.gson.annotations.SerializedName

data class PlayerSearchResponse(
    @SerializedName("end")
    val end: Int,
    @SerializedName("items")
    val players: List<Player>,
    @SerializedName("start")
    val start: Int
)