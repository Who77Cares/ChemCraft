package com.bignerdranch.chemcraft.items_in_lesson.ui.tab_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.databinding.FragmentTabsBinding
import com.bignerdranch.chemcraft.items_in_lesson.ui.full_screen_img.FullScreenImageFragment
import com.google.android.material.tabs.TabLayoutMediator

class TabsFragment: Fragment() {

    private var _binding: FragmentTabsBinding? = null
    private val binding get() = _binding!!

    private var tabMediator: TabLayoutMediator? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTabsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lessonId = requireArguments().getString("lesson_id").orEmpty()
        val cardId   = requireArguments().getString("card_id").orEmpty()
        val title    = requireArguments().getString("card_title").orEmpty()
        val startTab = requireArguments().getInt("start_tab", 0)

        binding.viewPager.adapter = TabsAdapter(this, lessonId, cardId, title)
        binding.viewPager.setCurrentItem(0, false)

        tabMediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            tab.text = if (pos == 0) title.toString() else "Задание-cardId карточки-мок %${cardId}"
        }.apply { attach() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tabMediator?.detach()
        tabMediator = null
        _binding = null
    }

//    private fun openFullScreen(imageUrl: String) {
//        val fragment = FullScreenImageFragment.newInstance(imageUrl)
//        parentFragmentManager.beginTransaction()
//            .replace(R.id.rootFragmentContainerView, fragment)
//            .addToBackStack(null) // Кладём TabsFragment в стек
//            .commit()
//    }

}