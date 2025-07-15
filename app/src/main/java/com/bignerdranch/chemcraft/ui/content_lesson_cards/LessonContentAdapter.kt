package com.bignerdranch.chemcraft.ui.content_lesson_cards

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.LessonsContentModel
import com.bignerdranch.chemcraft.data.SharedPrefManager
import com.bignerdranch.chemcraft.ui.sub_cards.CardsActivity


class LessonContentAdapter(
    context: Context,
    private val lessonsContentModels: List<LessonsContentModel>
) : RecyclerView.Adapter<LessonContentViewHolder>() {

    private val sharedPreferences = SharedPrefManager(context)
//
    override fun getItemCount(): Int = lessonsContentModels.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonContentViewHolder =
        LessonContentViewHolder(parent)

    override fun onBindViewHolder(holder: LessonContentViewHolder, position: Int) {
        holder.bind(lessonsContentModels[position])

        updateIcon(holder.addOrRemove, lessonsContentModels[position].id)

        holder.lessonTitle.setOnClickListener {
            val intent = Intent(holder.itemView.context, CardsActivity::class.java).apply {
                putExtra("lessonId", lessonsContentModels[position].id)
                putExtra("title", lessonsContentModels[position].title)
                putExtra("description", lessonsContentModels[position].description)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.addOrRemove.setOnClickListener {
            if (sharedPreferences.isFavorite(lessonsContentModels[position].id)) {
                sharedPreferences.removeFavorite(lessonsContentModels[position].id)

            } else {
                sharedPreferences.addFavorite(lessonsContentModels[position].id)
            }
            updateIcon(holder.addOrRemove, lessonsContentModels[position].id)
        }
    }


    private fun updateIcon(imageView: ImageView, lessonId: String) {
        val iconRes = if (sharedPreferences.isFavorite(lessonId)) {
            R.drawable.save_to_my_lessons_icon_saved
        } else {
            R.drawable.save_to_my_lessons_icon
        }
        imageView.setImageResource(iconRes)
    }

}

class LessonContentViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater
        .from(parent.context)
        .inflate(R.layout.item_lesson, parent, false)
) {

    val addOrRemove: ImageView = itemView.findViewById(R.id.add_or_remove_to_my_lessons)
    val lessonTitle: Button = itemView.findViewById(R.id.lessonButton)
    private val lessonDescription: TextView = itemView.findViewById(R.id.lessonDrescription)

    fun bind(model: LessonsContentModel) {
        lessonTitle.text = model.title
        lessonDescription.text = model.description
    }
}