package com.bignerdranch.chemcraft.lessonsListScreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.chemcraft.databinding.ActivityLessonsListScreenBinding
import com.bignerdranch.chemcraft.lessonScreen.ContentAdapter
import com.bignerdranch.chemcraft.lessonScreen.ContentItem
import com.bignerdranch.chemcraft.lessonScreen.ContentList
import com.bignerdranch.chemcraft.lessonScreen.Lesson
import com.bignerdranch.chemcraft.lessonScreen.LessonScreen
import java.io.Serializable

class LessonsListScreen : AppCompatActivity() {
    private lateinit var binding: ActivityLessonsListScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonsListScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val lessons = listOf<Lesson>( // все уроки
            Lesson(
                title = "Урок 1",
                blocks = listOf<ContentList>( // подтемы урока
                    ContentList(
                        blockName = "Пушкин и колотушкин",
                        content = listOf<ContentItem>( // блоки текста-картинок
                            ContentItem.Text("Солнышко встало на утреннем небе, разгоняя последние тёмные тени ночи. Его лучи мягко касались земли, согревая всё вокруг. Цветы раскрывались, приветствуя его тепло, а воздух наполнился запахом свежести и цветов. В лесу пели птицы, а на поляне играли зайчата. Солнышко радостно светило, обещая всем новый день, полный света и счастья."),
                            ContentItem.Image("https://downloader.disk.yandex.ru/preview/2153d566688a75d0f062a5735d124a9f4fb21e5b5106859b62c83d2b1f6f2049/67ac0bca/KwOkaa4_16A0-eBS6lqGoJa68VyyOyd9rOCU23C_tMREektdtbp_9ZRW92rnidHseF00_ysuUihmEjSIsaJqtQ%3D%3D?uid=0&filename=Screenshot_1.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048"),
                            ContentItem.Text("Котик по имени Мурка сидел на подоконнике и наблюдал за миром с высоты. Его пушистая шёрстка была как мягкое облако, а большие зелёные глаза с интересом следили за каждым движением. Мурка был любителем тёплых местечек и длинных дневных снов. Иногда он лениво растягивался на солнечном диване, потягиваясь и мурлыкая, словно наслаждаясь каждым моментом своей жизни."),
                            ContentItem.Image("https://i.ibb.co/NgVFXxqk/123.png"),
                            ContentItem.Text("Далеко в лесу стоял уютный домик. В его окнах горел тёплый свет, а на крыше весело стрекотали птицы. В этом доме было много счастья. Вечером, когда на улице темнело, внутри горел камин, и можно было услышать, как трещат дрова. В доме всегда пахло свежим хлебом, а на кухне стояла чашка с горячим чаем. Это было место, где всегда можно было укрыться от холода и забот, где каждый уголок был полон уюта и тепла."),
                            ContentItem.Image("https://cs15.pikabu.ru/post_img/2025/02/13/7/1739447142190961691.webp"),
                            ContentItem.Image("https://downloader.disk.yandex.ru/preview/2153d566688a75d0f062a5735d124a9f4fb21e5b5106859b62c83d2b1f6f2049/67ac0bca/KwOkaa4_16A0-eBS6lqGoJa68VyyOyd9rOCU23C_tMREektdtbp_9ZRW92rnidHseF00_ysuUihmEjSIsaJqtQ%3D%3D?uid=0&filename=Screenshot_1.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048")
                        )
                    ),
                    ContentList(
                        blockName = "Зазибар",
                        content = listOf(
                            ContentItem.Text("Солнышко встало на утреннем небе, разгоняя последние тёмные тени ночи. Его лучи мягко касались земли, согревая всё вокруг. Цветы раскрывались, приветствуя его тепло, а воздух наполнился запахом свежести и цветов. В лесу пели птицы, а на поляне играли зайчата. Солнышко радостно светило, обещая всем новый день, полный света и счастья."),
                            ContentItem.Image("https://downloader.disk.yandex.ru/preview/89bfc97be1d9042b504435114f3f711383b261722abfddbd5b89b14ee37bc32c/67ac0d55/54mfhgKZ0eQbl1BP26JYeWieqr2AKZku9Q4EHTVZd0Gy6nkfyKSKHQug7H7QxjvbYd9HOFWX3x5d-JxEAZZRaQ%3D%3D?uid=0&filename=333.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048"),
                            ContentItem.Image("https://downloader.disk.yandex.ru/preview/2153d566688a75d0f062a5735d124a9f4fb21e5b5106859b62c83d2b1f6f2049/67ac0bca/KwOkaa4_16A0-eBS6lqGoJa68VyyOyd9rOCU23C_tMREektdtbp_9ZRW92rnidHseF00_ysuUihmEjSIsaJqtQ%3D%3D?uid=0&filename=Screenshot_1.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048")
                        )
                    ),
                    ContentList(
                        blockName = "Это 3 тема - блок",
                        content = listOf(
                            ContentItem.Image("https://i.ibb.co/NgVFXxqk/123.png"),
                            ContentItem.Image("https://i.ibb.co/CK0HbxwC/dfas.png"),
                            ContentItem.Text("Солнышко встало на утреннем небе, разгоняя последние тёмные тени ночи. Его лучи мягко касались земли, согревая всё вокруг. Цветы раскрывались, приветствуя его тепло, а воздух наполнился запахом свежести и цветов. В лесу пели птицы, а на поляне играли зайчата. Солнышко радостно светило, обещая всем новый день, полный света и счастья."),
                        )
                    ),
                    ContentList(
                        blockName = "Это ЧЕТВЕРТАЯ тема-блок",
                        content = listOf(
                            ContentItem.Image("https://i.ibb.co/CK0HbxwC/dfas.png"),
                            ContentItem.Text("Солнышко встало на утреннем небе, разгоняя последние тёмные тени ночи. Его лучи мягко касались земли, согревая всё вокруг. Цветы раскрывались, приветствуя его тепло, а воздух наполнился запахом свежести и цветов. В лесу пели птицы, а на поляне играли зайчата. Солнышко радостно светило, обещая всем новый день, полный света и счастья."),
                        )
                    )
                )
            ),

            Lesson(
                title = "Урок 2",
                blocks = listOf(
                    ContentList(
                        blockName = "123",
                        content = listOf(
                            ContentItem.Text("Солнышко встало на утреннем небе, разгоняя последние тёмные тени ночи. Его лучи мягко касались земли, согревая всё вокруг. Цветы раскрывались, приветствуя его тепло, а воздух наполнился запахом свежести и цветов. В лесу пели птицы, а на поляне играли зайчата. Солнышко радостно светило, обещая всем новый день, полный света и счастья."),
                        )
                    )
                )
            ),

            Lesson(
                title = "Урок 3",
                blocks = listOf(
                    ContentList(
                        blockName = "pklask!!!!!!!!!!",
                        content = listOf(
                            ContentItem.Text("Солнышко встало на утреннем небе, разгоняя последние тёмные тени ночи. Его лучи мягко касались земли, согревая всё вокруг. Цветы раскрывались, приветствуя его тепло, а воздух наполнился запахом свежести и цветов. В лесу пели птицы, а на поляне играли зайчата. Солнышко радостно светило, обещая всем новый день, полный света и счастья."),
                            ContentItem.Image("https://i.ibb.co/NgVFXxqk/123.png"),
                            ContentItem.Image("https://i.ibb.co/CK0HbxwC/dfas.png"),
                            ContentItem.Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.."),
                            ContentItem.Image("https://i.ibb.co/NgVFXxqk/123.png"),
                            ContentItem.Image("https://i.ibb.co/CK0HbxwC/dfas.png"),
                            ContentItem.Text("Солнышко встало на утреLorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.ёмные тени ночи. Его лучи мягко касались земли, согревая всё вокруг. Цветы раскрывались, приветствуя его тепло, а воздух наполнился запахом свежести и цветов. В лесу пели птицы, а на поляне играли зайчата. Солнышко радостно светило, обещая всем новый день, полный света и счастья."),

                        )
                    )
                )
            )
        )


        binding.lessonsRecycleView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

       val adapter = LessonsListScreenAdapter(lessons)
        binding.lessonsRecycleView.adapter = adapter


    }
}