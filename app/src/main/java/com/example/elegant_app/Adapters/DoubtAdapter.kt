package com.example.elegant_app.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.elagant.pojo.Doubtmodel
import com.example.elegant_app.databinding.LayoutDoubtBinding


class DoubtAdapter(val requireContext: Context, val ll: MutableList<Doubtmodel>) :
    RecyclerView.Adapter<DoubtAdapter.MyViewholder>() {
    inner class MyViewholder(val binding: LayoutDoubtBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(teacherModel: Doubtmodel) {
            binding.apply {
                textView9.text = teacherModel.question
                if (teacherModel.answer.equals("")) {

                } else {
                    textView10.text = teacherModel.answer
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val binding =
            LayoutDoubtBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewholder(binding)
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.bind(ll[position])
    }

    override fun getItemCount(): Int {
        return ll.size
    }


}
