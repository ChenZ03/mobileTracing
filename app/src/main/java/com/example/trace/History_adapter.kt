package com.example.trace

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class History_adapter(
    history: History,
    history1: Array<String>,
    history_time: Array<String>,
    interactions: Array<String>,
    dangerImg: Array<Int>
) : RecyclerView.Adapter<History_adapter.ViewHolder>() {

    lateinit var context: Context
    lateinit var historyData: ArrayList<String>
    lateinit var historyTime : ArrayList<String>
    lateinit var historyInteraction: ArrayList<String>
    lateinit var img : ArrayList<Int>

    fun MyAdapter(ct : Context, history : ArrayList<String>, history_time : ArrayList<String>, history_interactions : ArrayList<String>, image : ArrayList<Int>) {
        context = ct
        historyData = history
        historyTime = history_time
        historyInteraction = history_interactions
        img = image
    }


    class ViewHolder: RecyclerView.ViewHolder {
        constructor(itemView: View) : super(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}