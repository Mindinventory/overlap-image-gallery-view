package com.mindinventory.overlapimagegalleyviewsample.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.mindinventory.overlapimagegalleyviewsample.models.OverlapImageModel
import com.mindinventory.overlaprecyclerview.R
import com.mindinventory.overlaprecyclerview.databinding.RowImageBinding
import com.mindinventory.overlaprecylcerview.adapters.OverlapRecyclerViewAdapter
import com.mindinventory.overlaprecylcerview.utils.TextDrawable

class RecyclerViewAdapter(
        overlapLimit: Int,
        overlapWidthInPercentage: Int
) : OverlapRecyclerViewAdapter<OverlapImageModel, RecyclerViewAdapter.CustomViewHolder>(overlapLimit, overlapWidthInPercentage) {

    override fun createItemViewHolder(parent: ViewGroup): CustomViewHolder {
        val rowImageBinding = RowImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(rowImageBinding)
    }

    override fun bindItemViewHolder(viewHolder: CustomViewHolder, position: Int) {
        val currentImageModel = getVisibleItemAt(position)!!
        //----bind data to view
        viewHolder.bind(currentImageModel)
    }

    override fun getItemCount() = visibleItems.size

    inner class CustomViewHolder(private val rowImageBinding: RowImageBinding) : RecyclerView.ViewHolder(rowImageBinding.root) {
        /**
         * bind model data to item
         */
        fun bind(overlapImageModel: OverlapImageModel) {
            with(rowImageBinding){
                if (isLastVisibleItemItem(adapterPosition)) {
                    //----set text drawable to show count on last imageview
                    val text = notVisibleItems.size.toString()
                    val drawable = TextDrawable.builder()
                        .beginConfig()
                        .textColor(Color.WHITE)
                        .width(90)
                        .height(90)
                        .endConfig()
                        .buildRound(text, R.color.light_green)
                    imageView.setImageDrawable(drawable)
                } else {
                    Glide.with(imageView.context)
                        .load(overlapImageModel.imageUrl)
                        .apply(RequestOptions.circleCropTransform().priority(Priority.HIGH))
                        .into(imageView)
                }
            }
        }
    }
}

