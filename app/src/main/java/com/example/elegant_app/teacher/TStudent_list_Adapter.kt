package com.example.elegant_app.teacher

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.elegant_app.databinding.StudentListBinding

class TStudent_list_Adapter(
    private val context: Context,
    private var student_list: List<StudentModel>) :
    RecyclerView.Adapter<TStudent_list_Adapter.TstudentlistviewHolder>()
{
    var listItemClick : ((StudentModel) -> Unit)? = null


    inner class TstudentlistviewHolder(val binding: StudentListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: StudentModel) {
            binding.apply {
                tvTstudentName.text=item.name
                rvTstudentList.setOnClickListener(){
                    listItemClick?.invoke(student_list!![adapterPosition])
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TstudentlistviewHolder {
       val binding=StudentListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TstudentlistviewHolder(binding)
    }

    override fun onBindViewHolder(holder: TstudentlistviewHolder, position: Int) {
        holder.bind(student_list[position])
    }

    override fun getItemCount(): Int {
      return student_list.size
    }
}