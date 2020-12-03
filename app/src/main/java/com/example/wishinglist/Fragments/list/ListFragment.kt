package com.example.wishinglist.Fragments.list

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wishinglist.R
import com.example.wishinglist.model.Items
import com.example.wishinglist.viewModel.ItemViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.showdata.view.*

class ListFragment : Fragment() {

    private lateinit var mItemViewModel: ItemViewModel

//instantiate a list of all the items, which is empty now
private var list= listOf<Items>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ):

            View? {


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val adapter = ListAdapter()
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mItemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        mItemViewModel.readAllData.observe(viewLifecycleOwner, Observer { items ->
            adapter.setData(items)
            list = items
        })


        view.imagebtnAdd.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)

        }
        view.imageBtnShare.setOnClickListener{
            shareStuff()
        }

        setHasOptionsMenu(true)
        return view
    }





//share the list. The ressStr is empty, and we fills it with the items. We fill it with all the items below, and sends it. Since it is a for loop, it goes through every item and does it.
    private fun shareStuff(){
        var resStr=""

        for (items in list){
            resStr = resStr + "Item:" +" " + items.name +
                    "\n" + "Description:" + " " + items.description +
                    "\n" + "Location:" + " "+ items.location +
                    "\n" + "Price:" + " "+ items.price.toString() + "kr" + "\n" + "\n"
        }
        //val adapter = ListAdapter()

        var shareIntent = Intent().apply{
            this.action = Intent.ACTION_SEND
            //share from database where is false
            this.putExtra(Intent.EXTRA_TEXT, resStr)
            // this.putExtra
            this.type = "text/plain"
        }
        startActivity(shareIntent)
    }




}