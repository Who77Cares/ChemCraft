package com.bignerdranch.chemcraft.items_in_lesson.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.chemcraft.databinding.ActivityItemsListBinding
import com.bignerdranch.chemcraft.items_in_lesson.domain.models.ItemModel
import com.bignerdranch.chemcraft.ui.test.TestActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemsListBinding
    private lateinit var adapter: ItemsAdapter
    private val viewModel: ItemsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemsListBinding.inflate(layoutInflater)
        setContentView(binding.root)




//        title = findViewById(R.id.itemTitle)

//        database = Firebase.firestore


        val lessonId = intent.getStringExtra("LESSON_ID")!!
        val cardId = intent.getStringExtra("CARD_ID")!!


        binding.itemTitle.text = cardId


        Log.d("LessonScreen", "Передаем ID урока: $cardId, ---- $title")

        binding.itemRecycleView.layoutManager = LinearLayoutManager(this)

        adapter = ItemsAdapter()
        binding.itemRecycleView.adapter = adapter




        viewModel.observeState().observe(this) {
            render(it)
        }
        viewModel.setLessonId(lessonId = lessonId, cardId = cardId)
        viewModel.getItems()


//        FirebaseNetworkClientOld.loadCardItemsById(
//            lessonId = lessonId,
//            cardId = cardId,
//            onSuccess = {  listSingleCardItems ->
//                singleCardAdapter.contentList = listSingleCardItems
//                singleCardAdapter.notifyDataSetChanged()
//
//
//            },
//            onFailure = {
//
//            }
//        )


        binding.itemTest.setOnClickListener {
            val intent = Intent(this, TestActivity::class.java)
            intent.putExtra("LESSON_ID", lessonId)
            intent.putExtra("CARD_ID", cardId)
            startActivity(intent)
        }


    }


    private fun render(state: ItemsState) {
        when(state) {
            ItemsState.Loading -> {
                Log.d("asd", "sad")


            }
            is ItemsState.Content -> {
                Log.d("ItemsActivity", "Получили контент: ${state.items}")
                showContent(items = state.items)
            }

            is ItemsState.Error -> {
                Log.d("asd", "sad")


            }
        }
    }


    private fun showContent(items: List<ItemModel>) {
        adapter.itemsList = items
        adapter.notifyDataSetChanged()

        binding.apply {
//
            itemTitle.visibility = View.VISIBLE
            itemRecycleView.visibility = View.VISIBLE

        }
    }

}