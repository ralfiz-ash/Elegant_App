package com.example.elegant_app.teacher

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.elegant_app.databinding.CardBinding

class TeacherCardAdapter(private val context: Context,
                         private var Cardlist: List<TeacherCardModel>):
    RecyclerView.Adapter<TeacherCardAdapter.TeacherCardviewHolder>() {
    var cardclick: ((TeacherCardModel) -> Unit)? = null


    inner class TeacherCardviewHolder(private val binding: CardBinding): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item:TeacherCardModel){
            binding.apply {
                tvCard.text=item.title
                cardBg.setBackgroundColor(ContextCompat.getColor(context, item.color))

                cardBg.setOnClickListener(){
                    cardclick?.invoke(Cardlist!![adapterPosition])
// var cardclick: ((TeacherCardModel) -> Unit)? = null
                    //cardclick?.invoke(item)
               /*     if(Cardlist[adapterPosition].id.equals("1")){

                    }*/
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherCardviewHolder {
        val binding=CardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TeacherCardviewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherCardviewHolder, position: Int) {
        holder.bind(Cardlist[position])
    }

    override fun getItemCount(): Int {
       return Cardlist.size
    }



}

