package com.mindinventory.overlapimagegalleyviewsample.adapters

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mindinventory.overlapimagegalleyviewsample.models.OverlapImageModel
import com.mindinventory.overlaprecyclerview.R
import com.mindinventory.overlaprecylcerview.adapters.OverlapRecyclerViewAdapter
import com.mindinventory.overlaprecylcerview.listeners.OverlapRecyclerViewClickListener
import com.mindinventory.overlaprecylcerview.utils.TextDrawable
import kotlinx.android.synthetic.main.row_image.view.*


class RecyclerViewAdapter(private var mContext: Context, private val listener: OverlapRecyclerViewClickListener,
                          overlapLimit: Int, overlapWidth: Int) : OverlapRecyclerViewAdapter<OverlapImageModel, RecyclerViewAdapter.CustomViewHolder>(overlapLimit, overlapWidth) {


    private val TAG = RecyclerViewAdapter::class.java.simpleName

    override fun createItemViewHolder(parent: ViewGroup): CustomViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.row_image, parent, false)
        return CustomViewHolder(view)
    }

    override fun bindItemViewHolder(holder: CustomViewHolder, position: Int) {
        val mCurrentImageModel = getVisibleItemAt(position)!!

        //----bind data to view
        holder.bind(mCurrentImageModel)
    }


    override fun getItemCount(): Int {
        return visibleItems.size
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var requestOptions: RequestOptions? = null

        /**
         * init request option for glide
         */
        private fun getGlideRequestOptions(): RequestOptions {
            if (requestOptions == null) {
                requestOptions = RequestOptions()
                requestOptions?.error(R.mipmap.ic_launcher)
                requestOptions?.placeholder(R.mipmap.ic_launcher)
            }
            return requestOptions!!
        }

        /**
         * bind model data to item
         */
        fun bind(mImageModel: OverlapImageModel) {

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

                //----load image
                Glide.with(mContext)
                        .load(mImageModel.imageUrl)
                        .apply(getGlideRequestOptions())
                        .into(itemView.imageView)

            }
        }
    }
}

