package com.example.elegant_app.admin

import android.content.Context
import android.content.DialogInterface
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.elegant_app.databinding.TutorListBinding
import com.example.elegant_app.staff.*
import com.google.firebase.firestore.FirebaseFirestore
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
                    if (item.photo == "nil") {
                        dp.setBackgroundResource(android.R.drawable.ic_menu_report_image)
                    } else {
                        val imageRef = item.photo?.let {
                            FirebaseStorage.getInstance().getReferenceFromUrl(it)
                        }
                        imageRef?.getBytes(10 * 1024 * 1024)?.addOnSuccessListener {
                            val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                            Glide.with(context).load(bitmap)
                                .apply(RequestOptions.circleCropTransform()).into(binding.dp)

                        }?.addOnFailureListener {
                            // Handle any errors
                        }
                    }
                    dpTitle.text = item.name

                    delete.setOnClickListener() {
                        showdialog(item)
                    }

                    MainCard.setOnClickListener() {
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

    fun showdialog(item: StaffModel) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle("Are you sure to delete ${item.name} ?")

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->

            val db = FirebaseFirestore.getInstance()
            // Log.d("3030303", "onViewCreated: ${item.imageUrl}")

            db.collection("Office_Staff").document(item.doc_id.toString())
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(context, "Staff Deleted", Toast.LENGTH_LONG).show()
                    notifyDataSetChanged()

                    val fireStoreDatabase = FirebaseFirestore.getInstance()
                    val obj = AlumniStaffModel(
                        name = item.name,
                        mobile = item.mobile,
                        email = item.email,
                        blood = item.blood,
                        dob = item.dob,
                        adhar = item.adhar,
                        gender = item.gender,
                        address = item.address,
                        photo = item.photo,
                        doc_id = item.doc_id
                    )
                    fireStoreDatabase.collection("Alumni_Staffs")
                        .add(obj)
                        .addOnSuccessListener {

                            Log.d("alumni_staff", "Added document with ID ${it.id}")
                        }

                }
                .addOnFailureListener { e ->
                    Log.w(
                        "error_delete",
                        "Error deleting document", e
                    )
                }

        })
        builder.setNegativeButton(
            "Cancel",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }
}