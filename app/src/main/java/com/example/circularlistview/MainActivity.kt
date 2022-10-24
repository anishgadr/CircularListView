package com.example.circularlistview

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.circularlistview.CircularTouchListener.CircularItemClickListener
import io.getstream.avatarview.AvatarView


class MainActivity : AppCompatActivity() {
    private lateinit var adapter: CircularItemAdapter
    private val crewItemList = ArrayList<CrewListItem>()
    private val crewItemListNew = ArrayList<CrewListItem>()
    private var modelSize = 0
    private var counterList = 0;





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val crewListItem = CrewListItem("AG","",true,"G","123456","https://res.cloudinary.com/dqemti5ft/image/upload/ar_1:1,b_rgb:262c35,bo_5px_solid_rgb:fbfbfb,c_fill,g_auto,r_max/v1546944526/KidsHelpLIne_csqjyf.jpg","https://res.cloudinary.com/dqemti5ft/image/upload/ar_1:1,b_rgb:262c35,bo_5px_solid_rgb:fbfbfb,c_fill,g_auto,r_max/v1546944526/KidsHelpLIne_csqjyf.jpg",2021,true)
        crewItemList.add(crewListItem)
        val crewListItem1 = CrewListItem("AB","",true,"G","123456","https://res.cloudinary.com/dqemti5ft/image/upload/ar_1:1,b_rgb:262c35,bo_5px_solid_rgb:fbfbfb,c_fill,g_auto,r_max/v1546944526/KidsHelpLIne_csqjyf.jpg","https://res.cloudinary.com/dqemti5ft/image/upload/ar_1:1,b_rgb:262c35,bo_5px_solid_rgb:fbfbfb,c_fill,g_auto,r_max/v1546944526/KidsHelpLIne_csqjyf.jpg",2021,true)
        crewItemList.add(crewListItem1)
        val crewListItem2 = CrewListItem("AC","",true,"G","123456","https://res.cloudinary.com/dqemti5ft/image/upload/ar_1:1,b_rgb:262c35,bo_5px_solid_rgb:fbfbfb,c_fill,g_auto,r_max/v1546944526/KidsHelpLIne_csqjyf.jpg","https://res.cloudinary.com/dqemti5ft/image/upload/ar_1:1,b_rgb:262c35,bo_5px_solid_rgb:fbfbfb,c_fill,g_auto,r_max/v1546944526/KidsHelpLIne_csqjyf.jpg",2021,true)
        crewItemList.add(crewListItem2)
        val crewListItem3 = CrewListItem("AD","",true,"G","123456","https://res.cloudinary.com/dqemti5ft/image/upload/ar_1:1,b_rgb:262c35,bo_5px_solid_rgb:fbfbfb,c_fill,g_auto,r_max/v1546944526/KidsHelpLIne_csqjyf.jpg","https://res.cloudinary.com/dqemti5ft/image/upload/ar_1:1,b_rgb:262c35,bo_5px_solid_rgb:fbfbfb,c_fill,g_auto,r_max/v1546944526/KidsHelpLIne_csqjyf.jpg",2021,true)
        crewItemList.add(crewListItem3)
        val crewListItem4 = CrewListItem("AE","",true,"G","123456","https://res.cloudinary.com/dqemti5ft/image/upload/ar_1:1,b_rgb:262c35,bo_5px_solid_rgb:fbfbfb,c_fill,g_auto,r_max/v1546944526/KidsHelpLIne_csqjyf.jpg","https://res.cloudinary.com/dqemti5ft/image/upload/ar_1:1,b_rgb:262c35,bo_5px_solid_rgb:fbfbfb,c_fill,g_auto,r_max/v1546944526/KidsHelpLIne_csqjyf.jpg",2021,true)
        crewItemList.add(crewListItem4)
        val crewListItem5 = CrewListItem("AE","",true,"G","123456","https://res.cloudinary.com/dqemti5ft/image/upload/ar_1:1,b_rgb:262c35,bo_5px_solid_rgb:fbfbfb,c_fill,g_auto,r_max/v1546944526/KidsHelpLIne_csqjyf.jpg","https://res.cloudinary.com/dqemti5ft/image/upload/ar_1:1,b_rgb:262c35,bo_5px_solid_rgb:fbfbfb,c_fill,g_auto,r_max/v1546944526/KidsHelpLIne_csqjyf.jpg",2021,true)
        crewItemList.add(crewListItem5)
        val crewListItem6 = CrewListItem("AE","",true,"G","123456","https://res.cloudinary.com/dqemti5ft/image/upload/ar_1:1,b_rgb:262c35,bo_5px_solid_rgb:fbfbfb,c_fill,g_auto,r_max/v1546944526/KidsHelpLIne_csqjyf.jpg","https://res.cloudinary.com/dqemti5ft/image/upload/ar_1:1,b_rgb:262c35,bo_5px_solid_rgb:fbfbfb,c_fill,g_auto,r_max/v1546944526/KidsHelpLIne_csqjyf.jpg",2021,true)
        crewItemList.add(crewListItem6)
        val crewListItem7 = CrewListItem("AE","",true,"G","123456","https://res.cloudinary.com/dqemti5ft/image/upload/ar_1:1,b_rgb:262c35,bo_5px_solid_rgb:fbfbfb,c_fill,g_auto,r_max/v1546944526/KidsHelpLIne_csqjyf.jpg","https://res.cloudinary.com/dqemti5ft/image/upload/ar_1:1,b_rgb:262c35,bo_5px_solid_rgb:fbfbfb,c_fill,g_auto,r_max/v1546944526/KidsHelpLIne_csqjyf.jpg",2021,true)
        crewItemList.add(crewListItem7)
        val crewListItem8 = CrewListItem("AE","",true,"G","123456","https://res.cloudinary.com/dqemti5ft/image/upload/ar_1:1,b_rgb:262c35,bo_5px_solid_rgb:fbfbfb,c_fill,g_auto,r_max/v1546944526/KidsHelpLIne_csqjyf.jpg","https://res.cloudinary.com/dqemti5ft/image/upload/ar_1:1,b_rgb:262c35,bo_5px_solid_rgb:fbfbfb,c_fill,g_auto,r_max/v1546944526/KidsHelpLIne_csqjyf.jpg",2021,true)
        crewItemList.add(crewListItem8)



