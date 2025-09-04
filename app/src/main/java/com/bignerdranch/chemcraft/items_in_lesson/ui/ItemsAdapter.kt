package com.bignerdranch.chemcraft.items_in_lesson.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.items_in_lesson.domain.models.ItemModel
import com.bignerdranch.chemcraft.items_in_lesson.ui.full_screen_img.FullScreenImageFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners


class ItemsAdapter(
    var itemsList: List<ItemModel> = emptyList(),
    private val onImageDoubleTap: (String) -> Unit
): RecyclerView.Adapter<ViewHolder> () {

    companion object {
        private const val TEXT = 0
        private const val IMAGE = 1
    }

    // функция для установки верных классов из sealed класса
    override fun getItemViewType(position: Int): Int {
        return when (itemsList[position]) {
            is ItemModel.TextItem -> TEXT
            is ItemModel.ImageItem -> IMAGE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            TEXT -> TextViewHolder(parent)
            IMAGE -> ImageViewHolder(parent, onImageDoubleTap)
            else -> throw IllegalStateException("There is no ViewHolder for $viewType")
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (val content = itemsList[position]) {
            is ItemModel.TextItem -> (holder as TextViewHolder).bind(content)
            is ItemModel.ImageItem -> (holder as ImageViewHolder).bind(content)

        }
    }
}



class TextViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater
        .from(parent.context)
        .inflate(R.layout.item_text, parent, false)
) {

    val lessonText: TextView = itemView.findViewById(R.id.lesson_text)

    fun bind(itemModel: ItemModel.TextItem) {
        lessonText.text = itemModel.content
    }
}


@SuppressLint("ClickableViewAccessibility")
class ImageViewHolder(
    parent: ViewGroup,
    private val onImageDoubleTap: (String) -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater
        .from(parent.context)
        .inflate(R.layout.item_image, parent, false)
) {


    private val lessonImage: ImageView = itemView.findViewById(R.id.lesson_image)

    fun bind(itemModel: ItemModel.ImageItem) {
        itemView.tag = itemModel

        Glide.with(itemView)
            .load(itemModel.url)
            .centerCrop()
            .transform(RoundedCorners(2))
            .placeholder(R.drawable.placeholder)
            .into(lessonImage)
    }

    // логика двойного клика и открытия картинки
    private val gestureDetector = GestureDetector(itemView.context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onDoubleTap(e: MotionEvent): Boolean {
            val itemModel = itemView.tag as ItemModel.ImageItem
            onImageDoubleTap(itemModel.url)
            return true
        }
    })

    init {
        lessonImage.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }
    }

}