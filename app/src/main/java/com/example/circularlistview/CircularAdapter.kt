package com.example.circularlistview

import android.view.View


public abstract class CircularAdapter {

    interface CircularItemChangeListener {
        fun onCircularItemChange()
    }

    /**
     * get item count
     * @return numbers of item
     */
    abstract fun getCount(): Int


    /**
     * get all custom views, you should put all views into an ArrayList
     * @return a list of views
     */
    abstract fun getAllViews(): ArrayList<View?>?


    /**
     * get item at index i
     * @param i index of item
     * @return view at position i
     */
    abstract fun getItemAt(i: Int): View?


    /**
     * remove move an item from the list
     * @param i index of item to be removed
     */
    abstract fun removeItemAt(i: Int)


    /**
     * add an item into the list from last
     */
    abstract fun addItem(view: View?)


    /**
     * need to notify parent view when item has been changed
     */
    open fun notifyItemChange() {
        circularItemChangeListener!!.onCircularItemChange()
    }

    private var circularItemChangeListener: CircularItemChangeListener? = null


    open fun setOnItemChangeListener(listener: CircularItemChangeListener?) {
        circularItemChangeListener = listener
    }
}