package br.com.maximatech.appa.components

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<M,
        VH : BaseRecyclerViewAdapter.ViewHolder<M>>(open var lista: MutableList<M> = mutableListOf()) :
    RecyclerView.Adapter<VH>() {

    override fun getItemCount() = lista.size

    abstract class ViewHolder<M>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bindView(item: M)
    }

}