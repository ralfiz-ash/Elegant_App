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
import com.example.elegant_app.databinding.AdCardBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class AdAdapter(
    private val context: Context,
    private val adImagelist: List<AdModel>) :
    RecyclerView.Adapter<AdAdapter.AdViewHolder>()

{
    var itemClick : ((AdModel) -> Unit)? = null

    inner class AdViewHolder(val binding:AdCardBinding): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item: AdModel){
            binding.apply {
                val imageRef = item.imageUrl?.let {
                    FirebaseStorage.getInstance().getReferenceFromUrl(item.imageUrl.toString())
                }
                imageRef?.getBytes(10 * 1024 * 1024)?.addOnSuccessListener {
                    val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                    rvAdImageInsideCard.setImageBitmap(bitmap)


                }?.addOnFailureListener {
                    // Handle any errors
                }

                card.setOnClickListener(){
                    Log.d("clickkkk", "bind: ${item.id}")
                    //itemClick?.invoke(item)
                    showdialog(item)

                }


            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdViewHolder {
        val binding= AdCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AdViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdViewHolder, position: Int) {
        holder.bind(adImagelist[position])
    }

    override fun getItemCount(): Int {
        return adImagelist.size
    }

    fun showdialog(item: AdModel) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle("Are you sure to Confirm the Advertisement delet ?")



// Set up the buttons
        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            // Here you get get input text from the Edittext
            val db = FirebaseFirestore.getInstance()
            Log.d("3030303", "onViewCreated: ${item.imageUrl}")
            item.id?.let { it1 ->
                db.collection("Advertisement").document(item.id.toString())
                    .delete()
                    .addOnSuccessListener {
                       Toast.makeText(context,"Ad Deleted",Toast.LENGTH_LONG).show()
                        notifyDataSetChanged()

                    }
                    .addOnFailureListener { e ->
                        Log.w(
                            "error_delete",
                            "Error deleting document", e
                        )
                    }
            }

        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }
}
