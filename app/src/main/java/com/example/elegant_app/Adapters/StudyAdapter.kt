package com.example.elegant_app.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.elagant.pojo.StudyModel
import com.example.elegant_app.databinding.LayoutMeterialsBinding

class StudyAdapter(val requireContext: Context, val ll: MutableList<StudyModel>) :
    RecyclerView.Adapter<StudyAdapter.MyViewholder>() {
    inner class MyViewholder(val binding: LayoutMeterialsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StudyModel) {
            binding.apply {
                txtTitle.text = "Topic\t:\t" + item.title
                tvSubject.text = "Subject\t:\t" + item.subject
                txtLink.text = "Link\t:\t" + item.link

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val binding =
            LayoutMeterialsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewholder(binding)
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.bind(ll[position])
    }

    override fun getItemCount(): Int {
        return ll.size
    }


}
