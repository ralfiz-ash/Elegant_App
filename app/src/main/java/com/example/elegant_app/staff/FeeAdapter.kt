package com.example.elegant_app.staff

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.elegant_app.databinding.FeeCardBinding
import com.example.elegant_app.databinding.TutorListBinding

class FeeAdapter(
    private val context: Context,
    private val Feelist: List<FeeModel>) :
    RecyclerView.Adapter<FeeAdapter.FeeViewHolder>()
{

        inner class FeeViewHolder(val binding:FeeCardBinding): RecyclerView.ViewHolder(binding.root)
        {
            fun bind(item:FeeModel){
                binding.apply {

                    tvpaidName.setText(item.s_name)
                    tvpaidClass.setText(item.std)
                    tvpaidDate.setText(item.date)
                    tvMonth.setText(item.month)
                    tvamount.setText(item.amount.toString())


                }

            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeeViewHolder {
        val binding = FeeCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeeViewHolder, position: Int) {
        holder.bind(Feelist[position])
    }

    override fun getItemCount(): Int {
        return Feelist.size
    }
}