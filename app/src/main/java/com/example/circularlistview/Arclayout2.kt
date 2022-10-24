package com.example.circularlistview

import android.content.Context
import android.graphics.*
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.cos
import kotlin.math.sin


class Arclayout2 : AppCompatActivity() {
    private var j:Int = 45
    private lateinit var mImageView: ImageView
    private lateinit var bitmap: Bitmap
    private lateinit var canvas: Canvas
    private lateinit var paint: Paint
    private var downX = 0f
    private var downY = 0f
    private var upX = 0f
    private var upY = 0f
    private val dw = 800
    private  val dh = 800

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.arclayout_two_activity)
        val frameLayout:FrameLayout = findViewById(R.id.main2)


        // Initializing the ImageView
        mImageView = findViewById(R.id.lineImg2)

        // Getting the current window dimensions
       //val currentDisplay = frameLayout.currentWindowMetrics


        // Creating a bitmap with fetched dimensions
        bitmap = Bitmap.createBitmap(dw, dh, Bitmap.Config.ARGB_8888)

        // Storing the canvas on the bitmap
        canvas = Canvas(bitmap)

        // Initializing Paint to determine
        // stoke attributes like color and size
        paint = Paint()
        paint.apply {
            isAntiAlias = true
            color = Color.parseColor("#FF74B1")
            strokeWidth = 5F
            style = Paint.Style.STROKE
            pathEffect = DashPathEffect(
                floatArrayOf(20F, 25F, 30F, 40F),
                0F
            )
        }

        // Setting the bitmap on ImageView
        mImageView.setImageBitmap(bitmap)


//        val intervalAngle = 2.0f * Math.PI / 9
//        for (i in 0..9) {
//            canvas?.drawLine(( canvas.width + cos(intervalAngle * i) * canvas.width/3).toFloat(),
//                (canvas.height / 2 + sin(intervalAngle * i) * canvas.width/3).toFloat(), (canvas.height /2).toFloat(),
//                (canvas.width/2).toFloat(),paint)
//            mImageView.invalidate()
//        }





        val numViews = 9
        for (i in 0 until numViews) {
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val v: View = inflater.inflate(R.layout.view_circular_item, null)
            v.id = i

            v.setOnClickListener {
                Toast.makeText(
                    this@Arclayout2,
                    "view at index ${v.id} is clicked!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            // Force the views to a nice size (150x100 px) that fits my display.
            // This should of course be done in a display size independent way.
            val lp = FrameLayout.LayoutParams(200, 200)
            // Place all views in the center of the layout. We'll transform them
            // away from there in the code below.
            lp.gravity = Gravity.CENTER

            // Set layout params on view.
            v.layoutParams = lp
            // Calculate the angle of the current view. Adjust by 90 degrees to
            // get View 0 at the top. We need the angle in degrees and radians.
            // Calculate the position of the view, offset from center (300 px from
            // center). Again, this should be done in a display size independent way.
            if (numViews == 1){
                oneMemberOnly(i,v,numViews,frameLayout)
            }else if(numViews > 1 && numViews < 12){
                moreViewOnly(i,v,numViews,frameLayout)
            }
//            else if (numViews > 7){
//                moreLimitViewOnly(i,v,numViews,frameLayout)
//            }
        }
    }



    private fun oneMemberOnly(i:Int,v:View,numViews:Int,frameLayout: FrameLayout){
        val angleDeg = 90
        val angleRad = (angleDeg * Math.PI / 180.0f).toFloat()
        v.translationX = 150 * cos(angleRad.toDouble()).toFloat()
        v.translationY = 150 * sin(angleRad.toDouble()).toFloat()
        v.rotation = angleDeg + 90.0f
        frameLayout.rotation = 180.0f
        frameLayout.addView(v)
    }

    private fun moreViewOnly(i:Int,v:View,numViews:Int,frameLayout: FrameLayout){
        val angleDeg =  i * 30
        val angleRad = (angleDeg * Math.PI / 180.0f).toFloat()
        v.translationX = 250 * cos(angleRad.toDouble()).toFloat()
        v.translationY = 250 * sin(angleRad.toDouble()).toFloat()

//       v.x =  ((canvas.height/2).toFloat() - v.width / 2 + canvas.width/2 *
//                cos(2.0943951023931953 )).toFloat()
//       v.y =  ((canvas.width/2).toFloat() - v.height / 2 + canvas.width/2 *
//                sin(2.0943951023931953)).toFloat()



        v.rotation = 180.0f
        frameLayout.rotation = 180.0f
        frameLayout.addView(v)
        val intervalAngle = 2.0f * Math.PI / 9
        canvas.drawLine((canvas.width / 2 + cos(2.0943951023931953) * canvas.width/2).toFloat(),
            (canvas.height / 2 + sin(2.0943951023931953) * canvas.width/2).toFloat(),
            (canvas.height/2).toFloat(), (canvas.height/2).toFloat(),paint)



    }

    private fun moreLimitViewOnly(i:Int,v:View,numViews:Int,frameLayout: FrameLayout){
        if (i<7) {
            val angleDeg = i * 32
            val angleRad = (angleDeg * Math.PI / 180.0f).toFloat()
            v.translationX = 300 * cos(angleRad.toDouble()).toFloat()
            v.translationY = 300 * sin(angleRad.toDouble()).toFloat()
            v.rotation = angleDeg + 90.0f
        }else{
            val angleDeg = j
            val angleRad = (angleDeg * Math.PI / 180.0f).toFloat()
            v.translationX = 150 * cos(angleRad.toDouble()).toFloat()
            v.translationY = 150 * sin(angleRad.toDouble()).toFloat()
            v.rotation = angleDeg + 90.0f
            j = j+50
        }
        frameLayout.rotation = 180.0f
        frameLayout.addView(v)
    }

//    private fun moredViewCall(i:Int,v:View,numViews:Int,frameLayout: FrameLayout){
//        if (i > 6) {
//            val angleDeg = j * 270.0f / 3 - 165.0f
//            val angleRad = (angleDeg * Math.PI / 180.0f).toFloat()
//            v.translationX = 155 * cos(angleRad.toDouble()).toFloat()
//            v.translationY = 155 * sin(angleRad.toDouble()).toFloat()
//            v.rotation = angleDeg + 90.0f
//            j++
//        } else {
//            val angleDeg = i * 360.0f / numViews
//            val angleRad = (angleDeg * Math.PI / 180.0f).toFloat()
//            v.translationX = 350 * cos(angleRad.toDouble()).toFloat()
//            v.translationY = 350 * sin(angleRad.toDouble()).toFloat()
//            v.rotation = angleDeg + 90.0f
//        }
//        frameLayout.rotation = 165.0f
//        frameLayout.addView(v)
//    }

}