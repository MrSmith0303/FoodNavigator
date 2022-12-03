package com.example.foodnavigator


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class VersionAdapter(private var versionList: List<Versions>)  :
    RecyclerView.Adapter<VersionAdapter.VersionVH>() {
    class VersionVH (itemView: View): RecyclerView.ViewHolder(itemView) {



        var codeNameTxt: TextView = itemView.findViewById(R.id.code_name)
        var descriptionTxt: TextView = itemView.findViewById(R.id.description)
        var linearLayout : LinearLayout = itemView.findViewById(R.id.linerLayout)
        var expandableLayout : RelativeLayout = itemView.findViewById(R.id.expandable_layout)


    }

    fun filterList(filterList: ArrayList<Versions>) {
        // below line is to add our filtered
        // list in our course array list.
        versionList = filterList
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VersionVH {
        val view : View = LayoutInflater.from(parent.context).inflate( R.layout.row , parent, false)

        return VersionVH(view)
    }

    override fun onBindViewHolder(holder: VersionVH, position: Int) {
        val versions : Versions = versionList[position]
        holder.codeNameTxt.text = versions.codeName
        holder.descriptionTxt.text = versions.description

        val isExpandable : Boolean = versionList[position].expandable
        holder.expandableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE

        holder.linearLayout.setOnClickListener {
            val versions = versionList[position]
            versions.expandable = !versions.expandable
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return versionList.size
    }

}