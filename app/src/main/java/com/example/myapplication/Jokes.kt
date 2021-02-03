package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class Jokes (
    @SerializedName("id")var id : Int,
    var type: String,
    var setup: String,
    var punchline: String){

    override fun toString(): String {
        return "$id, type: $type\n $setup\n $punchline\n"
    }
}