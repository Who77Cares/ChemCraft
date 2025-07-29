package com.bignerdranch.chemcraft.ui.test

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.ui.test.model.AllTestResultModel

class AllTestResult : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_test_result)


        recyclerView = findViewById(R.id.testResultRecycleView)

        val layoutManager = GridLayoutManager(this, 3, GridLayoutManager.HORIZONTAL, false)
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