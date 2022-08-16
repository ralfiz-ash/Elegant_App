package com.example.elegant_app.staff

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.elegant_app.databinding.TutorListBinding

class StudentAdapter(
    private val context: Context,
    private val Students_list: List<StudentModel>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>()
{
    var student_item_click : ((StudentModel) -> Unit)? = null

        inner class StudentViewHolder(val binding:TutorListBinding): RecyclerView.ViewHolder(binding.root)
        {
            fun bind(item:StudentModel){
                binding.apply {
                    dp.setImageURI(item.photo)
                    dpTitle.setText(item.name)

                    MainCard.setOnClickListener(){
                        student_item_click?.invoke(Students_list!![adapterPosition])
                    }
                }

            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
       val binding=TutorListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(Students_list[position])
    }

    override fun getItemCount(): Int {
       return Students_list.size
    }
}