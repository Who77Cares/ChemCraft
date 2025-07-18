package com.bignerdranch.chemcraft.ui.main
import android.content.Intent
import android.os.Bundle


import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.chemcraft.SingleCardItemModel
import com.bignerdranch.chemcraft.data.get_card_repository.FirebaseAdminManager
import com.bignerdranch.chemcraft.databinding.ActivityMainBinding
import com.bignerdranch.chemcraft.ui.cards.models.CardToServerModel
import com.bignerdranch.chemcraft.ui.lessons_data.LessonActivity
import com.bignerdranch.chemcraft.ui.favorite.FavoriteActivity
import com.bignerdranch.chemcraft.ui.test.TestActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore


class MainMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: FirebaseFirestore



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lessonsListButton.setOnClickListener {
            startActivity(Intent(this, LessonActivity::class.java))
        }

        binding.myListLessonsButton.setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
        }

        binding.testsButton.setOnClickListener {
            startActivity(Intent(this, TestActivity::class.java))
        }

        val cardItems = listOf(
            mapOf("type" to "text", "content" to "Привет! Это первый текст."),
            mapOf("type" to "img", "content" to "https://example.com/image1.jpg"),
            mapOf("type" to "text", "content" to "Ещё немного текста."),
            mapOf("type" to "img", "content" to "https://example.com/image2.jpg"),
            mapOf("type" to "text", "content" to "Заключение.")
        )

        // Данные
        val lessonData: HashMap<String, Any> = hashMapOf(
            "description" to "",
            "name" to "",
            "score" to 0
        )

        val testData: HashMap<String, Any> = hashMapOf(
            "score" to 0,
            "testName" to ""
        )

        val cardsToServerData = CardToServerModel(
            imageUrl = "123",
            important = false,
            title = "Geir",
            cardItems = cardItems
        )




        binding.getCardData.setOnClickListener {

//            FirebaseAdminManager.addCardToLesson(
//                lessonId = "1YbgLsyvKVjGqcMuZWkk",
//                cardModel = cardsToServerData,
//                onSuccess = { },
//                onFailure = { }
//            )

//
//        FirebaseAdminManager.createLessonData(
//        lessonData = lessonData,
//        testData = testData,
//        cardsData = cardsToServerData
//        )

        }


        db = Firebase.firestore


    }


}
