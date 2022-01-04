package com.choi.door_notify.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.choi.door_notify.data.Status
import com.choi.door_notify.databinding.ItemWholeStatusBinding

class WholeStatusRVAdapter(private val dataList: ArrayList<Status>) : RecyclerView.Adapter<WholeStatusRVAdapter.viewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WholeStatusRVAdapter.viewHolder {
        val binding: ItemWholeStatusBinding = ItemWholeStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: WholeStatusRVAdapter.viewHolder, position: Int) {
//        holder.binding.itemWholeStatusIv.setImageResource(data[position])
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class viewHolder(val binding: ItemWholeStatusBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Status) {
            val rain = data.rain.toString() + "%"
            binding.itemWholeStatusRainTv.text = rain
            binding.itemWholeStatusIv.setImageResource(data.img!!)
            val time = data.time.toString() + "시"
            binding.itemWholeStatusTimeTv.text = time
            val tmpr = data.tmpr.toString() + '\u2103'.toString()
            binding.itemWholeStatusTmprTv.text = tmpr
        }
    }
}