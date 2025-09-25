package com.bignerdranch.chemcraft.lesson_task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.lesson_task.model.AllTestResultModel
import com.bignerdranch.chemcraft.lesson_task.ui.answer_screen.AllTestResultAdapter

class AllTestResult : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_test_result)


        recyclerView = findViewById(R.id.testResultRecycleView)

        val layoutManager = GridLayoutManager(this, 10)
        recyclerView.layoutManager = layoutManager

        val adapter = AllTestResultAdapter(
            this
        )

        recyclerView.adapter = adapter



        val moke  = listOf<AllTestResultModel>(
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
            AllTestResultModel("j qwe jiopnqw jepiub qwyub eouyb dfpj", "uidj89djsdijhfiudshfpjin  qwijhbe b qjwheb hjbw qee qwbnehji hwqbn   bwqohe ubuhowepiufkasldj", 3),
        )


        adapter.setItems(moke)
    }
}