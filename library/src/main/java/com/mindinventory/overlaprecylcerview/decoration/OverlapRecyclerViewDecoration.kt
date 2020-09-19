package com.mindinventory.overlaprecylcerview.decoration

import android.graphics.Rect
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OverlapRecyclerViewDecoration(
        private val overlapLimit: Int,
        private var overlapPercentage: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val width = view.layoutParams.width

        if (overlapPercentage < 0) {
            overlapPercentage = 0
        } else if (overlapPercentage > 100) {
            overlapPercentage = 100
        }
        val widthPercentage = (overlapPercentage * width * -1) / 100

        //-----get current position of item
        val itemPosition = parent.getChildAdapterPosition(view)

        val isReverseLayout = (parent.layoutManager as LinearLayoutManager).reverseLayout

        //-----avoid first item decoration else it will go of the screen
        if (itemPosition == 0) {
            return
        } else {

            if (isReverseLayout) {
                when {
                    itemPosition <= overlapLimit -> outRect.set(0, 0, widthPercentage, 0)

                    else -> outRect.set(0, 0, 0, 0)
                }
            } else {
                //-----apply decoration
                when {
                    itemPosition <= overlapLimit -> outRect.set(widthPercentage, 0, 0, 0)
                    else -> outRect.set(0, 0, 0, 0)
                }
            }
        }

    }
}


