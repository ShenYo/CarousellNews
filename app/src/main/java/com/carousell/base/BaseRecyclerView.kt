package com.carousell.base

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.koin.standalone.KoinComponent


/**
 * Description:
 *
 * @author Shenyo
 * @version 1.0, 2019/3/30
 */


abstract class BaseRecyclerViewAdapter<T>(
    val context: Context
) : RecyclerView.Adapter<BaseRecyclerViewHolder<T>>(), KoinComponent {

    private lateinit var recyclerView: RecyclerView
    var dataList: MutableList<T> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    val isEmpty: Boolean
        get() = dataList.isEmpty()

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    fun setData(data: List<T>) {
        val previousSize = dataList.size
        dataList.clear()
        notifyItemRangeRemoved(0, previousSize)
        dataList.addAll(data)
        notifyItemRangeInserted(0, data.size)
    }

    fun insert(data: T) {
        dataList.add(data)
        notifyItemInserted(dataList.size - 1)
    }

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<T>, position: Int) {
        holder.bindDataToView(dataList[position])
    }

}


abstract class BaseRecyclerViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView), KoinComponent {

    abstract fun bindDataToView(data: T)
}