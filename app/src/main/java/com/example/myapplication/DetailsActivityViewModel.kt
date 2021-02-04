package com.example.myapplication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class DetailsActivityViewModel : ViewModel() {
    var texto=""

    fun recibirtexto(text:String){
        texto =text
    }
    fun enviartexto(): String {
        return texto
    }
}