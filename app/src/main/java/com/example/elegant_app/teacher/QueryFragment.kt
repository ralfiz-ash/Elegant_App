package com.example.elegant_app.teacher

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elegant_app.databinding.FragmentQueryBinding
import com.google.firebase.firestore.FirebaseFirestore


class QueryFragment : Fragment() {
private lateinit var binding: FragmentQueryBinding
var queryList= mutableListOf<QueryModel>()
    var answer:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        showData()
      binding=FragmentQueryBinding.inflate(layoutInflater,container,false)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun showData() {

        queryList.clear()
        val db = FirebaseFirestore.getInstance()

        db.collection("Doubt")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")

                    queryList.add(
                        QueryModel(document.id,
                            document.data["student"].toString(),
                            document.data["question"].toString(), document.data["answer"].toString())
                    )


                }

                binding.rvQuery.layoutManager=LinearLayoutManager(requireContext())
                val adapter= Query_Adapter(requireContext(),queryList)
                binding.rvQuery.adapter=adapter

                adapter.cardclick= {

                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }
}