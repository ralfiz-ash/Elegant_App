package com.example.elegant_app.admin

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.elegant_app.databinding.FeedbackTileBinding
import com.example.elegant_app.databinding.TutorListBinding
import com.example.elegant_app.staff.TeacherAdapter
import com.example.elegant_app.staff.TeacherModel

class FeedbackAdapter(
    private val context: Context,
    private val feedabacklist: List<feedbackModel>) : RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder>() {


            inner class FeedbackViewHolder(val binding: FeedbackTileBinding): RecyclerView.ViewHolder(binding.root)
            {
                fun bind(item:feedbackModel){
                    binding.apply {
                        tvStudentId.setText(item.s_id)
                        tvfeedback.setText(item.content)

                    }

                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedbackViewHolder {
        val binding = FeedbackTileBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FeedbackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedbackViewHolder, position: Int) {
      holder.bind(feedabacklist[position])
    }

    override fun getItemCount(): Int {
       return feedabacklist.size
    }


}