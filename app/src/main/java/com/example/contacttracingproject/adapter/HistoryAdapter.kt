package com.example.contacttracingproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contacttracingproject.R
import com.example.contacttracingproject.models.History
import com.example.contacttracingproject.databinding.HistoryItemBinding

class HistoryAdapter: androidx.recyclerview.widget.ListAdapter<History, HistoryAdapter.HviewHolder>(COMPARATOR) {

//    var addressData = arrayOf("Jelutong", "Penang", "East Malaysia", "West Malaysia", "SomeWhere")
//    var dateTimeData = arrayOf("April 1st, 2022 ~ 11:00AM", "April 1st, 2022 ~ 10:00AM", "April 1st, 2022 ~ 9:00AM", "April 1st, 2022 ~ 8:30AM", "April 1st, 2022 ~ 8:00AM")
//    var interactionData = arrayOf(7,4,2,6,3)
//    var imgData = arrayOf(
//        R.drawable.red,
//        R.drawable.orange,
//        R.drawable.yellow,
//        R.drawable.red,
//        R.drawable.orange
//    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HviewHolder {
        val binding = HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HviewHolder(binding)
    }

    override fun onBindViewHolder(holder: HviewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class HviewHolder(val binding : HistoryItemBinding): RecyclerView.ViewHolder(binding.root) {
        val address: TextView = binding.history
        val dateTime: TextView = binding.historyTime

        fun bind(history: History){
            address.text = history.location
            dateTime.text = history.dateTime
        }
    }

    companion object{
        val COMPARATOR = object : DiffUtil.ItemCallback<History>(){
            override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem == newItem
            }
        }
    }

}