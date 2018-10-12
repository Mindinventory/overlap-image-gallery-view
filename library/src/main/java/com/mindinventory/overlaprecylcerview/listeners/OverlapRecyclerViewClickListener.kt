package com.mindinventory.overlaprecylcerview.listeners

interface OverlapRecyclerViewClickListener {
    fun onNormalItemClicked(adapterPosition: Int)

    fun onNumberedItemClick(adapterPosition: Int)
}