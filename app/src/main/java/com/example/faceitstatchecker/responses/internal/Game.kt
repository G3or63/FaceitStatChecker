package com.example.faceitstatchecker.responses.internal


import com.google.gson.annotations.SerializedName

data class Game(
    @SerializedName("name")
    val name: String,
    @SerializedName("skill_level")
    val skillLevel: String
)