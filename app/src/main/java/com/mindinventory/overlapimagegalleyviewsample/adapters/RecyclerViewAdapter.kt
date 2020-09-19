package com.mindinventory.overlapimagegalleyviewsample.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.mindinventory.overlapimagegalleyviewsample.models.OverlapImageModel
import com.mindinventory.overlaprecyclerview.R
import com.mindinventory.overlaprecylcerview.adapters.OverlapRecyclerViewAdapter
import com.mindinventory.overlaprecylcerview.utils.TextDrawable
import kotlinx.android.synthetic.main.row_image.view.*

class RecyclerViewAdapter(
        overlapLimit: Int,
        overlapWidthInPercentage: Int
) : OverlapRecyclerViewAdapter<OverlapImageModel, RecyclerViewAdapter.CustomViewHolder>(overlapLimit, overlapWidthInPercentage) {

    override fun createItemViewHolder(parent: ViewGroup): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_image, parent, false)
        return CustomViewHolder(view)
    }

    override fun bindItemViewHolder(viewHolder: CustomViewHolder, position: Int) {
        val currentImageModel = getVisibleItemAt(position)!!
        //----bind data to view
        viewHolder.bind(currentImageModel)
    }

    override fun getItemCount() = visibleItems.size

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /**
         * bind model data to item
         */
        fun bind(overlapImageModel: OverlapImageModel) {
            if (isLastVisibleItemItem(adapterPosition)) {
                //----set text drawable to show count on last imageview
                val text = notVisibleItems.size.toString()
                val drawable = TextDrawable.builder()
                        .beginConfig()
                        .textColor(Color.WHITE)
                        .width(90)
                        .height(90)
                        .endConfig()
                        .buildRound(text, Color.parseColor("#8FAE5D"))
                itemView.imageView.setImageDrawable(drawable)
            } else {
                Glide.with(itemView.imageView.context)
                        .load(overlapImageModel.imageUrl)
                        .apply(RequestOptions.circleCropTransform().priority(Priority.HIGH))
                        .into(itemView.imageView)
            }
        }
    }
}

