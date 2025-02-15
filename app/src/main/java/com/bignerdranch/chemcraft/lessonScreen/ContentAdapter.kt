package com.bignerdranch.chemcraft.lessonScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bignerdranch.chemcraft.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners


class ContentAdapter(private var contentList: List<ContentItem>): RecyclerView.Adapter<ViewHolder> () {

    companion object {
        private const val TEXT = 0
        private const val IMAGE = 1
    }

    // функция для установки верных классов из sealed класса
    override fun getItemViewType(position: Int): Int {
        return when (contentList[position]) {
            is ContentItem.Text -> TEXT
            is ContentItem.Image -> IMAGE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            TEXT -> TextViewHolder(parent)
            IMAGE -> ImageViewHolder(parent)
            else -> throw IllegalStateException("There is no ViewHolder for $viewType")
        }
    }

    override fun getItemCount(): Int {
        return contentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (val content = contentList[position]) {
            is ContentItem.Text -> (holder as TextViewHolder).bind(content)
            is ContentItem.Image -> (holder as ImageViewHolder).bind(content)

        }
    }

    fun updateContent(newContentList: List<ContentItem>) {
        contentList = newContentList
        notifyDataSetChanged()  // Обновляем RecyclerView
    }
}



class TextViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater
        .from(parent.context)
        .inflate(R.layout.item_text, parent, false)
) {

    val lessonText: TextView = itemView.findViewById(R.id.lesson_text)

    fun bind(contentItem: ContentItem.Text) {
        lessonText.text = contentItem.content
    }
}




@SuppressLint("ClickableViewAccessibility")
class ImageViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater
        .from(parent.context)
        .inflate(R.layout.item_image, parent, false)
) {


    val lessonImage: ImageView = itemView.findViewById(R.id.lesson_image)

    fun bind(contentItem: ContentItem.Image) {
        itemView.tag = contentItem

        Glide.with(itemView)
            .load(contentItem.url)
            .centerCrop()
            .transform(RoundedCorners(2))
//          .placeholder(R.drawable.placeholder)
            .into(lessonImage)
    }

    // логика двойного клика и открытия картинки
    private val gestureDetector = GestureDetector(itemView.context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onDoubleTap(e: MotionEvent): Boolean {
            val contentItem = itemView.tag as ContentItem.Image
            openImageFullScreen(contentItem.url)
            return true
        }
    })

    init {
        lessonImage.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }

        
    }

    private fun openImageFullScreen(imageUrl: String) {
        val intent = Intent(itemView.context, FullScreenImageActivity::class.java)
        intent.putExtra("image_url", imageUrl)
        itemView.context.startActivity(intent)
    }


}