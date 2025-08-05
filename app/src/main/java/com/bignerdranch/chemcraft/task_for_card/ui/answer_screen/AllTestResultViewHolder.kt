package com.bignerdranch.chemcraft.task_for_card.ui.answer_screen

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.task_for_card.model.AllTestResultModel

class AllTestResultViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val cardResultNumber: TextView = itemView.findViewById(R.id.testTaskNumber)
    private val checkAnswer: TextView = itemView.findViewById(R.id.checkAnswer)
    private val userAnswer: TextView = itemView.findViewById(R.id.userAnswer)


    fun bind(model: AllTestResultModel, position: Int) {


        cardResultNumber.text = (position + 1).toString()
        checkAnswer.text = model.correct
        userAnswer.text = model.userAnswer

    }




}