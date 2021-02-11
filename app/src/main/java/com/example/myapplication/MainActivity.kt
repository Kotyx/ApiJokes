package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var model :MainActivityViewModel
    private lateinit var binding: ActivityMainBinding

    private var adapter = AdapterString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        createRecyclerView()

        GlobalScope.launch(Dispatchers.IO) {
            val resultados = model.getApiResults()
            adapter.setData(resultados)
            hideProgressBar()
        }

        binding.bSiguiente.setOnClickListener {
            adapter.jokeSeleccionada?.let {
                DetailsActivity.createDetailsActivity(this, it.setup,it.punchline)

            }

            /*val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("SHARE", adapter.trans.toString())
            startActivity(intent)*/
        }


    }

    private suspend fun hideProgressBar() = withContext(Dispatchers.Main) {
        binding.progressBar2.visibility = View.GONE
    }

    private fun createRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}