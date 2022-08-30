package com.example.elegant_app.staff

import android.content.Context
import android.content.DialogInterface
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.elegant_app.databinding.StudentListBinding
import com.example.elegant_app.databinding.TutorListBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

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
                  /*  val pic=item.photo?.toUri()
                    dp.setImageURI(pic)*/
                    if(item.photo=="nil")
                    {
                        dp.setBackgroundResource(android.R.drawable.ic_menu_report_image)
                    }
                    else
                    {
                        val imageRef = item.photo?.let {
                            FirebaseStorage.getInstance().getReferenceFromUrl(it)
                        }
                        imageRef?.getBytes(10 * 1024 * 1024)?.addOnSuccessListener {
                            val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                            dp.setImageBitmap(bitmap)


                        }?.addOnFailureListener {
                            // Handle any errors
                        }
                    }


                    dpTitle.text = item.name

                    delete.setOnClickListener(){
                        //Log.d("studid", "bind: ${item.doc_id}")
                        showdialog(item)
                    }

                    MainCard.setOnClickListener(){
                        //Log.d("studid", "bind: ${it.}")
                        student_item_click?.invoke(item)

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

    fun showdialog(item: StudentModel) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle("Are you sure to delete ${item.name} ?")

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->

            val db = FirebaseFirestore.getInstance()
           // Log.d("3030303", "onViewCreated: ${item.imageUrl}")

                db.collection("Students").document(item.doc_id.toString())
                    .delete()
                    .addOnSuccessListener {
                        Toast.makeText(context,"Student Deleted", Toast.LENGTH_LONG).show()
                        notifyDataSetChanged()

                        val fireStoreDatabase = FirebaseFirestore.getInstance()
                        val obj = AlumniStudentModel(name = item.name, reg_no = item.reg_no, school = item.school, parent = item.parent, mobile = item.mobile, email = item.email, blood = item.blood, dob = item.dob, adhar = item.adhar, gender = item.gender, address = item.address, standard = item.standard, division = item.division, medium = item.medium, photo = item.photo, doc_id = item.doc_id)
                        fireStoreDatabase.collection("Alumni_Students")
                            .add(obj)
                            .addOnSuccessListener {

                                Log.d("alumni_student", "Added document with ID ${it.id}")
                            }

                    }
                    .addOnFailureListener { e ->
                        Log.w(
                            "error_delete",
                            "Error deleting document", e
                        )
                    }

        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }
}

