package com.example.circularlistview

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.cos
import kotlin.math.sin


class Arclayout : AppCompatActivity() {
    private var j:Int = 45
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arclayout)
        val frameLayout:FrameLayout = findViewById(R.id.main)


        val displayMetrics = DisplayMetrics()
        try {
            windowManager.defaultDisplay.getMetrics(displayMetrics)
        } catch (e: Exception) {
        }
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels

        val numViews = 11
        for (i in 0 until numViews) {
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val v: View = inflater.inflate(R.layout.view_circular_item, null)
            v.id = i

            v.setOnClickListener {
                Toast.makeText(
                    this@Arclayout,
                    "view at index ${v.id} is clicked!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            // Force the views to a nice size (150x100 px) that fits my display.
            // This should of course be done in a display size independent way.
            var lp: FrameLayout.LayoutParams? = null
            if (width in 721..1080) {
                lp = FrameLayout.LayoutParams(120, 180)

            } else if (width <= 720) {
                lp = FrameLayout.LayoutParams(100, 150)
            }

            // Place all views in the center of the layout. We'll transform them
            // away from there in the code below.
            lp?.gravity = Gravity.CENTER
            
            // Set layout params on view.
            v.layoutParams = lp
            // Calculate the angle of the current view. Adjust by 90 degrees to
            // get View 0 at the top. We need the angle in degrees and radians.
            // Calculate the position of the view, offset from center (300 px from
            // center). Again, this should be done in a display size independent way.
            if (numViews == 1){
                oneMemberOnly(i,v,numViews,frameLayout)
            }else if(numViews > 1 && numViews < 8){
                moreViewOnly(i,v,numViews,frameLayout)
            }else if (numViews > 7){
                moreLimitViewOnly(i,v,numViews,frameLayout)
            }
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
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        var width = displayMetrics.widthPixels
        var height = displayMetrics.heightPixels

        val angleDeg = i * 30
        val angleRad = (angleDeg * Math.PI / 180.0f).toFloat()
        if (width in 721..1080) {
            v.translationX = 350 * cos(angleRad.toDouble()).toFloat()
            v.translationY = 350 * sin(angleRad.toDouble()).toFloat()
        } else if (width <= 720) {
            v.translationX = 250 * cos(angleRad.toDouble()).toFloat()
            v.translationY = 250 * sin(angleRad.toDouble()).toFloat()
        }
        // v.rotation = angleDeg + 90.0f
        frameLayout.rotation = 180.0f
        frameLayout.addView(v)
    }

    private fun moreLimitViewOnly(i:Int,v:View,numViews:Int,frameLayout: FrameLayout){
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        var width = displayMetrics.widthPixels
        var height = displayMetrics.heightPixels

        if (i < 7) {
            val angleDeg = i * 30
            val angleRad = (angleDeg * Math.PI / 180.0f).toFloat()
            if (width in 721..1080) {
                v.translationX = 350 * cos(angleRad.toDouble()).toFloat()
                v.translationY = 350 * sin(angleRad.toDouble()).toFloat()
            } else if (width <= 720) {
                v.translationX = 250 * cos(angleRad.toDouble()).toFloat()
                v.translationY = 250 * sin(angleRad.toDouble()).toFloat()
            }
            //  v.rotation = angleDeg + 90.0f
        } else {
            val angleDeg = j
            val angleRad = (angleDeg * Math.PI / 180.0f).toFloat()
            if (width in 721..1080) {
                v.translationX = 200 * cos(angleRad.toDouble()).toFloat()
                v.translationY = 200 * sin(angleRad.toDouble()).toFloat()
            } else if (width <= 720) {
                v.translationX = 120 * cos(angleRad.toDouble()).toFloat()
                v.translationY = 120 * sin(angleRad.toDouble()).toFloat()
            }
            //  v.rotation = angleDeg + 90.0f
            j += 50
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