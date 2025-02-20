package com.bignerdranch.chemcraft.test

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.databinding.ActivityTestScreenBinding

class TestScreen : AppCompatActivity() {

    private lateinit var binding: ActivityTestScreenBinding
    private lateinit var testAdapter: TestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val a = Test("1", "First", listOf(
            TestContent("Describe fish anatomy", "","1221", 2, true),
            TestContent("Join to us", "", "No yes no", 4, false),
            TestContent("Describe fish anatomy", "","1221", 2, true),
            TestContent("Describe fish anatomy", "","1221", 2, true),
            TestContent("Describe fish anatomy", "","1221", 2, true),
            TestContent("Describe fish anatomy", "","1221", 2, true),
            TestContent("Describe fish anatomy", "","1221", 2, true),
            TestContent("Describe fish anatomy", "","1221", 2, true),
            TestContent("Describe fish anatomy", "","1221", 2, true)
        ))

        testAdapter = TestAdapter(a.blocks)

        binding.testBlocksRecycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.testBlocksRecycleView.adapter = testAdapter

    }
}