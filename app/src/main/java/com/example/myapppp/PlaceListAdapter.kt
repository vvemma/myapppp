package com.example.myapppp

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlaceListAdapter(var places: ArrayList<Place>, var con: Context) :
    RecyclerView.Adapter<PlaceListAdapter.ViewHolder>(), Filterable {
    var TAG = "PlaceListAdapter"

    var filteredPlaces = ArrayList<Place>()
    var itemFilter = ItemFilter()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_name_place_list_item: TextView
        var tv_place_number_list_item: TextView

        init {
            tv_name_place_list_item = itemView.findViewById(R.id.tv_name_place_list_item)
            tv_place_number_list_item = itemView.findViewById(R.id.tv_place_number_list_item)

            itemView.setOnClickListener {
                //TODO(살려줘)
            }
        }
    }

    init {
        filteredPlaces.addAll(places)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val con = parent.context
        val inflater = con.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.places, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place: Place = filteredPlaces[position]
        //[수정요함] 이미지 작업의 경우 glide를 사용해 server의 image를 불러올 것
        //holder.iv_person_phone_book_list_item
        holder.tv_name_place_list_item.text = place.name
        holder.tv_place_number_list_item.text = "현재 인구: ${place.peopleCount.first} ~ ${place.peopleCount.second}명"
    }

    override fun getItemCount(): Int {
        return filteredPlaces.size
    }

    //-- filter
    override fun getFilter(): Filter {
        return itemFilter
    }

    inner class ItemFilter : Filter() {
        override fun performFiltering(charSequence: CharSequence): FilterResults {
            val filterString = charSequence.toString()
            val results = FilterResults()
            Log.d(TAG, "charSequence : $charSequence")

            //검색이 필요없을 경우를 위해 원본 배열을 복제
            val filteredList: ArrayList<Place> = ArrayList()
            //공백제외 아무런 값이 없을 경우 -> 원본 배열
            if (filterString.trim { it <= ' ' }.isEmpty()) {
                results.values = places
                results.count = places.size

                return results
            } else {
                for (place in places) {
                    if (place.name.contains(filterString)) {
                        filteredList.add(place)
                    }
                }
            }
            results.values = filteredList
            results.count = filteredList.size

            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
            filteredPlaces.clear()
            filteredPlaces.addAll(filterResults.values as ArrayList<Place>)
            notifyDataSetChanged()
        }
    }


}