package com.trung.kotlinexample.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.trung.kotlinexample.BR
import com.trung.kotlinexample.R
import com.trung.kotlinexample.model.jobresult.JobItem
import com.trung.kotlinexample.viewmodel.ItemJobViewModel

class ListJobAdapter(context: Context, list: MutableList<JobItem>) :
        BaseRecyclerAdapter<JobItem, ListJobAdapter.ViewHolder>(context, list) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewDataBinding = holder.viewDataBinding
        viewDataBinding.setVariable(BR.itemJobViewModel,
                ItemJobViewModel(list[position]))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context),
                R.layout.item_job, parent, false)

        return ViewHolder(binding)
    }

    inner class ViewHolder(val viewDataBinding: ViewDataBinding) :
            RecyclerView.ViewHolder(viewDataBinding.root) {

        init {
            this.viewDataBinding.executePendingBindings()
            itemView.setOnClickListener {
            }
        }
    }
}