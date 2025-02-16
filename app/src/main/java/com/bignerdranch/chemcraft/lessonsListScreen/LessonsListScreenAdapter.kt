package com.bignerdranch.chemcraft.lessonsListScreen

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.lessonScreen.Lesson
import com.bignerdranch.chemcraft.lessonScreen.LessonScreen
import java.io.Serializable



class LessonsListScreenAdapter(
    val lessons: List<Lesson>
) : RecyclerView.Adapter<LessonsListScreenHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsListScreenHolder = LessonsListScreenHolder(parent)

    override fun onBindViewHolder(holder: LessonsListScreenHolder, position: Int) {
        holder.bind(lessons[position])

        holder.itemView.setOnClickListener {

            val intent = Intent(holder.itemView.context, LessonScreen::class.java).apply {
                putExtra("lesson", lessons[position] as Serializable) // as Serializable и добавление : Serializable в ContentLISt необходимо для передачи объекта в интент. вообще этот способ не очень - лучше использовать Parcelable
            }
            holder.itemView.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int = lessons.size


}

class LessonsListScreenHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater
        .from(parent.context)
        .inflate(R.layout.item_lesson, parent, false)
) {

    private val lessonTitle: Button = itemView.findViewById(R.id.lessonButton)
    private val lessonDescription: TextView = itemView.findViewById(R.id.lessonDrescription)

    fun bind(model: Lesson) {
        lessonTitle.text = model.title
        lessonDescription.text = model.description
    }

}