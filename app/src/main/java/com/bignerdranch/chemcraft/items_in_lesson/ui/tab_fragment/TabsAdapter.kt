package com.bignerdranch.chemcraft.items_in_lesson.ui.tab_fragment

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bignerdranch.chemcraft.items_in_lesson.ui.ItemsFragment
import com.bignerdranch.chemcraft.lesson_task.ui.TaskFragment

class TabsAdapter( host: Fragment,
                   private val lessonId: String,
                   private val cardId: String,
                   private val cardTitle: String): FragmentStateAdapter(host) {

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> ItemsFragment.newInstance(lessonId, cardId, cardTitle)
        else -> TaskFragment.newInstance(lessonId, cardId)
    }

    override fun getItemCount(): Int = 2
}