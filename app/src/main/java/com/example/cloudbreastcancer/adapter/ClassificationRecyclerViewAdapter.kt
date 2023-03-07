package com.example.cloudbreastcancer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.breastcancerlocal.model.HeartDiseaseVO
import com.example.cloudbreastcancer.R
import com.example.cloudbreastcancer.fragments.ListFragment


class ClassificationRecyclerViewAdapter(items: List<HeartDiseaseVO>, listener: ListFragment.OnListFragmentInteractionListener)
    : RecyclerView.Adapter<ClassificationRecyclerViewAdapter.ViewHolder>() {

    private var mValues: List<HeartDiseaseVO> = items
    private var mListener: ListFragment.OnListFragmentInteractionListener = listener

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.listrowitems_layout, parent,
            false)
        return ViewHolder(itemView)
    }

    // Displays data at a certain position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        holder.listClassificationoneView.text = " " + mValues[position].getId() + " "
        holder.listClassificationtwoView.text = " " + mValues[position].getAge() + " "
        holder.listClassificationthreeView.text = " " + mValues[position].getSex() + " "
        holder.listClassificationfourView.text = " " + mValues[position].getCp() + " "
        holder.listClassificationfiveView.text = " " + mValues[position].getTrestbps() + " "
        holder.listClassificationsixView.text = " " + mValues[position].getChol() + " "
        holder.listClassificationsevenView.text = " " + mValues[position].getFbs() + " "
        holder.listClassificationeightView.text = " " + mValues[position].getRestecg() + " "
        holder.listClassificationnineView.text = " " + mValues[position].getThalach() + " "
        holder.listClassificationtenView.text = " " + mValues[position].getExang() + " "
        holder.listClassificationelevnView.text = " " + mValues[position].getOldpeak() + " "
        holder.listClassificationtwelveView.text = " " + mValues[position].getSlope() + " "
        holder.listClassificationthirteenView.text = " " + mValues[position].getCa() + " "
        holder.listClassificationfourteenView.text = " " + mValues[position].getThal() + " "
        holder.listClassificationresultView.text = " " + mValues[position].getOutcome() + " "

        holder.mView.setOnClickListener {
            mListener?.onListFragmentInteraction(holder.mItem)
        }
    }

    // Returns size of data list
    override fun getItemCount(): Int {
        return mValues.size
    }

    // Describes an item view and its place within the RecyclerView
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var mView: View
        var listClassificationoneView: TextView
        var listClassificationtwoView: TextView
        var listClassificationthreeView: TextView
        var listClassificationfourView: TextView
        var listClassificationfiveView: TextView
        var listClassificationsixView: TextView
        var listClassificationsevenView: TextView
        var listClassificationeightView: TextView
        var listClassificationnineView: TextView
        var listClassificationtenView: TextView
        var listClassificationelevnView: TextView
        var listClassificationtwelveView: TextView
        var listClassificationthirteenView: TextView
        var listClassificationfourteenView: TextView
        var listClassificationresultView: TextView
        lateinit var mItem: HeartDiseaseVO

        init {
            mView = view
            listClassificationoneView = view.findViewById(R.id.listClassificationoneView)
            listClassificationtwoView = view.findViewById(R.id.listClassificationtwoView)
            listClassificationthreeView = view.findViewById(R.id.listClassificationthreeView)
            listClassificationfourView = view.findViewById(R.id.listClassificationfourView)
            listClassificationfiveView = view.findViewById(R.id.listClassificationfiveView)
            listClassificationsixView = view.findViewById(R.id.listClassificationsixView)
            listClassificationsevenView = view.findViewById(R.id.listClassificationsevenView)
            listClassificationeightView = view.findViewById(R.id.listClassificationeightView)
            listClassificationnineView = view.findViewById(R.id.listClassificationnineView)
            listClassificationtenView = view.findViewById(R.id.listClassificationtenView)
            listClassificationelevnView = view.findViewById(R.id.listClassificationelevenView)
            listClassificationtwelveView = view.findViewById(R.id.listClassificationtwelveView)
            listClassificationthirteenView = view.findViewById(R.id.listClassificationthirteenView)
            listClassificationfourteenView = view.findViewById(R.id.listClassificationfourteenView)
            listClassificationresultView = view.findViewById(R.id.listClassificationfourteenView)
            listClassificationresultView = view.findViewById(R.id.listClassificationresultView)
        }

        override fun toString(): String {
            return super.toString() + " " + mItem
        }
    }
}
