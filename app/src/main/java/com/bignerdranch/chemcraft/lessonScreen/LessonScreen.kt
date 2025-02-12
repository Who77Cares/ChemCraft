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
import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.databinding.ActivityLessonScreenBinding

class LessonScreen : AppCompatActivity() {
    private lateinit var binding: ActivityLessonScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val oneContent = mutableListOf<ContentItem>(
            ContentItem.Text("Солнышко встало на утреннем небе, разгоняя последние тёмные тени ночи. Его лучи мягко касались земли, согревая всё вокруг. Цветы раскрывались, приветствуя его тепло, а воздух наполнился запахом свежести и цветов. В лесу пели птицы, а на поляне играли зайчата. Солнышко радостно светило, обещая всем новый день, полный света и счастья."),
            ContentItem.Image("https://downloader.disk.yandex.ru/preview/2153d566688a75d0f062a5735d124a9f4fb21e5b5106859b62c83d2b1f6f2049/67ac0bca/KwOkaa4_16A0-eBS6lqGoJa68VyyOyd9rOCU23C_tMREektdtbp_9ZRW92rnidHseF00_ysuUihmEjSIsaJqtQ%3D%3D?uid=0&filename=Screenshot_1.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048"),
            ContentItem.Text("Котик по имени Мурка сидел на подоконнике и наблюдал за миром с высоты. Его пушистая шёрстка была как мягкое облако, а большие зелёные глаза с интересом следили за каждым движением. Мурка был любителем тёплых местечек и длинных дневных снов. Иногда он лениво растягивался на солнечном диване, потягиваясь и мурлыкая, словно наслаждаясь каждым моментом своей жизни."),
            ContentItem.Image("https://downloader.disk.yandex.ru/preview/b999bdbd0db493450b6b0612a5b80e065b97e3d0589826b48647ce216ce0af69/67ac0ceb/hrJG2GwCV6bYC41plAVy6X7thwC5dKTBksIAL0-v6NplMPGawzJS-yIHcFbd7TBztn8koFmf7ibrjnn55TsG9g%3D%3D?uid=0&filename=123.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048"),
            ContentItem.Text("Далеко в лесу стоял уютный домик. В его окнах горел тёплый свет, а на крыше весело стрекотали птицы. В этом доме было много счастья. Вечером, когда на улице темнело, внутри горел камин, и можно было услышать, как трещат дрова. В доме всегда пахло свежим хлебом, а на кухне стояла чашка с горячим чаем. Это было место, где всегда можно было укрыться от холода и забот, где каждый уголок был полон уюта и тепла."),
            ContentItem.Image("https://downloader.disk.yandex.ru/preview/89bfc97be1d9042b504435114f3f711383b261722abfddbd5b89b14ee37bc32c/67ac0d55/54mfhgKZ0eQbl1BP26JYeWieqr2AKZku9Q4EHTVZd0Gy6nkfyKSKHQug7H7QxjvbYd9HOFWX3x5d-JxEAZZRaQ%3D%3D?uid=0&filename=333.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048"),
            ContentItem.Image("https://downloader.disk.yandex.ru/preview/2153d566688a75d0f062a5735d124a9f4fb21e5b5106859b62c83d2b1f6f2049/67ac0bca/KwOkaa4_16A0-eBS6lqGoJa68VyyOyd9rOCU23C_tMREektdtbp_9ZRW92rnidHseF00_ysuUihmEjSIsaJqtQ%3D%3D?uid=0&filename=Screenshot_1.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048")
        )


        binding.lessonRecycleView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.lessonRecycleView.adapter = ContentAdapter(oneContent)
    }
}