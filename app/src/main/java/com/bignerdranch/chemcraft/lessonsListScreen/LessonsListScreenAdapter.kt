package com.bignerdranch.chemcraft.lessonsListScreen

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.chemcraft.myLessons.MyLessonsScreen
import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.lessonScreen.Lesson
import com.bignerdranch.chemcraft.lessonScreen.LessonScreen
import com.bignerdranch.chemcraft.myLessons.SharedPrefManager


class LessonsListScreenAdapter(
    context: Context,
    private val lessons: List<Lesson>
) : RecyclerView.Adapter<LessonsListScreenHolder>() {

    private val sharedPreferences = SharedPrefManager(context)

    override fun getItemCount(): Int = lessons.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsListScreenHolder = LessonsListScreenHolder(parent)

    override fun onBindViewHolder(holder: LessonsListScreenHolder, position: Int) {
        holder.bind(lessons[position])

        updateIcon(holder.addOrRemove, lessons[position].id)

        holder.lessonTitle.setOnClickListener {
            val intent = Intent(holder.itemView.context, LessonScreen::class.java).apply {
                putExtra("lessonId", lessons[position].id)
                putExtra("title", lessons[position].title)
                putExtra("description", lessons[position].description)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.addOrRemove.setOnClickListener {
            if (sharedPreferences.isFavorite(lessons[position].id)) {
                sharedPreferences.removeFavorite(lessons[position].id)

            } else {
                sharedPreferences.addFavorite(lessons[position].id)
            }
            updateIcon(holder.addOrRemove, lessons[position].id)
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

class LessonsListScreenHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater
        .from(parent.context)
        .inflate(R.layout.item_lesson, parent, false)
) {

    val addOrRemove: ImageView = itemView.findViewById(R.id.add_or_remove_to_my_lessons)
    val lessonTitle: Button = itemView.findViewById(R.id.lessonButton)
    private val lessonDescription: TextView = itemView.findViewById(R.id.lessonDrescription)

    fun bind(model: Lesson) {
        lessonTitle.text = model.title
        lessonDescription.text = model.description

    }

}