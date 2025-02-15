package com.bignerdranch.chemcraft.lessonScreen

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.load
import coil.request.CachePolicy
import com.bignerdranch.chemcraft.OnBlockClickListener
import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.databinding.ActivityLessonScreenBinding

class LessonScreen : AppCompatActivity(), OnBlockClickListener {
    private lateinit var binding: ActivityLessonScreenBinding
    private lateinit var contentAdapter: ContentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lesson = intent.getSerializableExtra("lesson") as Lesson

        val lessonBlock = lesson.blocks[0]

        // Инициализируем contentAdapter до настройки RecyclerView
        contentAdapter = ContentAdapter(lessonBlock.content)

        binding.title.text = lesson.title

        // Устанавливаем адаптер только один раз
        binding.lessonRecycleView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.lessonRecycleView.adapter = contentAdapter // Используем инициализированный адаптер

        binding.blocksRecycleView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.blocksRecycleView.adapter = BlocksAdapter(lesson.blocks, this)
    }

    override fun onBlockClick(contentList: List<ContentItem>) {
        contentAdapter.updateContent(contentList)
    }
}