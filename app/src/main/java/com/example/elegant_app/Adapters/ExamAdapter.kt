package com.example.elegant_app.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.elagant.pojo.ExamModel
import com.example.elegant_app.databinding.LayoutExamscoreBinding

class ExamAdapter(val requireContext: Context, val ll: MutableList<ExamModel>) :
    RecyclerView.Adapter<ExamAdapter.MyViewholder>() {
    inner class MyViewholder(val binding: LayoutExamscoreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(teacherModel: ExamModel) {
            binding.apply {
                txtSubject.text = "Subject\t:\t" + teacherModel.subject
                txtScore.text = "Score\t\t:\t" + teacherModel.score

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val binding =
            LayoutExamscoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewholder(binding)
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.bind(ll[position])
    }

    override fun getItemCount(): Int {
        return ll.size
    }


}
