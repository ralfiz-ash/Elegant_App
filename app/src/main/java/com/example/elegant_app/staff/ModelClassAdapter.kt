package com.example.elegant_app.staff

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.elegant_app.admin.ModelClass
import com.example.elegant_app.databinding.SimpleCardBinding

class ModelClassAdapter(val context: Context, private val cardlist: MutableList<ModelClass>):
                        RecyclerView.Adapter<ModelClassAdapter.ModelViewHolder>()
{
    inner class ModelViewHolder(val binding:SimpleCardBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: ModelClass){

            binding.apply {

                txtMsg.setText(item.msg)

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val binding = SimpleCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ModelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
       holder.bind(cardlist[position])
    }

    override fun getItemCount(): Int {
       return cardlist.size
    }
}