package com.bignerdranch.chemcraft.single_card

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
import com.bignerdranch.chemcraft.single_card.full_screen_img.FullScreenImageActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners


class SingleCardAdapter(
    var contentList: List<SingleCardModel>
): RecyclerView.Adapter<ViewHolder> () {

    companion object {
        private const val TEXT = 0
        private const val IMAGE = 1
    }

    // функция для установки верных классов из sealed класса
    override fun getItemViewType(position: Int): Int {
        return when (contentList[position]) {
            is SingleCardModel.TextItem -> TEXT
            is SingleCardModel.ImageItem -> IMAGE
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
            is SingleCardModel.TextItem -> (holder as TextViewHolder).bind(content)
            is SingleCardModel.ImageItem -> (holder as ImageViewHolder).bind(content)

        }
    }
}



class TextViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater
        .from(parent.context)
        .inflate(R.layout.item_text, parent, false)
) {

    val lessonText: TextView = itemView.findViewById(R.id.lesson_text)

    fun bind(singleCardModel: SingleCardModel.TextItem) {
        lessonText.text = singleCardModel.content
    }
}



@SuppressLint("ClickableViewAccessibility")
class ImageViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater
        .from(parent.context)
        .inflate(R.layout.item_image, parent, false)
) {


    private val lessonImage: ImageView = itemView.findViewById(R.id.lesson_image)

    fun bind(singleCardModel: SingleCardModel.ImageItem) {
        itemView.tag = singleCardModel

        Glide.with(itemView)
            .load(singleCardModel.url)
            .centerCrop()
            .transform(RoundedCorners(2))
            .placeholder(R.drawable.placeholder)
            .into(lessonImage)
    }

    // логика двойного клика и открытия картинки
    private val gestureDetector = GestureDetector(itemView.context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onDoubleTap(e: MotionEvent): Boolean {
            val singleCardModel = itemView.tag as SingleCardModel.ImageItem
            openImageFullScreen(singleCardModel.url)
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