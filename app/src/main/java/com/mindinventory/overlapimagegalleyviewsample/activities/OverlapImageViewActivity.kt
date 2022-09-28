package com.mindinventory.overlapimagegalleyviewsample.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mindinventory.overlapimagegalleyviewsample.adapters.RecyclerViewAdapter
import com.mindinventory.overlapimagegalleyviewsample.models.OverlapImageModel
import com.mindinventory.overlapimagegalleyviewsample.utils.imagesURLS
import com.mindinventory.overlapimagegalleyviewsample.utils.toast
import com.mindinventory.overlaprecyclerview.R
import com.mindinventory.overlaprecyclerview.databinding.ActivityOverlapImageBinding
import com.mindinventory.overlaprecylcerview.animations.OverlapRecyclerViewAnimation
import com.mindinventory.overlaprecylcerview.listeners.OverlapRecyclerViewClickListener
import com.mindinventory.overlaprecylcerview.utils.Direction


class OverlapImageViewActivity : AppCompatActivity(), OverlapRecyclerViewClickListener {

    private var _binding: ActivityOverlapImageBinding? = null
    private val binding get() = _binding!!

    //------limit number of visibleItems to be overlapped
    private val numberOfItemToBeOverlapped = 25

    //------set value of item overlapping in percentage between 0 to 100
    private val overlapWidthInPercentage = 25

    //------init RecyclerView adapter
    private val topBottomAdapter by lazy {
        RecyclerViewAdapter(numberOfItemToBeOverlapped, overlapWidthInPercentage)
    }
    private val bottomTopAdapter by lazy {
        RecyclerViewAdapter(numberOfItemToBeOverlapped, overlapWidthInPercentage)
    }
    private val leftRightAdapter by lazy {
        RecyclerViewAdapter(numberOfItemToBeOverlapped, overlapWidthInPercentage)
    }
    private val rightLeftAdapter by lazy {
        RecyclerViewAdapter(numberOfItemToBeOverlapped, overlapWidthInPercentage)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOverlapImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerDirectionRadioButtonListener()
        setAnimatedList()
    }

    private fun registerDirectionRadioButtonListener() {
        with(binding.rgDirection)
        {
            setOnCheckedChangeListener { group, checkedId ->
                run {
                    when (checkedId) {
                        R.id.rbLeftToRight -> {
                            setOrientation(Direction.LEFT_TO_RIGHT)
                        }
                        R.id.rbRightToLeft -> {
                            setOrientation(Direction.RIGHT_TO_LEFT)
                        }
                    }
                }
            }
        }

        binding.rbLeftToRight.isChecked = true
    }

    private fun setOrientation(direction: Direction) {
        val topBottomLinearLayoutManager = LinearLayoutManager(
            this@OverlapImageViewActivity,
            LinearLayoutManager.HORIZONTAL,
            direction == Direction.RIGHT_TO_LEFT
        )

        val bottomTopLinearLayoutManager = LinearLayoutManager(
            this@OverlapImageViewActivity,
            LinearLayoutManager.HORIZONTAL,
            direction == Direction.RIGHT_TO_LEFT
        )

        val leftRightLinearLayoutManager = LinearLayoutManager(
            this@OverlapImageViewActivity,
            LinearLayoutManager.HORIZONTAL,
            direction == Direction.RIGHT_TO_LEFT
        )
        val rightLeftLinearLayoutManager = LinearLayoutManager(
            this@OverlapImageViewActivity,
            LinearLayoutManager.HORIZONTAL,
            direction == Direction.RIGHT_TO_LEFT
        )
        with(binding)
        {
            rvTopBottom.layoutManager = topBottomLinearLayoutManager
            rvBottomTop.layoutManager = bottomTopLinearLayoutManager
            rvLeftRight.layoutManager = leftRightLinearLayoutManager
            rvRightLeft.layoutManager = rightLeftLinearLayoutManager
        }
    }

    private fun setAnimatedList() {
        with(topBottomAdapter) {
            binding.rvTopBottom.addItemDecoration(getItemDecoration())
            binding.rvTopBottom.adapter = this
            addAnimation = true
            animationType = OverlapRecyclerViewAnimation.TOP_BOTTOM
            addAll(getDummyArrayList())
            overlapRecyclerViewClickListener = this@OverlapImageViewActivity
            notifyDataSetChanged()
        }

        with(bottomTopAdapter) {
            binding.rvBottomTop.addItemDecoration(getItemDecoration())
            binding.rvBottomTop.adapter = this
            addAnimation = true
            animationType = OverlapRecyclerViewAnimation.BOTTOM_UP
            addAll(getDummyArrayList())
            overlapRecyclerViewClickListener = this@OverlapImageViewActivity
            notifyDataSetChanged()
        }

        with(leftRightAdapter) {
            binding.rvLeftRight.addItemDecoration(getItemDecoration())
            binding.rvLeftRight.adapter = this
            addAnimation = true
            animationType = OverlapRecyclerViewAnimation.LEFT_RIGHT
            addAll(getDummyArrayList())
            overlapRecyclerViewClickListener = this@OverlapImageViewActivity
            notifyDataSetChanged()
        }

        with(rightLeftAdapter) {
            binding.rvRightLeft.addItemDecoration(getItemDecoration())
            binding.rvRightLeft.adapter = this
            addAnimation = true
            animationType = OverlapRecyclerViewAnimation.RIGHT_LEFT
            addAll(getDummyArrayList())
            overlapRecyclerViewClickListener = this@OverlapImageViewActivity
            notifyDataSetChanged()
        }
    }

    /**
     * addItem dummy data to ArrayList
     */
    private fun getDummyArrayList(): java.util.ArrayList<OverlapImageModel> {
        val items = java.util.ArrayList<OverlapImageModel>()

        //-----fill data in to array list
        for (i in imagesURLS.indices) {
            items.add(OverlapImageModel(imagesURLS[i]))
        }
//        for (i in 0..20) {
//            items.add(OverlapImageModel(imageURLs[i % imageURLs.size]))
//        }
        return items
    }

    override fun onNormalItemClicked(adapterPosition: Int) {
        toast(this, "Normal item clicked >> $adapterPosition")
    }

    override fun onNumberedItemClick(adapterPosition: Int) {
        toast(this, "Numbered item clicked >> $adapterPosition")
    }

}
