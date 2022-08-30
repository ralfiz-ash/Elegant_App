package com.example.elegant_app.teacher

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.elegant_app.databinding.QueryCardBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class Query_Adapter (
    private val context: Context,
    private var query_list:List<QueryModel>  ):RecyclerView.Adapter<Query_Adapter.Query_viewHolder>()

{
    var cardclick: ((QueryModel) -> Unit)? = null

        inner class Query_viewHolder(val binding:QueryCardBinding): RecyclerView.ViewHolder(binding.root){
            fun bind(item: QueryModel) {
             binding.apply {
                    tvStudent.text = item.student
                    tvDoubt.text = item.question
                 tvAnswer.text=item.answer
                 if (tvAnswer.text.equals("")){

                 }else{
                     answerSubmit.alpha=0f
                     etAnswer.visibility=View.GONE
                 }

                    answerSubmit.setOnClickListener(){

                        val a= etAnswer.text.toString()
                        var obj=QueryModel(item.id,item.student,item.question,a)
                        item?.answer?.let {
                            tvAnswer.text=it
                        }
                        tvAnswer.text = item?.answer
                        val db1 = FirebaseFirestore.getInstance()
                        db1.collection("Doubt").document(item.id.toString()).set(obj).addOnSuccessListener {
                            Toast.makeText( context,"Answer Submitted" ,Toast.LENGTH_SHORT).show()
                        }.addOnFailureListener {
                            Toast.makeText( context,it.toString() ,Toast.LENGTH_SHORT).show()
                        }

                        answerSubmit.alpha=0f
                        etAnswer.visibility=View.GONE
                        tvAnswer.text=a



                    }

                   query.setOnClickListener(){
                        cardclick?.invoke(item)

                    }

                }

            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Query_viewHolder {
        val binding=QueryCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Query_viewHolder(binding)
    }

    override fun onBindViewHolder(holder: Query_viewHolder, position: Int) {
       holder.bind(query_list[position])
    }

    override fun getItemCount(): Int {
        return query_list.size
    }
}