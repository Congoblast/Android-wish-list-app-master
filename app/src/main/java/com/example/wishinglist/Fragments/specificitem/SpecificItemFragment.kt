package com.example.wishinglist.Fragments.specificitem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.wishinglist.Fragments.list.ListFragmentDirections
import com.example.wishinglist.R
import kotlinx.android.synthetic.main.fragment_specific_item.view.*
import kotlinx.android.synthetic.main.showdata.view.*


class SpecificItemFragment : Fragment() {

    private val args by navArgs<SpecificItemFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_specific_item, container, false)

        view.showName.text = args.specificItem.name
        view.showLocation.text = args.specificItem.location
        view.showDescription.text = args.specificItem.description
        view.showPrice.text = args.specificItem.price.toString()


        return view
    }

}