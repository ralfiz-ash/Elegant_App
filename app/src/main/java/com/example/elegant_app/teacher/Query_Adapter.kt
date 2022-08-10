package com.example.elegant_app.teacher

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.elegant_app.databinding.CardBinding

class Query_Adapter (
    private val context: Context,
    private var query_list:List<QueryModel>  ):RecyclerView.Adapter<Query_Adapter.Query_viewHolder>()

{
    var cardclick: ((QueryModel) -> Unit)? = null

        inner class Query_viewHolder(val binding: CardBinding): RecyclerView.ViewHolder(binding.root){
            fun bind(item: QueryModel) {
                binding.apply {

                    tvCard.text=item.title
                    cardBg.setOnClickListener(){
                        cardclick?.invoke(query_list!![adapterPosition])

                    }

                }

            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Query_viewHolder {
        val binding=CardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Query_viewHolder(binding)
    }

    override fun onBindViewHolder(holder: Query_viewHolder, position: Int) {
       holder.bind(query_list[position])
    }

    override fun getItemCount(): Int {
        return query_list.size
    }
}