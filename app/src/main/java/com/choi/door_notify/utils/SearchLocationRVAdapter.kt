package com.choi.door_notify.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.choi.door_notify.data.entities.Location
import com.choi.door_notify.databinding.ItemSearchLocationBinding
import java.util.*
import kotlin.collections.ArrayList

class SearchLocationRVAdapter(private var dataList: ArrayList<Location>): RecyclerView.Adapter<SearchLocationRVAdapter.viewHolder>(), Filterable {

    private var filteredList = dataList
    private var nonFilteredList = dataList

    interface ItemClickListener {
        fun onClick(loc: Location)
    }

    private lateinit var itemClickListener: ItemClickListener

    fun setItemClickListener(listener: ItemClickListener) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val binding: ItemSearchLocationBinding = ItemSearchLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind()
        holder.itemView.setOnClickListener{
            itemClickListener.onClick(dataList[position])
        }

    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    inner class viewHolder(val binding: ItemSearchLocationBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.itemSearchLocationName.text = filteredList[adapterPosition].first + " " + filteredList[adapterPosition].second + " " + filteredList[adapterPosition].third
        }
    }


    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                filteredList = if(charString.isEmpty()) {
                    nonFilteredList
                }
                else {
                    var filteringList = ArrayList<Location>()
                    for (item in nonFilteredList) {
                        val str = item.first + " " + item.second + " " + item.third
                        if (str.lowercase().contains(charString.lowercase())) {
                            filteringList.add(item)
                        }
                    }
                    filteringList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as ArrayList<Location>
                notifyDataSetChanged()
            }

        }
    }
}