package com.example.circularlistview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import kotlin.math.cos
import kotlin.math.sin


class DrawLines : View{
    var itemWith = 0f
    var itemHeight = 0f
    var layoutWidth = 0f
    var layoutHeight = 0f
    var layoutCenter_x = 0f
    var layoutCenter_y = 0f
    var radius = 0f

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
            radius = layoutWidth / 3
        }
        itemViewList = ArrayList()
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
      //  circularAdapter.notifyItemChange()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var paint = Paint().apply {
            isAntiAlias = true
            color = Color.parseColor("#FF74B1")
            strokeWidth = 5F
            style = Paint.Style.STROKE
            pathEffect = DashPathEffect(
                floatArrayOf(20F, 25F, 30F, 40F),
                0F
            )
        }
        intervalAngle = 2.0f * Math.PI / 3
            canvas?.drawLine((width / 2 + cos(intervalAngle * 3) * radius).toFloat(),
                (height / 2 + sin(intervalAngle * 3) * radius).toFloat(),layoutCenter_x,layoutCenter_x,paint)


    }

}