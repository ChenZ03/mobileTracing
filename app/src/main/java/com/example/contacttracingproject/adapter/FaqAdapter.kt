package com.example.contacttracingproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contacttracingproject.R

class FaqAdapter : RecyclerView.Adapter<FaqAdapter.ViewHolder>() {

    var questionData = arrayOf("Question 1", "Question 2", "Question 3", "Question 4", "Question 5")
    var answerData = arrayOf("This is the answer for answer 1", "This is the answer for answer 2", "This is the answer for answer 3", "This is the answer for answer 4", "This is the answer for answer 5")

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var question: TextView
        var answer: TextView
        init {
            question = itemView.findViewById(R.id.question)
            answer = itemView.findViewById(R.id.answer)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.faq_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.question.text = questionData[position]
        holder.answer.text = answerData[position]
    }

    override fun getItemCount(): Int {
        return questionData.size
    }
}