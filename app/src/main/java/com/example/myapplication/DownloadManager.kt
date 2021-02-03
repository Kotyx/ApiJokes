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
import java.io.IOException

class DownloadManager {

    companion object {
        fun downloadApiResults(mainActivityvm : MainActivityViewModel?) {
            val client = OkHttpClient()
            val url = "https://official-joke-api.appspot.com/jokes/ten"
            val request = Request.Builder()
                .url(url)
                .build()
            val call = client.newCall(request)
            call.enqueue(object : Callback {

                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                    Log.e("GetJokes", call.toString())

                }

                override fun onResponse(call: Call, response: Response) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val bodyInString = response.body?.string()
                        bodyInString?.let {
                            Log.w("GetJokes", bodyInString)
                            val JsonObject = JSONObject(bodyInString)

                            val results = JsonObject.optJSONArray("results")
                            results?.let {
                                Log.w("GetJokes", results.toString())
                                val gson = Gson()

                                val itemType = object : TypeToken<List<Jokes>>() {}.type

                                val list = gson.fromJson<List<Jokes>>(results.toString(), itemType)

                                mainActivityvm?.getlist(list)
                            }
                        }
                    }
                }


                suspend fun downloadApiSingleResult(userChoice: String): String {
                    // CONEXION A INTERNET
                    // OKHTTP....
                    delay(3000)
                    return when (userChoice.length) {
                        1 -> "RESULTADO 1"
                        2 -> "RESULTADO 2"
                        else -> "OTROS RESULTADOS"
                    }
                }
            })
        }
    }
}