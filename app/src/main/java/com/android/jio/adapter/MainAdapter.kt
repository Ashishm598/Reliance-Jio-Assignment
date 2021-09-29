package com.android.jio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.jio.databinding.ItemUserListBinding
import com.android.jio.model.Data
import com.android.jio.model.MainResponse
import com.android.jio.util.extension.loadImage

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var currentPage = MainResponse()
    private val userList = mutableListOf<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserListViewHolder(
            ItemUserListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentData = userList[position]
        (holder as UserListViewHolder).bind(currentData)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun loadData(page: MainResponse) {
        this.currentPage = page
        page.data.let {
            this.userList.addAll(it)
            notifyItemRangeInserted(itemCount, it.size)
        }
    }

    fun clearAllLoadData(page: MainResponse) {
        this.currentPage = page
        page.data.let {
            userList.clear()
            this.userList.addAll(it)
            notifyDataSetChanged()
        }
    }

    fun getCurrentPageNo(): Int {
        return currentPage.page
    }

    private inner class UserListViewHolder(private val binding: ItemUserListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentData: Data) {
            binding.apply {
                tvEmail.text = currentData.email
                tvFirstName.text = currentData.first_name
                tvLastName.text = currentData.last_name
                imageView.loadImage(currentData.avatar)
            }
        }

    }

}