package com.bignerdranch.chemcraft.items_in_lesson.ui.full_screen_img

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bignerdranch.chemcraft.databinding.FragmentFullScreenImageBinding
import com.bumptech.glide.Glide

class FullScreenImageFragment : Fragment() {

    companion object {
        private val IMG_URL = "img_url"
        fun newInstance(imgUrl: String): FullScreenImageFragment {
            return FullScreenImageFragment().apply {
                arguments = Bundle().apply {
                    putString(IMG_URL, imgUrl)
                }
            }
        }
    }



    private var _binding: FragmentFullScreenImageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFullScreenImageBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imagUrl = requireArguments().getString(IMG_URL)


        Glide.with(view)
            .load(imagUrl)
            .into(binding.fullscreenImage)

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}