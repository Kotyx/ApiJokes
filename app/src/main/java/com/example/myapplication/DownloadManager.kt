package com.example.myapplication

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

class DownloadManager {

    companion object {
        fun downloadApiResults(): List<Jokes>? {
            val client = OkHttpClient()
            val url = "https://official-joke-api.appspot.com/jokes/ten"
            val request = Request.Builder()
                .url(url)
                .build()
            val call = client.newCall(request)
            val response = call.execute()
            val bodyInString = response.body?.string()
            var a = bodyInString?.let {
                Log.w("GetJokes", bodyInString)
                val results = JSONArray(bodyInString)

                results?.let {
                    Log.w("GetJokes", results.toString())
                    val gson = Gson()

                    val itemType = object : TypeToken<List<Jokes>>() {}.type

                    val list = gson.fromJson<List<Jokes>>(results.toString(), itemType)

                    return@let list
                }
            }
            return a
        }
        /*suspend fun downloadApiSingleResult(userChoice: String): String {
            // CONEXION A INTERNET
            // OKHTTP....
            delay(3000)
            return when (userChoice.length) {
                1 -> "RESULTADO 1"
                2 -> "RESULTADO 2"
                else -> "OTROS RESULTADOS"
            }
        }*/
    }

}
