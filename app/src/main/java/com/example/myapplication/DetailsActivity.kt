package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityDetailsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable

@Serializable
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var model : DetailsActivityViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this).get(DetailsActivityViewModel::class.java)
        var transpaso=intent.getStringExtra(SHARE)
        var transpaso2=intent.getStringExtra(SHARE2)

        binding.tvResultados.setText(transpaso+"\n"+transpaso2)


    }



    companion object {
        private const val SHARE = "TAG_EJEMPLO"
        private const val SHARE2 = "TAG_EJEMPLO"


        fun createDetailsActivity(context : Context,valor:String,valor2:String) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(SHARE, valor)
            intent.putExtra(SHARE2, valor2)
            context.startActivity(intent)
        }


    }
}