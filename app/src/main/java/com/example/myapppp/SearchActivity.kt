package com.example.myapppp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SearchActivity : AppCompatActivity() {

    val TAG = "SearchActivity"

    lateinit var rv_place_book: RecyclerView
    lateinit var placeListAdapter: PlaceListAdapter
    lateinit var places: ArrayList<Place>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        rv_place_book = findViewById(R.id.rv_phone_book)

        val searchView: SearchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(searchViewTextListener)

        places = tempPlaces()
        setAdapter()
    }

    var searchViewTextListener: SearchView.OnQueryTextListener =
        object : SearchView.OnQueryTextListener {
            //검색버튼 입력시 호출, 검색버튼이 없으므로 사용하지 않음
            override fun onQueryTextSubmit(s: String): Boolean {
                return false
            }

            //텍스트 입력/수정시에 호출
            override fun onQueryTextChange(s: String): Boolean {
                placeListAdapter.filter.filter(s)
                Log.d(TAG, "SearchVies Text is changed : $s")
                return false
            }
        }

    fun setAdapter() {
        //리사이클러뷰에 리사이클러뷰 어댑터 부착
        rv_place_book.layoutManager = LinearLayoutManager(this)
        placeListAdapter = PlaceListAdapter(places, this)
        rv_place_book.adapter = placeListAdapter
    }

    fun tempPlaces(): ArrayList<Place> {
        var tempPersons = ArrayList<Place>()
        tempPersons.add(Place("경복궁", Pair(1000, 2000)))
        tempPersons.add(Place("a경복궁1", Pair(1000, 2000)))
        tempPersons.add(Place("b경복궁2", Pair(1000, 2000)))
        tempPersons.add(Place("c경복궁3", Pair(1000, 2000)))
        tempPersons.add(Place("d경복궁4", Pair(1000, 2000)))
        tempPersons.add(Place("e경복궁5", Pair(1000, 2000)))
        tempPersons.add(Place("f경복궁6", Pair(1000, 2000)))
        return tempPersons
    }
}