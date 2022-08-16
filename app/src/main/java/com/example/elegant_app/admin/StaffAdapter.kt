package com.example.elegant_app.admin

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.elegant_app.databinding.TutorListBinding
import com.example.elegant_app.staff.TeacherAdapter
import com.example.elegant_app.staff.TeacherModel

class StaffAdapter(
    private val context: Context,
    private val Stafflist: List<StaffModel>) :
    RecyclerView.Adapter<StaffAdapter.StaffViewHolder>()

{
    var itemClick : ((StaffModel) -> Unit)? = null

        inner class StaffViewHolder(val binding: TutorListBinding): RecyclerView.ViewHolder(binding.root)
        {
            fun bind(item:StaffModel){
                binding.apply {
                    dp.setImageURI(item.photo)
                    dpTitle.setText(item.name)

                    MainCard.setOnClickListener(){
                        itemClick?.invoke(Stafflist!![adapterPosition])
                    }
                }
            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaffViewHolder {
       val binding= TutorListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return StaffViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StaffViewHolder, position: Int) {
       holder.bind(Stafflist[position])
    }

    override fun getItemCount(): Int {
       return Stafflist.size
    }
}