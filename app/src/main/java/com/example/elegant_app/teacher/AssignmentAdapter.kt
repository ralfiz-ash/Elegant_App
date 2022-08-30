package com.example.elegant_app.teacher

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.elegant_app.databinding.FeedbackTileBinding
import com.example.elegant_app.databinding.QueryCardBinding
import com.google.firebase.firestore.FirebaseFirestore

class AssignmentAdapter( private val context: Context,
                         private var assignmentList:List<AssignmentModel>  ): RecyclerView.Adapter<AssignmentAdapter.A_ViewHolder>()
{
    var cardclick: ((QueryModel) -> Unit)? = null

    inner class A_ViewHolder(val binding:FeedbackTileBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: AssignmentModel) {
            binding.apply {
                tvStudentId.text = item.topic
                tvfeedback.text = item.date


                /*answerSubmit.setOnClickListener(){

                    val a= etAnswer.text.toString()
                    var obj=QueryModel(item.id,item.student,item.question,a)
                    item?.answer?.let {
                        tvAnswer.text=it
                    }
                    tvAnswer.text = item?.answer
                    *//*val db1 = FirebaseFirestore.getInstance()
                    db1.collection("Doubt").document(item.id.toString()).set(obj).addOnSuccessListener {
                        Toast.makeText( context,"Answer Submitted" , Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText( context,it.toString() , Toast.LENGTH_SHORT).show()
                    }*//*

                    answerSubmit.alpha=0f
                    etAnswer.visibility= View.GONE
                    tvAnswer.text=a
                }*/

               /* query.setOnClickListener(){
                    cardclick?.invoke(item)

                }*/

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):A_ViewHolder {
        val binding= FeedbackTileBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return A_ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: A_ViewHolder, position: Int) {
        holder.bind(assignmentList[position])
    }

    override fun getItemCount(): Int {
        return assignmentList.size
    }

}