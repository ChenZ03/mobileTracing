package com.example.contacttracingproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class   HistoryAdapter: RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    var addressData = arrayOf("Jelutong", "Penang", "East Malaysia", "West Malaysia", "SomeWhere")
    var dateTimeData = arrayOf("April 1st, 2022 ~ 11:00AM", "April 1st, 2022 ~ 10:00AM", "April 1st, 2022 ~ 9:00AM", "April 1st, 2022 ~ 8:30AM", "April 1st, 2022 ~ 8:00AM")
    var interactionData = arrayOf(7,4,2,6,3)
    var imgData = arrayOf(R.drawable.red, R.drawable.orange, R.drawable.yellow, R.drawable.red, R.drawable.orange)

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var address: TextView
        var dateTime: TextView
        var interaction: TextView
        var img : ImageView

        init {
            address = itemView.findViewById(R.id.history)
            dateTime = itemView.findViewById(R.id.history_time)
            interaction = itemView.findViewById(R.id.history_interaction)
            img = itemView.findViewById(R.id.danger)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.address.text = addressData[position]
        holder.dateTime.text = dateTimeData[position]
        holder.interaction.text = interactionData[position].toString() + " Interactions"
        holder.img.setImageResource(imgData[position])
    }

    override fun getItemCount(): Int {
        return addressData.size
    }
}