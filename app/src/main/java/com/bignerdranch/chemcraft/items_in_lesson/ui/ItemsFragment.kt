package com.bignerdranch.chemcraft.items_in_lesson.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.databinding.FragmentItemsBinding
import com.bignerdranch.chemcraft.items_in_lesson.domain.models.ItemModel
import com.bignerdranch.chemcraft.items_in_lesson.ui.full_screen_img.FullScreenImageFragment
import com.bignerdranch.chemcraft.lesson_task.ui.TaskFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemsFragment : Fragment() {
    companion object {

        const val LESSON_ID = "lesson_id"
        const val CARD_ID = "card_id"
        const val CARD_TITLE = "card_title"

        fun newInstance(lessonId: String, cardId: String, cardTitle: String): ItemsFragment {
            return ItemsFragment().apply {
                arguments = Bundle().apply {
                    putString(LESSON_ID, lessonId)
                    putString(CARD_ID, cardId)
                    putString(CARD_TITLE, cardTitle)
                }
            }
        }
    }
    private var _binding: FragmentItemsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ItemsAdapter
    private val viewModel: ItemsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val lessonId = requireArguments().getString(LESSON_ID) ?: " "
        val cardId = requireArguments().getString(CARD_ID) ?: " "


        val title = cardId
        binding.itemTitle.text = title


        Log.d("LessonScreen", "Передаем ID урока: $cardId, ---- $title")

        binding.itemRecycleView.layoutManager = LinearLayoutManager(requireContext())

        adapter = ItemsAdapter() { url ->
            parentFragment?.parentFragmentManager?.commit {
               replace(
                   R.id.rootFragmentContainerView,
                   FullScreenImageFragment.newInstance(url)
               )
               addToBackStack(null)
           }
        }


        binding.itemRecycleView.adapter = adapter



        viewModel.observeState().observe(viewLifecycleOwner) {
            render(it)
        }
        viewModel.setLessonId(lessonId = lessonId, cardId = cardId)
        viewModel.getItems()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun render(state: ItemsState) {
        when(state) {
            ItemsState.Loading -> {
                Log.d("asd", "sad")


            }
            is ItemsState.Content -> {
                Log.d("ItemsActivity", "Получили контент: ${state.items}")
                showContent(items = state.items)
            }

            is ItemsState.Error -> {
                Log.d("asd", "sad")


            }
        }
    }

    private fun showContent(items: List<ItemModel>) {
        adapter.itemsList = items
        adapter.notifyDataSetChanged()

        binding.apply {
            itemTitle.visibility = View.VISIBLE
            itemRecycleView.visibility = View.VISIBLE
        }
    }

}