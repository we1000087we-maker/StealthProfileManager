package com.stealth.manager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProfilesAdapter(
    private val profiles: List<ProfileEntity>,
    private val onLaunch: (ProfileEntity) -> Unit
) : RecyclerView.Adapter<ProfilesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(android.R.id.text1)
        val proxy: TextView = view.findViewById(android.R.id.text2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val profile = profiles[position]
        holder.name.text = profile.name
        holder.proxy.text = "${profile.proxyHost}:${profile.proxyPort}"
        holder.itemView.setOnClickListener { onLaunch(profile) }
    }

    override fun getItemCount() = profiles.size
}
