package com.bignerdranch.chemcraft
import android.content.Intent
import android.os.Bundle
import android.util.Log


import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.chemcraft.databinding.ActivityMainBinding
import com.bignerdranch.chemcraft.lessonsListScreen.LessonsListScreen
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


class MainMenu : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.lessonsListButton.setOnClickListener {
            startActivity(Intent(this, LessonsListScreen::class.java))
        }

        val db = Firebase.firestore

        val lesson3Content = hashMapOf(
            "blocks" to listOf(
                hashMapOf(
                    "blockName" to "1 lesson Intro",
                    "content" to listOf(
                        hashMapOf("type" to "Text", "content" to "алеко в лесу стоял уютный домик. В его окнах горел тёплый свет, а на крыше весело стрекотали птицы. В этом доме было много счастья. Вечером, когда на улице темнело, внутри горел камин, и можно было услышать, как трещат дрова. В доме всегда пахло свежим хлебом, а на кухне стояла чашка с горячим чаем. Это было место, где всегда можно было укрыться от холода и забот, где каждый уголок был полон уюта и тепла.."),
                        hashMapOf("type" to "Img", "url" to "https://downloader.disk.yandex.ru/preview/2153d566688a75d0f062a5735d124a9f4fb21e5b5106859b62c83d2b1f6f2049/67ac0bca/KwOkaa4_16A0-eBS6lqGoJa68VyyOyd9rOCU23C_tMREektdtbp_9ZRW92rnidHseF00_ysuUihmEjSIsaJqtQ%3D%3D?uid=0&filename=Screenshot_1.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048"),
                        hashMapOf("type" to "Text", "content" to "а свет, а на крыше весело стрекотали птицы. В этом доме было много счастья. Вечером, когда на улице темнело, внутри горел камин, и можно было услышать, как трещат дрова. В доме всегда пахло свежим хлебом, а на кухне стояла чашка с горячим чаем. Это было место, где всегда можно было укрыться от холода и забот, где каждый уголок был полон уюта и тепла..")

                    )
                ),
                hashMapOf(
                    "blockName" to "23 on start",
                    "content" to listOf(
                        hashMapOf("type" to "Text", "content" to "In this chapter, we cover basic algebra."),
                        hashMapOf("type" to "Img", "url" to "https://i.ibb.co/abcd1234/chapter1-image.png"),
                        hashMapOf("type" to "Text", "content" to "отик по имени Мурка сидел на подоконнике и наблюдал за миром с высоты. Его пушистая шёрстка была."),
                        hashMapOf("type" to "Text", "content" to "In this chapter, we cover basic algebra."),
                        hashMapOf("type" to "Img", "url" to "https://i.ibb.co/abcd1234/chapter1-image.png"),
                        hashMapOf("type" to "Text", "content" to "ягкое облако, а большие зелёные глаза с интересом следили за каждым движе."),

                    )
                ),
                hashMapOf(
                    "blockName" to "3 on345art",
                    "content" to listOf(
                        hashMapOf("type" to "Text", "content" to "In this chapter, we cover basic algebra."),
                        hashMapOf("type" to "Img", "url" to "https://i.ibb.co/abcd1234/chapter1-image.png"),
                        hashMapOf("type" to "Text", "content" to "отик по имени Мурка сидел на подоконнике и наблюдал за миром с высоты. Его пушистая шёрстка была."),
                        hashMapOf("type" to "Text", "content" to "In this chapter, we cover basic algebra."),
                        hashMapOf("type" to "Img", "url" to "https://i.ibb.co/abcd1234/chapter1-image.png"),
                        hashMapOf("type" to "Text", "content" to "ягкое облако, а большие зелёные глаза с интересом следили за каждым движе."),

                        )
                ),
                hashMapOf(
                    "blockName" to "3 on 345642356start",
                    "content" to listOf(
                        hashMapOf("type" to "Text", "content" to "In this chapter, we cover basic algebra."),
                        hashMapOf("type" to "Img", "url" to "https://i.ibb.co/abcd1234/chapter1-image.png"),
                        hashMapOf("type" to "Text", "content" to "отик по имени Мурка сидел на подоконнике и наблюдал за миром с высоты. Его пушистая шёрстка была."),
                        hashMapOf("type" to "Text", "content" to "In this chapter, we cover basic algebra."),
                        hashMapOf("type" to "Img", "url" to "https://i.ibb.co/abcd1234/chapter1-image.png"),
                        hashMapOf("type" to "Text", "content" to "ягкое облако, а большие зелёные глаза с интересом следили за каждым движе."),

                        )
                ),
                hashMapOf(
                    "blockName" to "3dsfgdsfg start",
                    "content" to listOf(
                        hashMapOf("type" to "Text", "content" to "In this chapter, we cover basic algebra."),
                        hashMapOf("type" to "Img", "url" to "https://i.ibb.co/abcd1234/chapter1-image.png"),
                        hashMapOf("type" to "Text", "content" to "отик по имени Мурка сидел на подоконнике и наблюдал за миром с высоты. Его пушистая шёрстка была."),
                        hashMapOf("type" to "Text", "content" to "In this chapter, we cover basic algebra."),
                        hashMapOf("type" to "Img", "url" to "https://i.pinimg.com/236x/c8/cc/24/c8cc24bba37a25c009647b8875aae0e3.jpg"),
                        hashMapOf("type" to "Text", "content" to "ягкое облако, а большие зелёные глаза с интересом следили за каждым движе."),

                        )
                )
            )
        )

        db.collection("lesson_content").document("lesson1")
            .set(lesson3Content)
            .addOnSuccessListener {
                Log.d("Firestore", "Lesson 3 content successfully uploaded.")
            }



    }
}
