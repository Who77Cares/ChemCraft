package com.example.chemcraft

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class CardsScreen : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var headText: TextView
    private lateinit var searchBox: TextInputEditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cards_screen)

        val cards: List<Card> = listOf(
            Card("First",
                "looong",
                "https://downloader.disk.yandex.ru/preview/86f6cd2894915660fd789ba777b2ca882b6d9d777418a0516e0144377614fb39/676c37a4/i9ukvIRh-kV9k77sKQBzElgJeWMBjq1RHe4xLhibkbZ-B87doJjrED5EOpcTsSySuE_dF2Wy3EwMWZjDqXKsCA%3D%3D?uid=0&filename=cell.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048",
                false,
                false),
            Card("Second",
                "looong",
                "https://downloader.disk.yandex.ru/preview/df00a98c8f1dd3c96a4b1d092704ce36aed4711fcec3ded83d1c42ebc23e913e/676c37f5/Gnf-XRIbbQ7I5exYZHxDaEc_ZxwBAK6liCD3CRLMbIT5gyPERSTy4mZB1vDy_no3D6cG40MGH1XP5DrkMbGRyQ%3D%3D?uid=0&filename=depilation.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048",
                false,
                false),
            Card("Third",
                "looong",
                "https://downloader.disk.yandex.ru/preview/2aba437e28d6db50a4892e9da972e5b9d92d0535d2792fbac331d94adabdf4f1/676c3812/QDewBICQ_FDD800yXTNq0IsRgVUa0qme5nJl1hSzZkPNHY85fNWAlntVNmhz7glkFyoX2LeUZv2N-UvFF4d8CQ%3D%3D?uid=0&filename=living-tissue.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048",
                false,
                true),
            Card("First",
                "looong",
                "https://downloader.disk.yandex.ru/preview/86f6cd2894915660fd789ba777b2ca882b6d9d777418a0516e0144377614fb39/676c37a4/i9ukvIRh-kV9k77sKQBzElgJeWMBjq1RHe4xLhibkbZ-B87doJjrED5EOpcTsSySuE_dF2Wy3EwMWZjDqXKsCA%3D%3D?uid=0&filename=cell.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048",
                false,
                false),
            Card("Second",
                "looong",
                "https://downloader.disk.yandex.ru/preview/df00a98c8f1dd3c96a4b1d092704ce36aed4711fcec3ded83d1c42ebc23e913e/676c37f5/Gnf-XRIbbQ7I5exYZHxDaEc_ZxwBAK6liCD3CRLMbIT5gyPERSTy4mZB1vDy_no3D6cG40MGH1XP5DrkMbGRyQ%3D%3D?uid=0&filename=depilation.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048",
                false,
                false),
            Card("Third",
                "looong",
                "https://downloader.disk.yandex.ru/preview/2aba437e28d6db50a4892e9da972e5b9d92d0535d2792fbac331d94adabdf4f1/676c3812/QDewBICQ_FDD800yXTNq0IsRgVUa0qme5nJl1hSzZkPNHY85fNWAlntVNmhz7glkFyoX2LeUZv2N-UvFF4d8CQ%3D%3D?uid=0&filename=living-tissue.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048",
                false,
                true),
            Card("First",
                "looong",
                "https://downloader.disk.yandex.ru/preview/86f6cd2894915660fd789ba777b2ca882b6d9d777418a0516e0144377614fb39/676c37a4/i9ukvIRh-kV9k77sKQBzElgJeWMBjq1RHe4xLhibkbZ-B87doJjrED5EOpcTsSySuE_dF2Wy3EwMWZjDqXKsCA%3D%3D?uid=0&filename=cell.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048",
                false,
                false),
            Card("Second",
                "looong",
                "https://downloader.disk.yandex.ru/preview/df00a98c8f1dd3c96a4b1d092704ce36aed4711fcec3ded83d1c42ebc23e913e/676c37f5/Gnf-XRIbbQ7I5exYZHxDaEc_ZxwBAK6liCD3CRLMbIT5gyPERSTy4mZB1vDy_no3D6cG40MGH1XP5DrkMbGRyQ%3D%3D?uid=0&filename=depilation.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048",
                false,
                false),
            Card("Third",
                "looong",
                "https://downloader.disk.yandex.ru/preview/2aba437e28d6db50a4892e9da972e5b9d92d0535d2792fbac331d94adabdf4f1/676c3812/QDewBICQ_FDD800yXTNq0IsRgVUa0qme5nJl1hSzZkPNHY85fNWAlntVNmhz7glkFyoX2LeUZv2N-UvFF4d8CQ%3D%3D?uid=0&filename=living-tissue.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048",
                false,
                true),
            Card("First",
                "looong",
                "https://downloader.disk.yandex.ru/preview/86f6cd2894915660fd789ba777b2ca882b6d9d777418a0516e0144377614fb39/676c37a4/i9ukvIRh-kV9k77sKQBzElgJeWMBjq1RHe4xLhibkbZ-B87doJjrED5EOpcTsSySuE_dF2Wy3EwMWZjDqXKsCA%3D%3D?uid=0&filename=cell.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048",
                false,
                false),
            Card("Second",
                "looong",
                "https://downloader.disk.yandex.ru/preview/df00a98c8f1dd3c96a4b1d092704ce36aed4711fcec3ded83d1c42ebc23e913e/676c37f5/Gnf-XRIbbQ7I5exYZHxDaEc_ZxwBAK6liCD3CRLMbIT5gyPERSTy4mZB1vDy_no3D6cG40MGH1XP5DrkMbGRyQ%3D%3D?uid=0&filename=depilation.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048",
                false,
                false),
            Card("Third",
                "looong",
                "https://downloader.disk.yandex.ru/preview/2aba437e28d6db50a4892e9da972e5b9d92d0535d2792fbac331d94adabdf4f1/676c3812/QDewBICQ_FDD800yXTNq0IsRgVUa0qme5nJl1hSzZkPNHY85fNWAlntVNmhz7glkFyoX2LeUZv2N-UvFF4d8CQ%3D%3D?uid=0&filename=living-tissue.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048",
                false,
                true),
            )

        recyclerView = findViewById(R.id.card_list)
        headText = findViewById(R.id.head_text)
        searchBox = findViewById(R.id.search_textInputEditText)

        val adapter = CardScreenAdapter(cards)
        recyclerView.adapter = adapter

    }
}