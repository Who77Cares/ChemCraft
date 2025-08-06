package com.bignerdranch.chemcraft.lesson_task.ui.answer_screen

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.lesson_task.model.AllTestResultModel

class AllTestResultAdapter(
    private val context: Context,
    private var items: List<AllTestResultModel> = emptyList()
): RecyclerView.Adapter<AllTestResultViewHolder>() {


    fun setItems(newItems: List<AllTestResultModel>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllTestResultViewHolder {
        val itemsView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_all_test_result, parent, false)
        return  AllTestResultViewHolder(itemsView)
    }

    override fun getItemCount(): Int  = items.size

    override fun onBindViewHolder(holder: AllTestResultViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, position)
    }




}