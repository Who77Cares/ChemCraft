package com.bignerdranch.chemcraft.lessons.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.chemcraft.R

import com.bignerdranch.chemcraft.local_storage.SharedPrefManager
import com.bignerdranch.chemcraft.lessons.domain.models.LessonsModel
import com.google.android.material.card.MaterialCardView


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
        holder.bind(lessons[position], onLessonClick)

        holder.itemView.setOnClickListener {
            Log.d("LessonClick", "Clicked lesson: ${lessons[position].name}")

            onLessonClick(lessons[position])
        }
        }
    }






class LessonContentViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater
        .from(parent.context)
        .inflate(R.layout.item_lesson, parent, false)
) {

    private val lessonDescription: TextView = itemView.findViewById(R.id.lessonDrescription)
    private val lessonCard: MaterialCardView = itemView.findViewById(R.id.lessonIItem)


    fun bind(model: LessonsModel, onClick: (LessonsModel) -> Unit) {
        lessonDescription.text = model.name
        lessonCard.setOnClickListener {
            onClick(model)
        }



    }
}