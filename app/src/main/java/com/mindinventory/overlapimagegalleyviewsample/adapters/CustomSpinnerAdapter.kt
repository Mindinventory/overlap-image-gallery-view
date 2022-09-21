package com.mindinventory.overlapimagegalleyviewsample.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.mindinventory.overlaprecylcerview.animations.OverlapRecyclerViewAnimation

class CustomSpinnerAdapter(
        private val context: Activity,
        private val resouceId: Int,
        private val textviewId: Int,
        list: List<OverlapRecyclerViewAnimation>
) : ArrayAdapter<OverlapRecyclerViewAnimation>(context, resouceId, textviewId, list) {

    override fun getDropDownView(position: Int, view: View?, parent: ViewGroup): View {
        var convertView = view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resouceId, parent, false)
        }
        val rowItem = getItem(position)!!.toString()
        val txtTitle = convertView!!.findViewById<TextView>(textviewId)
        txtTitle.text = rowItem
        return convertView
    }
}
