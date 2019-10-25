package com.mindinventory.overlaprecylcerview.adapters

import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.mindinventory.overlaprecylcerview.R
import com.mindinventory.overlaprecylcerview.animations.OverlapRecyclerViewAnimation
import com.mindinventory.overlaprecylcerview.decoration.OverlapRecyclerViewDecoration
import com.mindinventory.overlaprecylcerview.listeners.OverlapRecyclerViewClickListener
import java.util.*


abstract class OverlapRecyclerViewAdapter<S, T : RecyclerView.ViewHolder?>(
        private var overlapLimit: Int = 0,
        private val overlapWidth: Int = 0
) : RecyclerView.Adapter<T>() {

    //S = Model , T = RecyclerView.ViewHolder

    //----list of visible items
    protected var visibleItems: MutableList<S> = ArrayList()

    //----list of all items
    private var allItems: MutableList<S> = ArrayList()

    //----list of items that are not visible
    protected var notVisibleItems: MutableList<S> = ArrayList()
    lateinit var overlapRecyclerViewClickListener: OverlapRecyclerViewClickListener

    //to manage animation
    var lastPosition = -1

    //is animation enabled by user
    var addAnimation = false

    //default animation
    var animationType = OverlapRecyclerViewAnimation.LEFT_RIGHT

    /**
     * create view holder of recycler view item
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        return createItemViewHolder(parent)
    }


    /**
     * bind recycler view item with data
     */
    override fun onBindViewHolder(viewHolder: T, position: Int) {
        if (addAnimation) {
            addAnimation(position, viewHolder)
            lastPosition = position
        }
        bindItemViewHolder(viewHolder, position)

        //----manage item click
        if (::overlapRecyclerViewClickListener.isInitialized)
            viewHolder!!.itemView.setOnClickListener {

                if (isLastVisibleItemItem(position)) {
                    overlapRecyclerViewClickListener.onNumberedItemClick(position)
                } else {
                    overlapRecyclerViewClickListener.onNormalItemClicked(position)
                }
            }
    }

    /**
     * add animation to recyclerview
     */
    private fun addAnimation(position: Int, viewHolder: T) {
        val animation = when (animationType) {
            OverlapRecyclerViewAnimation.LEFT_RIGHT -> {
                AnimationUtils.loadAnimation(viewHolder?.itemView?.context,
                        if (position > lastPosition)
                            R.anim.right_from_left
                        else
                            R.anim.left_from_right)
            }

            OverlapRecyclerViewAnimation.RIGHT_LEFT -> {
                AnimationUtils.loadAnimation(viewHolder?.itemView?.context,
                        if (position > lastPosition)
                            R.anim.left_from_right
                        else
                            R.anim.right_from_left)
            }

            OverlapRecyclerViewAnimation.TOP_BOTTOM -> {
                AnimationUtils.loadAnimation(viewHolder?.itemView?.context,
                        if (position > lastPosition)
                            R.anim.bottom_from_top
                        else
                            R.anim.top_from_bottom)
            }

            OverlapRecyclerViewAnimation.BOTTOM_UP -> {
                AnimationUtils.loadAnimation(viewHolder?.itemView?.context,
                        if (position > lastPosition)
                            R.anim.top_from_bottom
                        else
                            R.anim.bottom_from_top)
            }
        }

        viewHolder?.itemView?.startAnimation(animation)
    }

    /**
     * get count for visible count
     */
    override fun getItemCount(): Int {
        return visibleItems.size
    }

    /**
     * abstract method to create custom view holder
     */
    protected abstract fun createItemViewHolder(parent: ViewGroup): T

    /**
     * abstract method to bind custom data
     */
    protected abstract fun bindItemViewHolder(viewHolder: T, position: Int)


    /**
     * get visible item at
     */
    fun getVisibleItemAt(position: Int): S? {
        return if (position != -1 && position <= overlapLimit) visibleItems[position] else null
    }

    /**
     * add items to list
     */
    fun addAll(items: ArrayList<S>, clearPrevious: Boolean = false) {
        if (clearPrevious) {
            visibleItems.clear()
            notVisibleItems.clear()
        }

        if (items.size <= overlapLimit) {
            overlapLimit = items.size
        }

        for (mImageModel in items) {
            if (this.visibleItems.size <= overlapLimit) {
                this.visibleItems.add(mImageModel)
            } else {
                this.notVisibleItems.add(mImageModel)
            }
        }

        this.allItems.addAll(items)
        notifyDataSetChanged()
    }

    fun removeItem(pos: Int) {
        if (pos < 0) {
            visibleItems.removeAt(pos)
            notifyItemRemoved(pos)
        }
    }

    fun isLastVisibleItemItem(position: Int): Boolean {
        return position == overlapLimit
    }

    fun getItemDecoration(): OverlapRecyclerViewDecoration {
        return OverlapRecyclerViewDecoration(overlapLimit, overlapWidth)
    }

    override fun onViewDetachedFromWindow(holder: T) {
        super.onViewDetachedFromWindow(holder)
        if (addAnimation)
            holder?.itemView?.clearAnimation()
    }
}