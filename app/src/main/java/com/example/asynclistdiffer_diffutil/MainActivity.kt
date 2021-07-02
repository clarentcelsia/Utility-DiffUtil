package com.example.asynclistdiffer_diffutil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvList: RecyclerView
    private lateinit var listAdapter: ListAdapter
    private lateinit var spotList: ArrayList<Items>

    private val spotName: ArrayList<String> = arrayListOf(
        "Taman Nasional Komodo",
        "Pink Beach",
        "Gili Island",
        "Raja Ampat"
    )
    private val spotImg: ArrayList<Int> = arrayListOf(
        R.drawable.taman_nasional_komodo,
        R.drawable.beach,
        R.drawable.gili,
        R.drawable.raja_ampat
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvList = findViewById(R.id.rvList)
        listAdapter = ListAdapter()
        rvList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = listAdapter
        }

        spotList = ArrayList()
        spotList.add(
            Items(
                spotName[0],
                spotImg[0]
            )
        )
        spotList.add(
            Items(
                spotName[1],
                spotImg[1]
            )
        )
        spotList.add(
            Items(
                spotName[2],
                spotImg[2]
            )
        )

        listAdapter.itemDiffer.submitList(spotList)
    }

    fun addNewData(view: View) {
        val list: MutableList<Items> = spotList.toMutableList()
        list.add(
            Items(
                spotName[3],
                spotImg[3]
            )
        )
        listAdapter.itemDiffer.submitList(list)
    }
}