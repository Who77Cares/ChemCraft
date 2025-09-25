package com.bignerdranch.chemcraft.local_storage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.chemcraft.databinding.FragmentFavoriteBinding
import com.bignerdranch.chemcraft.lessons.ui.LessonContentAdapter

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: LessonContentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreference = SharedPrefManager(requireContext())
        val favoritesList = sharedPreference.getFavorite()

        if (favoritesList.isEmpty()) {
            binding.myListIsEmpty.visibility = View.VISIBLE
        } else {
            binding.myListIsEmpty.visibility = View.INVISIBLE
        }

        binding.myLessonsRecycleView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}