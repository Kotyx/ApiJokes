package com.example.myapplication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class MainActivityViewModel : ViewModel() {



    suspend fun getApiResults():List<Jokes>? {
        return withContext(Dispatchers.IO) {
            val resultado = GlobalScope.async {
                DownloadManager.downloadApiResults()
            }
            resultado.await()
        }
    }



}