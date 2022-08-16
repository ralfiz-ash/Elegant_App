package com.example.elegant_app.staff

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.elegant_app.databinding.TutorListBinding
import com.example.elegant_app.teacher.StudentModel

class TeacherAdapter(
    private val context: Context,
    private val Teacherlist: List<TeacherModel>) :
        RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>()
{
    var listItemClick : ((TeacherModel) -> Unit)? = null

            inner class TeacherViewHolder(val binding:TutorListBinding): RecyclerView.ViewHolder(binding.root)
            {
                fun bind(item:TeacherModel){
                    binding.apply {
                        dp.setImageURI(item.photo)
                        dpTitle.setText(item.name)

                        MainCard.setOnClickListener(){
                            listItemClick?.invoke(Teacherlist!![adapterPosition])
                        }
                    }

                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
       val binding = TutorListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TeacherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
      holder.bind(Teacherlist[position])
    }

    override fun getItemCount(): Int {
        return Teacherlist.size
    }
}