package com.bignerdranch.chemcraft.test

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.chemcraft.R

class TestAdapter(
    private val test: List<TestContent>
): RecyclerView.Adapter<TestHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestHolder = TestHolder(parent)
    override fun getItemCount(): Int = test.size

    override fun onBindViewHolder(holder: TestHolder, position: Int) {
        holder.bind(test[position], position)
    }
}

class  TestHolder(parent: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater
        .from(parent.context)
        .inflate(R.layout.item_test, parent, false)
) {

    private val text: TextView = itemView.findViewById(R.id.test_number)

    fun bind(model: TestContent, position: Int){
        text.text = "$position"
    }
}