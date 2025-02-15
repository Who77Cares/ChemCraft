package com.bignerdranch.chemcraft.lessonScreen

import android.view.LayoutInflater
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.chemcraft.OnBlockClickListener
import com.bignerdranch.chemcraft.R

class BlocksAdapter(private val contentList: List<ContentList>,
                    private val listener: OnBlockClickListener
): RecyclerView.Adapter<BlocksHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlocksHolder = BlocksHolder(parent)

    override fun getItemCount(): Int = contentList.size

    override fun onBindViewHolder(holder: BlocksHolder, position: Int) {
        holder.bind(contentList[position])
        // Обработчик клика
        holder.itemView.setOnClickListener {
            // Передаем блок в слушатель
            listener.onBlockClick(contentList[position].content)
        }


    }
}

class BlocksHolder(parent: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater
        .from(parent.context)
        .inflate(R.layout.item_block, parent, false)

){
    private val blockTitle: TextView = itemView.findViewById(R.id.block_title)

    fun bind(model: ContentList) {
        blockTitle.text = model.blockName
    }
}