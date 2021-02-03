package com.example.myapplication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class MainActivityViewModel : ViewModel() {

    private var listas : List<Jokes>? = null

    suspend fun getApiResults() {
        return withContext(Dispatchers.IO) {
            val resultado = GlobalScope.async {
                DownloadManager.downloadApiResults(this@MainActivityViewModel)
            }
            resultado.await()
        }
    }

    fun getlist(lista:List<Jokes>){
        listas=lista
    }
}