package com.bignerdranch.chemcraft.ui.lessons_data

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.chemcraft.R

import com.bignerdranch.chemcraft.data.SharedPrefManager
import com.bignerdranch.chemcraft.ui.cards.CardsActivity


class LessonContentAdapter(
    context: Context,
    private val lessonsData: List<LessonsData>
) : RecyclerView.Adapter<LessonContentViewHolder>() {

    private val sharedPreferences = SharedPrefManager(context)
//
    override fun getItemCount(): Int = lessonsData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonContentViewHolder =
        LessonContentViewHolder(parent)

    override fun onBindViewHolder(holder: LessonContentViewHolder, position: Int) {
        holder.bind(lessonsData[position])

        updateIcon(holder.addOrRemove, lessonsData[position].name)

        holder.lessonTitle.setOnClickListener {
            val intent = Intent(holder.itemView.context, CardsActivity::class.java).apply {

                putExtra("lessonId", lessonsData[position].id)


            }
            holder.itemView.context.startActivity(intent)
        }

        holder.addOrRemove.setOnClickListener {
            if (sharedPreferences.isFavorite(lessonsData[position].name)) {
                sharedPreferences.removeFavorite(lessonsData[position].name)

            } else {
                sharedPreferences.addFavorite(lessonsData[position].name)
            }
            updateIcon(holder.addOrRemove, lessonsData[position].name)
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

    fun bind(model: LessonsData) {
        lessonTitle.text = model.description
        lessonDescription.text = model.name
    }
}