package com.example.circularlistview

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.RelativeLayout
import androidx.core.view.forEach
import kotlin.math.cos
import kotlin.math.sin


class CircularListView : RelativeLayout, CircularAdapter.CircularItemChangeListener {
    private lateinit var circularTouchListener: CircularTouchListener
    var itemWith = 0f
    var itemHeight = 0f
    var layoutWidth = 0f
    var layoutHeight = 0f
    var layoutCenter_x = 0f
    var layoutCenter_y = 0f
    var radius = 0f
    var crewList = ArrayList<CrewListItem>()

    /**
     * get interval degree of each view
     *
     * @return degree
     */
    var intervalAngle = Math.PI / 4
    private set
    private var pre_IntervalAngle = Math.PI / 4
    var itemViewList: ArrayList<View>? = null
    private lateinit var circularAdapter: CircularAdapter

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    /**
     * initialization
     */
    private fun init() {

        // need to get the real height and width of view
        post {
            Log.e("CircularListView", "get layout width and height")
            layoutWidth = width.toFloat()
            layoutHeight = height.toFloat()
            layoutCenter_x = layoutWidth / 2
            layoutCenter_y = layoutHeight / 2
            radius = layoutWidth / 2.5f
        }
        itemViewList = ArrayList()
        circularTouchListener = CircularTouchListener()
        setOnTouchListener(circularTouchListener)
    }

    /**
     * set radius of circle(distance of list item between center)
     * @param r radius of circle. The default radius is layout width divide by 3
     */
    @JvmName("setRadius1")
    fun setRadius(r: Float) {

        // check minimal value
        var r = r
        r = if (r < 0) 0F else r
        radius = r

        // support dynamically changing
        circularAdapter.notifyItemChange()
    }


    /**
     * set radius of circle(distance of list item between center)
     * @param r radius of circle. The default radius is layout width divide by 3
     */
    @JvmName("setListItem")
    fun setListItem(crewListFrom: ArrayList<CrewListItem>) {
        crewList = crewListFrom
        // support dynamically changing
        circularAdapter.notifyItemChange()
    }

    /**
     * set item click listener
     */
    /**
     * set item click listener
     */
    fun setOnItemClickListener(listener: CircularTouchListener.CircularItemClickListener) {
        circularTouchListener.setItemClickListener(listener)
    }


    override fun onCircularItemChange() {
        setItemPosition()
    }

    /**
     * add your custom items into this view
     *
     * @param adapter initialize your views in adapter
     */
    fun setAdapter(adapter: CircularAdapter) {
        // register item change listener
        circularAdapter = adapter
        circularAdapter.setOnItemChangeListener(this)
        setItemPosition()
    }

    /**
     * set the circular position of each item
     */
    private fun setItemPosition() {
        val itemCount = circularAdapter.getCount()
        val existChildCount = childCount
        val isLayoutEmpty = existChildCount == 0
        pre_IntervalAngle = if (isLayoutEmpty) 0.0 else 2.0f * Math.PI / existChildCount.toDouble()
        intervalAngle = 2.0f * Math.PI / itemCount.toDouble()


        // add all item view into parent layout
        for (i in 0 until circularAdapter.getCount()) {
            val item = circularAdapter.getItemAt(i)

            // add item if no parent
            if (item!!.parent == null) {
                item.visibility = INVISIBLE
                addView(item)
                println("do add :$item")
            }

            // wait for view drawn to get width and height
            item.post {
                itemWith = item.width.toFloat()
                itemHeight = item.height.toFloat()
                /*
                             * position items according to circle formula
                             * margin left -> x = h + r * cos(theta)
                             * margin top -> y = k + r * sin(theta)
                             *
                             */
                val valueAnimator = ValueAnimator()
                valueAnimator.setFloatValues(pre_IntervalAngle.toFloat(), intervalAngle.toFloat())
                valueAnimator.duration = 500
                valueAnimator.interpolator = OvershootInterpolator()
                valueAnimator.addUpdateListener { animation ->
                    val value = animation.animatedValue as Float
                    val params = item.layoutParams as LayoutParams

                    println("dddddddd"+(layoutCenter_x - itemWith / 2 + radius *
                            cos(i * value + MoveAccumulator * Math.PI )).toInt())
                    println("dddddddds"+(layoutCenter_y - itemHeight / 2 + radius *
                            sin(i * value + MoveAccumulator * Math.PI)).toInt())


                    params.setMargins(
                        (layoutCenter_x - itemWith / 2 + radius *
                                cos(i * value + MoveAccumulator * Math.PI )).toInt(),
                        (layoutCenter_y - itemHeight / 2 + radius *
                                sin(i * value + MoveAccumulator * Math.PI)).toInt(),
                        0,
                        0
                    )



                    item.layoutParams = params
                }



                valueAnimator.start()
                item.visibility = VISIBLE
            }
        }

        // remove item from parent if it has been remove from list
        for (i in itemViewList!!.indices) {
            val itemAfterChanged = itemViewList!![i]
            if (circularAdapter.getAllViews()!!.indexOf(itemAfterChanged) == -1) {
                println("do remove :$itemAfterChanged")
                removeView(itemAfterChanged)
            }
        }
        itemViewList = circularAdapter.getAllViews()!!.clone() as ArrayList<View>
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var paint = Paint().apply {
            isAntiAlias = true
            strokeWidth = 5F
            style = Paint.Style.STROKE
            pathEffect = DashPathEffect(
                floatArrayOf(20F, 25F, 30F, 40F),
                0F
            )
        }
        intervalAngle = 2.0f * Math.PI / circularAdapter.getCount()
        for (i in crewList.indices) {
            if (i != 5 && i != 6 ) {
                if (crewList[i].lineFlag) {
                    if (crewList[0].firstName == "ADD" && i==0) {
                        paint.color = Color.parseColor("#808080")
                        canvas?.drawLine(
                            (width / 2 + cos(intervalAngle * i) * radius).toFloat(),
                            (height / 2 + sin(intervalAngle * i) * radius).toFloat(),
                            layoutCenter_x,
                            layoutCenter_x,
                            paint
                        )
                    }else{
                        paint.color = Color.parseColor("#FF74B1")
                        canvas?.drawLine(
                            (width / 2 + cos(intervalAngle * i) * radius).toFloat(),
                            (height / 2 + sin(intervalAngle * i) * radius).toFloat(),
                            layoutCenter_x,
                            layoutCenter_x,
                            paint
                        )
                    }
                }
            }
        }
    }




    companion object {
        var MoveAccumulator = 0f
    }
}