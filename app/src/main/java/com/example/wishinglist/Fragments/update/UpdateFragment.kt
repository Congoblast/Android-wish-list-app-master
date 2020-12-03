package com.example.wishinglist.Fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.wishinglist.R
import com.example.wishinglist.model.Items
import com.example.wishinglist.viewModel.ItemViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    lateinit var item: Items

    private lateinit var mItemViewModel:ItemViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        item = args.currentItem


        mItemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        var checkmark = args.currentItem.checkmark

        view.checkBox.isChecked = checkmark == true



        view.update_ItemName_edittext.setText(args.currentItem.name)
        view.update_Description_edittext.setText(args.currentItem.description)
        view.update_Location_edittext.setText(args.currentItem.location)
        view.update_editTextNumber.setText(args.currentItem.price.toString())




        view.checkBox.setOnClickListener{
            if (view.checkBox.isChecked)
            {
                item.checkmark = true
                Log.d("checkbox","is clicked")
            }
            else{
                item.checkmark = false
                Log.d("checkbox","unclicked")

            }

        }
        view.imagebtndel.setOnClickListener{
            deleteItem()
        }



         view.update_btn.setOnClickListener{
             checkfirst()
        updateItem()
         }

        //menuitems
        setHasOptionsMenu(true)
        return view
    }

    private fun checkfirst(){
        if (update_editTextNumber.text.toString().isEmpty()){
            update_editTextNumber.setText("0")
        }

    }


    private fun updateItem() {
        val name = update_ItemName_edittext.text.toString()
        val description = update_Description_edittext.text.toString()
        val location = update_Location_edittext.text.toString()
        val price = Integer.parseInt(update_editTextNumber.text.toString())
        var checkmark = item.checkmark
        val image = "path"


        if (inputCheck(name)) {
            val updatedItem = Items(args.currentItem.id, name, description, location, price,image,checkmark)
            //update item
            mItemViewModel.updateItems(updatedItem)
            Toast.makeText(requireContext(), "Changes Applied", Toast.LENGTH_LONG).show()

            //navigate back to fragment list
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out a name for the wish", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name:String): Boolean{
        return !(TextUtils.isEmpty(name))
    }



        //function to delete 
    private fun deleteItem() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Delete"){_,_->
            mItemViewModel.deleteItem(args.currentItem)
            Toast.makeText(requireContext(),"Your wish has been removed!",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        //Delete button confirmation
        builder.setNegativeButton("No"){_,_ ->}
        builder.setTitle("Delete Wish?")
        builder.setMessage("Are you sure you want to delete this wish from your list?")
        builder.create().show()
    }

}