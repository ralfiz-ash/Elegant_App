package com.example.elegant_app.admin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.elegant_app.R
import com.example.elegant_app.databinding.FragmentConnectStaffBinding
import com.google.firebase.firestore.FirebaseFirestore


class ConnectStaffFragment : Fragment() {
    private lateinit var binding:FragmentConnectStaffBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= FragmentConnectStaffBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignin.setOnClickListener(){
            val msg=binding.etSPostmessage.text.toString()

            binding.etSPostmessage.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s.toString().isNotEmpty())

                    {
                        binding.etSPostmessage.setBackgroundResource(R.drawable.curved)
                    }
                }

                override fun afterTextChanged(s: Editable?) {

                }

            })

            if(validator()){
                addData(msg)
            }


        }
    }
    private fun
            addData(Data: String) {

        //creating firestore object
        val fireStoreDatabase = FirebaseFirestore.getInstance()
        val obj = ModelClass(Data) // obj of modelclass
        Log.d("TAG", "addData: ${Data}")
        //Adding data to db
        fireStoreDatabase.collection("admin_msgs")
            .add(obj)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Data added", Toast.LENGTH_LONG).show()
                binding.etSPostmessage.text.clear()

                //Log.d(TAG, "Added document with ID ${it.id}")
            }
            .addOnFailureListener { exception ->
                Toast.makeText(requireContext(), "Error Occured", Toast.LENGTH_LONG).show()
                //Log.w(TAG, "Error adding document $exception")
            }
    }

    fun validator() :Boolean{
        if (binding.etSPostmessage.text.isBlank() || binding.etSPostmessage.text.isEmpty()
        ){
            binding.etSPostmessage.setBackgroundResource(R.drawable.error_curved)
            return false
        }
        else
        {
            return true}
    }
}