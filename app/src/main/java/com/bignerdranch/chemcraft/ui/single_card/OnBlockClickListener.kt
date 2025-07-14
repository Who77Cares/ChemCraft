package com.bignerdranch.chemcraft.ui.single_card

import com.bignerdranch.chemcraft.SingleCardItemModel

interface OnBlockClickListener {
    fun onBlockClick(contentList: List<SingleCardItemModel>)
}