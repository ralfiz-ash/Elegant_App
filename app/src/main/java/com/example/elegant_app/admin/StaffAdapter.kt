package com.example.elegant_app.admin

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.elegant_app.databinding.TutorListBinding
import com.example.elegant_app.staff.TeacherAdapter
import com.example.elegant_app.staff.TeacherModel
import com.google.firebase.storage.FirebaseStorage

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
                    val imageRef = item.photo?.let {
                        FirebaseStorage.getInstance().getReferenceFromUrl(it)
                    }
                    imageRef?.getBytes(10 * 1024 * 1024)?.addOnSuccessListener {
                        val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                        dp.setImageBitmap(bitmap)


                    }?.addOnFailureListener {
                        // Handle any errors
                    }
                    dpTitle.text = item.name

                    MainCard.setOnClickListener(){
                        itemClick?.invoke(item)
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