        for (i in 0..10){
            addDummyData()
        }



        modelSize = if (crewItemList.size in 1..8){
            9
        }else{
            10
        }

        for (i in 0..10){
                addtoDedicatedSlots(i)
        }














//        modelSize = if (crewItemList.size in 1..8){
//            9
//        }else{
//            10
//        }
//
//        for (i in 0..modelSize){
//            if (crewItemList.size < 9 && i == 0){
//                 addOptionalButtons(i)
//                if (crewItemList.size > 0){
//                    counterList +=1
//                    crewItemListNew.add(1,crewItemList[0])
//                }
//            }else if (i==5||i==6) {
//                    addOptionalButtons(i)
//            }else{
//                 if (i<crewItemList.size) {
//                     crewItemListNew.add(crewItemList[i])
//                     counterList +=1
//                 }else{
//                     if (counterList<crewItemList.size){
//                         counterList +=1
//                         crewItemListNew.add(crewItemList[i-2])
//                     }else{
//                        addDummyData()
//                     }
//
//                 }
//
//            }
//
//        }


        // usage sample
        val circularListView:CircularListView = findViewById(R.id.my_circular_list)
        adapter = CircularItemAdapter(layoutInflater, crewItemListNew)
        circularListView.setAdapter(adapter)
        circularListView.setListItem(crewItemListNew)
        circularListView.setRadius(100f)

