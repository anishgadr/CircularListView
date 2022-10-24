package com.example.circularlistview

import android.view.MotionEvent
import android.view.View
import android.view.animation.OvershootInterpolator
import android.view.animation.ScaleAnimation
import android.widget.RelativeLayout


class CircularTouchListener: View.OnTouchListener {


    interface CircularItemClickListener {
        fun onItemClick(view: View?, index: Int)
    }

    private var itemClickListener: CircularItemClickListener? = null
    private var init_x = 0f
    private var init_y = 0f
    private var pre_x = 0f
    private var pre_y = 0f
    private var cur_x = 0f
    private var cur_y = 0f
    private var move_x = 0f
    private var move_y = 0f
    private val minClickDistance = 30.0f
    private val minMoveDistance = 30.0f
    private val mMovingSpeed = 2000.0f // default is 2000, larger > faster

    private var isCircularMoving =
        false // ensure that item click only triggered when it's not moving


    fun setItemClickListener(listener: CircularItemClickListener?) {
        itemClickListener = listener
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        val circularView = v as CircularListView
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                cur_x = event.x
                cur_y = event.y
                init_x = event.x
                init_y = event.y
                pre_x = cur_x
                pre_y = cur_y
                cur_x = event.x
                cur_y = event.y
                var diff_x = cur_x - pre_x
                var diff_y = cur_y - pre_y
                move_x = init_x - cur_x
                move_y = init_y - cur_y
                val moveDistance =
                    Math.sqrt((move_x * move_x + move_y * move_y).toDouble()).toFloat()
                if (cur_y >= circularView.layoutCenter_y) diff_x = -diff_x
                if (cur_x <= circularView.layoutCenter_x) diff_y = -diff_y

                // should rotate the layout
                if (moveDistance > minMoveDistance) {
                    isCircularMoving = true
                    CircularListView.MoveAccumulator += (diff_x + diff_y) / mMovingSpeed

                    // calculate new position around circle
                    var i = 0
                    while (i < circularView.itemViewList!!.size) {
                        val idx = i
                        val itemView = circularView.itemViewList!![i]
                        itemView.post {
                            val params = itemView.layoutParams as RelativeLayout.LayoutParams
                            params.setMargins(
                                (circularView.layoutCenter_x - circularView.itemWith / 2 + circularView.radius * Math.cos(
                                    idx * circularView.intervalAngle +
                                            CircularListView.MoveAccumulator * Math.PI * 2
                                )).toInt(),
                                (circularView.layoutCenter_y - circularView.itemHeight / 2 + circularView.radius * Math.sin(
                                    idx * circularView.intervalAngle +
                                            CircularListView.MoveAccumulator * Math.PI * 2
                                )).toInt(),
                                0,
                                0
                            )
                            itemView.layoutParams = params
                            itemView.requestLayout()
                        }
                        i++
                    }
                }
                return true
            }
//            MotionEvent.ACTION_MOVE -> {
//                pre_x = cur_x
//                pre_y = cur_y
//                cur_x = event.x
//                cur_y = event.y
//                var diff_x = cur_x - pre_x
//                var diff_y = cur_y - pre_y
//                move_x = init_x - cur_x
//                move_y = init_y - cur_y
//                val moveDistance =
//                    Math.sqrt((move_x * move_x + move_y * move_y).toDouble()).toFloat()
//                if (cur_y >= circularView.layoutCenter_y) diff_x = -diff_x
//                if (cur_x <= circularView.layoutCenter_x) diff_y = -diff_y
//                if (moveDistance > minMoveDistance) {
//                    isCircularMoving = true
//                    CircularListView.MoveAccumulator += (diff_x + diff_y) / mMovingSpeed
//                    var i = 0
//                    while (i < circularView.itemViewList!!.size) {
//                        val idx = i
//                        val itemView = circularView.itemViewList!![i]
//                        itemView.post {
//                            val params = itemView.layoutParams as RelativeLayout.LayoutParams
//                            params.setMargins(
//                                (circularView.layoutCenter_x - circularView.itemWith / 2 + circularView.radius * Math.cos(
//                                    idx * circularView.intervalAngle +
//                                            CircularListView.MoveAccumulator * Math.PI * 2
//                                )).toInt(),
//                                (circularView.layoutCenter_y - circularView.itemHeight / 2 + circularView.radius * Math.sin(
//                                    idx * circularView.intervalAngle +
//                                            CircularListView.MoveAccumulator * Math.PI * 2
//                                )).toInt(),
//                                0,
//                                0
//                            )
//                            itemView.layoutParams = params
//                            itemView.requestLayout()
//                        }
//                        i++
//                    }
//                }
//                return true
//            }
            MotionEvent.ACTION_UP -> {

                // it is an click action if move distance < min distance
               val moveDistance = Math.sqrt((move_x * move_x + move_y * move_y).toDouble()).toFloat()
                if (moveDistance < minClickDistance && !isCircularMoving) {
                    var i = 0
                    while (i < circularView.itemViewList!!.size) {
                        val view = circularView.itemViewList!![i]
                        if (isTouchInsideView(cur_x, cur_y, view)) {
                            itemClickListener!!.onItemClick(view, i)

                            // set click animation
                            val animation = ScaleAnimation(
                                0.5f, 1.0f, 0.5f, 1.0f,
                                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                                ScaleAnimation.RELATIVE_TO_SELF, 0.5f
                            )
                            animation.duration = 300
                            animation.interpolator = OvershootInterpolator()
                            view.startAnimation(animation)
                            break
                        }
                        i++
                    }
                }
                isCircularMoving = false // reset moving state when event ACTION_UP
                return true
            }
        }
        return false
    }


    /**
     *
     * @param x touch position of x
     * @param y touch position of y
     * @param view the view that you want to know if we touch inside it
     * @return true or false whether we are actually touch the view
     */
//    private fun isTouchInsideView(x: Float, y: Float, view: View): Boolean {
//        val left = view.x
//        val top = view.y
//        val wid = view.width.toFloat()
//        val h = view.height.toFloat()
//        return x > left && x < left + wid && y > top && y < top + h
//    }

   private fun isTouchInsideView(x: Float, y: Float, view: View): Boolean {
        val left = view.x
        val top = view.y
        val wid = view.width.toFloat()
        val h = view.height.toFloat()
        return x > left && x < left + wid && y > top && y < top + h
    }

}