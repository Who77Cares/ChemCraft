package com.bignerdranch.chemcraft.lessonScreen

import android.view.LayoutInflater
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.chemcraft.R

class BlocksAdapter(private var contentCardViewModel: List<ContentCardViewModel>,
                    private val listener: OnBlockClickListener,
                    private val recyclerView: RecyclerView
): RecyclerView.Adapter<BlocksHolder>() {

    var selectedPosition: Int = -1 // Переменная для отслеживания выбранного элемента

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlocksHolder = BlocksHolder(parent)
    override fun getItemCount(): Int = contentCardViewModel.size
    override fun onBindViewHolder(holder: BlocksHolder, position: Int) {
        holder.bind(contentCardViewModel[position], position == selectedPosition)

        // Обработчик клика
        holder.itemView.setOnClickListener {

            val previousSelectedPosition = selectedPosition
            selectedPosition = holder.adapterPosition
            notifyItemChanged(previousSelectedPosition)  // Возвращаем цвет предыдущего элемента
            notifyItemChanged(selectedPosition)

            // Передаем блок в слушатель
            listener.onBlockClick(contentCardViewModel[position].content)
            recyclerView.smoothScrollToPosition(position)
        }


    }

    fun updateBlocks(newBlocks: List<ContentCardViewModel>) {
        contentCardViewModel = newBlocks
        notifyDataSetChanged()  // Перерисовываем RecyclerView с новыми блоками
    }
}

class BlocksHolder(parent: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater
        .from(parent.context)
        .inflate(R.layout.item_block, parent, false)

){
    private val blockTitle: TextView = itemView.findViewById(R.id.block_title)

    fun bind(model: ContentCardViewModel, isSelected: Boolean) {
        blockTitle.text = model.blockName

        if (isSelected) {
            blockTitle.setBackgroundResource(R.drawable.back_for_block_click)  // Устанавливаем drawable для выбранного элемента
        } else {
            blockTitle.setBackgroundResource(R.drawable.back_for_block)  // Устанавливаем drawable для невыбранного элемента
        }
    }
}