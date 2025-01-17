package com.ballboycorp.tingting.table.profile.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ballboycorp.tingting.R
import com.ballboycorp.tingting.databinding.ItemTableProfileBinding
import com.ballboycorp.tingting.table.model.TableItemViewModel
import com.ballboycorp.tingting.table.profile.ProfileActivity
import com.ballboycorp.tingting.utils.extensions.bind

/**
 * Created by musooff on 2019-04-22.
 */

class TablePeopleAdapter(private val clickHandler: ProfileActivity.ClickHandler) : RecyclerView.Adapter<TablePeopleAdapter.TablePeopleViewHolder>() {

    private var mViewModel: TableItemViewModel? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TablePeopleViewHolder {
        val binding = parent.bind<ItemTableProfileBinding>(R.layout.item_table_profile, viewType)
        return TablePeopleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mViewModel?.people?.size ?: 0
    }

    override fun onBindViewHolder(holder: TablePeopleViewHolder, position: Int) {
        holder.binding.tableViewModel = mViewModel
        holder.binding.userIndex = position
        holder.binding.clickHandler = clickHandler
    }

    fun submitList(viewModel: TableItemViewModel?) {
        mViewModel = viewModel
        notifyDataSetChanged()
    }

    inner class TablePeopleViewHolder(val binding: ItemTableProfileBinding): RecyclerView.ViewHolder(binding.root)
}