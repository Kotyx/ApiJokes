package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AdapterString : RecyclerView.Adapter<AdapterString.StringViewHolder>()  {

    private var datos : List<Jokes>? = null
    lateinit var trans : Jokes

    class StringViewHolder(root: View, val textView: TextView) : RecyclerView.ViewHolder(root)

    private lateinit var recyclerView : RecyclerView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_main_item, parent, false)
        val textView = view.findViewById<TextView>(R.id.tv_activity_main_item)
        return StringViewHolder(view, textView)
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        datos?.let {
            holder.textView.text = it[position].toString()
        }
        holder.textView.setOnClickListener {
            val pos = recyclerView.getChildLayoutPosition(it)
            datos?.let {
                trans= datos!![pos]
            }

        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun getItemCount(): Int {
        datos?.let {
            return it.size
        }
        return 0
    }



    suspend fun setData(jokes : List<Jokes>?){
        datos = jokes
        withContext(Dispatchers.Main){
            notifyDataSetChanged()
        }
    }

}