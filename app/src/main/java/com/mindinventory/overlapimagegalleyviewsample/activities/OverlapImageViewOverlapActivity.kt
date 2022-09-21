package com.mindinventory.overlapimagegalleyviewsample.activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mindinventory.overlapimagegalleyviewsample.adapters.CustomSpinnerAdapter
import com.mindinventory.overlapimagegalleyviewsample.adapters.RecyclerViewAdapter
import com.mindinventory.overlapimagegalleyviewsample.models.OverlapImageModel
import com.mindinventory.overlapimagegalleyviewsample.utils.imageURLs
import com.mindinventory.overlapimagegalleyviewsample.utils.toast
import com.mindinventory.overlaprecyclerview.R
import com.mindinventory.overlaprecyclerview.databinding.ActivityMainBinding
import com.mindinventory.overlaprecylcerview.animations.OverlapRecyclerViewAnimation
import com.mindinventory.overlaprecylcerview.listeners.OverlapRecyclerViewClickListener

class OverlapImageViewOverlapActivity : AppCompatActivity(), OverlapRecyclerViewClickListener {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    //------limit number of visibleItems to be overlapped
    private val numberOfItemToBeOverlapped = 10

    //------set value of item overlapping in percentage between 0 to 100
    private val overlapWidthInPercentage = 50

    //------init RecyclerView adapter
    private val adapter by lazy {
        RecyclerViewAdapter(numberOfItemToBeOverlapped, overlapWidthInPercentage)
    }

    val animationList = arrayListOf(
            OverlapRecyclerViewAnimation.BOTTOM_UP,
            OverlapRecyclerViewAnimation.TOP_BOTTOM,
            OverlapRecyclerViewAnimation.LEFT_RIGHT,
            OverlapRecyclerViewAnimation.RIGHT_LEFT
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAnimationDropDown()
        setDirectionDropDown()
    }

    private fun setAnimationDropDown() {
        val spinnerAdapter = CustomSpinnerAdapter(
            this,
            R.layout.row_spinner_item,
            R.id.tvTitle, animationList
        )
        with(binding)
        {
            //select animation type
            spChooseAnimation.adapter = spinnerAdapter
            spChooseAnimation.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    adapter.animationType = animationList[position]
                    adapter.notifyDataSetChanged()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
        }
    }

    private fun setDirectionDropDown() {
        with(binding){
            spChooseDirection.adapter = ArrayAdapter(
                this@OverlapImageViewOverlapActivity,
                R.layout.row_spinner_item,
                R.id.tvTitle,
                arrayListOf("Left to right", "Right to left")
            )

            spChooseDirection.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val layoutManager = LinearLayoutManager(
                        this@OverlapImageViewOverlapActivity,
                        LinearLayoutManager.HORIZONTAL,
                        position != 0
                    )
                    recyclerView.layoutManager = layoutManager
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
            recyclerView.addItemDecoration(adapter.getItemDecoration())
            recyclerView.adapter = adapter
            adapter.addAnimation = true
            adapter.animationType = OverlapRecyclerViewAnimation.BOTTOM_UP
            adapter.addAll(getDummyArrayList())
            adapter.overlapRecyclerViewClickListener = this@OverlapImageViewOverlapActivity
        }
    }

    /**
     * addItem dummy data to ArrayList
     */
    private fun getDummyArrayList(): java.util.ArrayList<OverlapImageModel> {
        val items = java.util.ArrayList<OverlapImageModel>()

        //-----fill data in to array list
        for (i in 0..20) {
            items.add(OverlapImageModel(imageURLs[i % imageURLs.size]))
        }
        return items
    }

    override fun onNormalItemClicked(adapterPosition: Int) {
        toast(this, "Normal item clicked >> $adapterPosition")
    }

    override fun onNumberedItemClick(adapterPosition: Int) {
        toast(this, "Numbered item clicked >> $adapterPosition")
    }

}
