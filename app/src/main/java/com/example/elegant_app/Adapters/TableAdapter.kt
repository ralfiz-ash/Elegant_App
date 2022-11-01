package com.example.elegant_app.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.elagant.pojo.TimetableModel
import com.example.elegant_app.R
import com.example.elegant_app.databinding.TableformatBinding

class TableAdapter(val requireContext: Context, val ll: MutableList<TimetableModel>) :
    RecyclerView.Adapter<TableAdapter.MyViewholder>() {
    inner class MyViewholder(val binding: TableformatBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TimetableModel) {
            binding.apply {
                if (adapterPosition % 2 == 0) {
                    binding.apply {
                        root.setBackgroundResource(R.drawable.white_tile)
                    }
                } else {
                    binding.apply {
                        root.setBackgroundResource(R.drawable.tint_tile)
                    }

                }
                date.text = item.date
                classs.text = item.std
                sub.text = item.subject
                time.text = item.time
                endTime.text = item.endTime
                tutorName.text = item.tutor

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val binding =
            TableformatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewholder(binding)
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {

        holder.bind(ll[position])
    }

    override fun getItemCount(): Int {
        return ll.size
    }


}
