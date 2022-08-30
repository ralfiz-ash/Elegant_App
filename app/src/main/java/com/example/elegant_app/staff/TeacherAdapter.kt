package com.example.elegant_app.staff

import android.content.Context
import android.content.DialogInterface
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.elegant_app.databinding.TutorListBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

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


                        dpTitle.setText(item.name)

                        delete.setOnClickListener(){
                            showdialog(item)
                        }

                        MainCard.setOnClickListener(){
                            listItemClick?.invoke(item)
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

    fun showdialog(item: TeacherModel) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle("Are you sure to delete ${item.name} ?")

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->

            val db = FirebaseFirestore.getInstance()
            // Log.d("3030303", "onViewCreated: ${item.imageUrl}")

            db.collection("Teachers").document(item.doc_id.toString())
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(context,"Tutor Deleted", Toast.LENGTH_LONG).show()
                    notifyDataSetChanged()

                    val fireStoreDatabase = FirebaseFirestore.getInstance()
                    val obj = AlumniTeacherModel(name = item.name, qualification = item.qualification, experience = item.experience, mobile = item.mobile, email = item.email, blood = item.blood, address = item.address, dob = item.dob, gender = item.gender, adhar = item.adhar, standard = item.standard,
                        medium = item.medium, division = item.division, photo = item.photo)
                    fireStoreDatabase.collection("Alumni_Teachers")
                        .add(obj)
                        .addOnSuccessListener {

                            Log.d("alumni_tutor", "Added document with ID ${it.id}")
                        }
                    /*val obj = AlumniTeacherModel(name = item.name, qualification = item.qualification, experience = item.experience, mobile = item.mobile, email = item.email, blood = item.blood, address = item.address, dob = item.dob, gender = item.gender, adhar = item.adhar, standard = item.standard,
                        medium = item.medium, division = item.division, photo = item.photo)
                    fireStoreDatabase.collection("Alumni_Teachers")
                        .add(obj)*/

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