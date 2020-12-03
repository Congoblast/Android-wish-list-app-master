package com.example.wishinglist.Fragments.list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wishinglist.R
import com.example.wishinglist.model.Items
import kotlinx.android.synthetic.main.showdata.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    //instantiate an empty list of items
    private var itemList = emptyList<Items>()

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }
//create our viewhold4er
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.showdata,parent, false))
    }
//let the adapter check the size of items in the database
    override fun getItemCount(): Int {
        return itemList.size
    }
    //bind the items to the list,
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.itemView.name_txt.text = currentItem.name

        // click on name and direct to the specific item
        holder.itemView.rowLayout.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToSpecificItemFragment(currentItem)
            holder.itemView.findNavController().navigate(action)

        }
        // clicking on the edit btn redirects to the update page with the specific item
        holder.itemView.button2.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }



//Change color if item has been bought
        if (!currentItem.checkmark){

            holder.itemView.CardView.setCardBackgroundColor(Color.WHITE)
            holder.itemView.name_txt.setTextColor(Color.parseColor("#808080"))
        }
        else{
            holder.itemView.CardView.setCardBackgroundColor(Color.parseColor("#A92921"))
            holder.itemView.name_txt.setTextColor(Color.parseColor("#F8D54C"))



        }


        }

    fun setData(items: List<Items>){
        this.itemList = items
        notifyDataSetChanged()
    }
}