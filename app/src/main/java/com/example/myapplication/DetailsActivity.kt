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


class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var model : DetailsActivityViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this).get(DetailsActivityViewModel::class.java)

        binding.bBuscar.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            GlobalScope.launch (Dispatchers.IO) {
                val resultado = intent.getSerializableExtra(SHARE)
                withContext(Dispatchers.Main){
                    binding.tvResultados.text = resultado.toString()
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }


    @Serializable
    companion object {
        private const val SHARE = "TAG_EJEMPLO"


        fun createDetailsActivity(context : Context,valor:Jokes) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("SHARE", valor)
            context.startActivity(intent)
        }


    }
}