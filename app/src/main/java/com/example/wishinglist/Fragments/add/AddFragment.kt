package com.example.wishinglist.Fragments.add

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wishinglist.R
import com.example.wishinglist.viewModel.ItemViewModel
import com.example.wishinglist.model.Items
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_update.*

class AddFragment : Fragment() {

    private lateinit var mItemViewModel: ItemViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        mItemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        view.btn_add_to_list.setOnClickListener{
            checkfirst()
            insertIntoDatabase()
        }
        return view
    }

    //check if the price field is empty. If empty set to 0
    private fun checkfirst(){
        if (editTextNumber.text.toString().isEmpty()){
            editTextNumber.setText("0")
        }

    }

    //check if the name is empty
    private fun inputCheck(name:String): Boolean{

        return !(TextUtils.isEmpty(name))
    }

//Insert our values from the forms to the database
    private fun insertIntoDatabase() {
        val name = addItemName_edittext.text.toString()
        val description = addDescription_edittext.text.toString()
        val location = addLocation_edittext.text.toString()
        val image = "path"
        val price = editTextNumber.text
        val checkmark = false
        if(inputCheck(name)){
        val items = Items(
            0,
            name,
            description,
            location,
            Integer.parseInt(price.toString()),
                image,
            checkmark
        )
            //add the items and make a toast
            mItemViewModel.addItem(items)
            Toast.makeText(requireContext(),"Added to Wishlist!",Toast.LENGTH_LONG).show()
            //return to the listview
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        //error message
        else{
            Toast.makeText(requireContext(),"Please fill out A name for your wish!",Toast.LENGTH_LONG).show()

        }
        }
}