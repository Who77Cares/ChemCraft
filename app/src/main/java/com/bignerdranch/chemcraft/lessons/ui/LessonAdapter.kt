package com.bignerdranch.chemcraft.lessons.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.chemcraft.R

import com.bignerdranch.chemcraft.data.old.SharedPrefManager
import com.bignerdranch.chemcraft.lessons.domain.models.LessonsModel
import com.bignerdranch.chemcraft.cards.ui.CardsActivity


class LessonContentAdapter(
    context: Context,
    var lessons: List<LessonsModel>,
    private val onLessonClick: (LessonsModel) -> Unit
) : RecyclerView.Adapter<LessonContentViewHolder>() {

    private val sharedPreferences = SharedPrefManager(context)

    //
    override fun getItemCount(): Int = lessons.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonContentViewHolder =
        LessonContentViewHolder(parent)

    override fun onBindViewHolder(holder: LessonContentViewHolder, position: Int) {
        holder.bind(lessons[position])

        holder.lessonArrowForward.setOnClickListener {
            onLessonClick(lessons[position])
        }


//        updateIcon(holder.addOrRemove, lessons[position].name)





        }
//
//        holder.addOrRemove.setOnClickListener {
//            if (sharedPreferences.isFavorite(lessons[position].name)) {
//                sharedPreferences.removeFavorite(lessons[position].name)
//
//            } else {
//                sharedPreferences.addFavorite(lessons[position].name)
//            }
//            updateIcon(holder.addOrRemove, lessons[position].name)
//        }
    }


//    private fun updateIcon(imageView: ImageView, lessonId: String) {
//        val iconRes = if (sharedPreferences.isFavorite(lessonId)) {
//            R.drawable.save_to_my_lessons_icon_saved
//        } else {
//            R.drawable.save_to_my_lessons_icon
//        }
//        imageView.setImageResource(iconRes)
//    }



class LessonContentViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater
        .from(parent.context)
        .inflate(R.layout.item_lesson, parent, false)
) {

//    val addOrRemove: ImageView = itemView.findViewById(R.id.progressStar1)
//    val lessonTitle: Button = itemView.findViewById(R.id.lessonButton)
    private val lessonDescription: TextView = itemView.findViewById(R.id.lessonDrescription)
     val lessonArrowForward: ImageView = itemView.findViewById(R.id.lessonArrowForward)

    fun bind(model: LessonsModel) {
//        lessonTitle.text = model.description
        lessonDescription.text = model.name
    }
}