        circularListView.setOnItemClickListener(object : CircularItemClickListener {
            override fun onItemClick(view: View?, index: Int) {
                if (crewItemListNew[index].lineFlag){
                    Toast.makeText(
                        this@MainActivity,
                        "view at index $index is clicked!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })



        val arc_btnn = findViewById<Button>(R.id.arc_btn)
        arc_btnn.setOnClickListener {
            intent = Intent(applicationContext, Arclayout2::class.java)
            startActivity(intent)
        }

        // remove item example
        val btRemoveItem = findViewById<View>(R.id.bt_remove_item) as Button
        btRemoveItem.setOnClickListener { adapter.removeItemAt(0) }
        // add item example
        val btAddItem = findViewById<View>(R.id.bt_add_item) as Button
        btAddItem.setOnClickListener {
            val view: View = layoutInflater.inflate(R.layout.view_circular_item, null)
//            val itemView = view.findViewById<View>(R.id.bt_item) as TextView
//            itemView.setText(java.lang.String.valueOf(adapter.getCount() + 1))
            adapter.addItem(view)
        }

        // remove item example
        val btEnlargeRadius = findViewById<View>(R.id.bt_enlarge_radius) as Button
        btEnlargeRadius.setOnClickListener { circularListView.setRadius(15.let { circularListView.radius += it; circularListView.radius }) }

        // remove item example
        val btReduceRadius = findViewById<View>(R.id.bt_reduce_radius) as Button
        btReduceRadius.setOnClickListener { circularListView.setRadius(15.let { circularListView.radius -= it; circularListView.radius }) }


    }


    fun addtoDedicatedSlots(index: Int){
        when (index){
            0 -> {
                if (crewItemList.size <9) {
                    addOptionalButtons(index)
                    if (crewItemList.size>0){
                        checkSlotAvailability(crewItemList,1,0)
                    }
                }else{
                    checkSlotAvailability(crewItemList,0,0)
                }
            }
            1 -> {

                if (crewItemList.size <9) {
                    checkSlotAvailability(crewItemList,1,0)
                }else{
                    checkSlotAvailability(crewItemList,1,1)
                }
            }
            2 -> {
                if (crewItemList.size <9) {
                    checkSlotAvailability(crewItemList,10,1)
                }else{
                    checkSlotAvailability(crewItemList,10,2)
                }
            }
            3 -> {
                if (crewItemList.size <9) {
                    checkSlotAvailability(crewItemList,2,2)
                }else{
                    checkSlotAvailability(crewItemList,2,3)
                }
            }
            4 -> {
                if (crewItemList.size <9) {
                    checkSlotAvailability(crewItemList,9,3)
                }else{
                    checkSlotAvailability(crewItemList,9,4)
                }
            }
            5 -> {
                addOptionalButtons(index)
            }
            6 -> {
                addOptionalButtons(index)
            }
            7 -> {
                if (crewItemList.size <9) {
                    checkSlotAvailability(crewItemList,3,4)
                }else{
                    checkSlotAvailability(crewItemList,3,5)
                }
            }
            8 -> {
                if (crewItemList.size <9) {
                    checkSlotAvailability(crewItemList,8,5)
                }else{
                    checkSlotAvailability(crewItemList,8,6)
                }
            }
            9 -> {
                if (crewItemList.size <9) {
                    checkSlotAvailability(crewItemList,4,6)
                }else{
                    checkSlotAvailability(crewItemList,4,7)
                }
            }
            10 -> {
                if (crewItemList.size <9) {
                    checkSlotAvailability(crewItemList,7,7)
                }else{
                    checkSlotAvailability(crewItemList,7,8)
                }
            }

        }
    }



    private fun checkSlotAvailability(crewListItemw: ArrayList<CrewListItem>, index: Int, slot:Int){
        if (crewListItemw.size>slot){
            crewItemListNew[index] = crewItemList[slot]
        }
    }






    private fun addOptionalButtons(index:Int){
        when (index) {
            0 -> {
                val crewListItem44 = CrewListItem("ADD","",true,"G","123456","https://res.cloudinary.com/dqemti5ft/image/upload/ar_1:1,b_rgb:262c35,bo_5px_solid_rgb:fbfbfb,c_fill,g_auto,r_max/v1546944526/KidsHelpLIne_csqjyf.jpg","https://res.cloudinary.com/dqemti5ft/image/upload/ar_1:1,b_rgb:262c35,bo_5px_solid_rgb:fbfbfb,c_fill,g_auto,r_max/v1546944526/KidsHelpLIne_csqjyf.jpg",2021,true)
                crewItemListNew[0] = crewListItem44
            }
            5 -> {
                val crewListItem22 = CrewListItem(
                    "CALL",
                    "",
                    true,
                    "G",
                    "123456",
                    "https://res.cloudinary.com/dqemti5ft/image/upload/ar_1:1,b_rgb:262c35,bo_5px_solid_rgb:fbfbfb,c_fill,g_auto,r_max/v1546944526/KidsHelpLIne_csqjyf.jpg",
                    "https://res.cloudinary.com/dqemti5ft/image/upload/ar_1:1,b_rgb:262c35,bo_5px_solid_rgb:fbfbfb,c_fill,g_auto,r_max/v1546944526/KidsHelpLIne_csqjyf.jpg",
                    2021, true
                )
                crewItemListNew[5] = crewListItem22

            }
            else -> {
                val crewListItem23 = CrewListItem(
                    "NCL",
                    "",
                    true,
                    "G",
                    "123456",
                    "https://res.cloudinary.com/dqemti5ft/image/upload/ar_1:1,b_rgb:262c35,bo_5px_solid_rgb:fbfbfb,c_fill,g_auto,r_max/v1546944526/KidsHelpLIne_csqjyf.jpg",
                    "https://res.cloudinary.com/dqemti5ft/image/upload/ar_1:1,b_rgb:262c35,bo_5px_solid_rgb:fbfbfb,c_fill,g_auto,r_max/v1546944526/KidsHelpLIne_csqjyf.jpg",
                    2021, true
                )
                crewItemListNew[6] = crewListItem23
            }
        }
    }


    private fun addDummyData(){
        val crewListItem33 = CrewListItem(
            "DUMMY",
            "",
            true,
            "G",
            "123456",
            "https://res.cloudinary.com/dqemti5ft/image/upload/ar_1:1,b_rgb:262c35,bo_5px_solid_rgb:fbfbfb,c_fill,g_auto,r_max/v1546944526/KidsHelpLIne_csqjyf.jpg",
            "https://res.cloudinary.com/dqemti5ft/image/upload/ar_1:1,b_rgb:262c35,bo_5px_solid_rgb:fbfbfb,c_fill,g_auto,r_max/v1546944526/KidsHelpLIne_csqjyf.jpg",
            2021,
            false
        )
        crewItemListNew.add(crewListItem33)
    }



    // you should extends CircularAdapter to add your custom item
    private class CircularItemAdapter(inflater: LayoutInflater, items: ArrayList<CrewListItem>) :
        CircularAdapter() {
        private val mItems: ArrayList<CrewListItem>
        private val mInflater: LayoutInflater
        private val mItemViews: ArrayList<View?> = ArrayList()

        init {
            mItems = items
            mInflater = inflater
            for (i in mItems.indices) {
                val view: View = mInflater.inflate(R.layout.view_circular_item, null)
                val item = view.findViewById<View>(R.id.item) as AvatarView
                val itemname = view.findViewById<View>(R.id.itemname) as TextView
                val itemmarc = view.findViewById<View>(R.id.itemarc) as LinearLayout
               // item.rotation = 90f
               // itemname.rotation = 90f
                if (mItems[i].lineFlag) {
                    item.visibility = View.VISIBLE
                    itemname.visibility = View.VISIBLE
                }else{
                    item.visibility = View.INVISIBLE
                    itemname.visibility = View.INVISIBLE
                }
                mItemViews.add(view)
            }
        }

        override fun getAllViews(): ArrayList<View?> {
            return mItemViews
        }

        override fun getCount(): Int {
            return mItemViews.size
        }

        override fun getItemAt(i: Int): View? {
            return mItemViews[i]
        }

        override fun removeItemAt(i: Int) {
            if (mItemViews.size > 0) {
                mItemViews.removeAt(i)
                notifyItemChange()
            }
        }

        override fun addItem(view: View?) {
            if (view != null) {
                mItemViews.add(view)
            }
            notifyItemChange()
        }
    }
}