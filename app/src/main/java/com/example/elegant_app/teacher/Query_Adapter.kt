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
                 tvReply.text = item.answer
                 if (item.answer.equals("")) {
                     answerSubmit.visibility = View.VISIBLE
                     etAnswer.visibility = View.VISIBLE
                 } else {
                     answerSubmit.visibility = View.GONE
                     etAnswer.visibility = View.GONE
                 }

                 answerSubmit.setOnClickListener() {

                     val a = etAnswer.text.toString()
                     if (a != null) {
                         var obj = QueryModel(item.id, item.student, item.question, a)
                         item?.answer?.let {
                             tvReply.text = it
                         }
                         tvReply.text = item?.answer
                         val db1 = FirebaseFirestore.getInstance()
                         db1.collection("Doubt").document(item.id.toString()).set(obj)
                             .addOnSuccessListener {
                                 Toast.makeText(context, "Answer Submitted", Toast.LENGTH_SHORT)
                                     .show()
                             }.addOnFailureListener {
                                 Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                             }
                         tvReply.visibility = View.VISIBLE
                         answerSubmit.visibility = View.GONE
                         etAnswer.visibility = View.GONE
                         tvReply.text = a


                     }
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