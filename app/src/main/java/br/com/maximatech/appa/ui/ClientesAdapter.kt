package br.com.maximatech.appa.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import br.com.maximatech.appa.R
import br.com.maximatech.appa.components.BaseRecyclerViewAdapter
import br.com.maximatech.appa.model.PDV
import br.com.maximatech.appa.util.LayoutUtil
import kotlinx.android.synthetic.main.item_clientes.view.*

class ClientesAdapter(
    override var lista: MutableList<PDV>,
    val onClick: (PDV) -> Unit = {}
): BaseRecyclerViewAdapter<PDV, ClientesAdapter.ViewHolder>() {

    class ViewHolder(
        itemView: View,
        val context: Context
    ): BaseRecyclerViewAdapter.ViewHolder<PDV>(itemView) {
        override fun bindView(item: PDV) {
            with(itemView) {
                img_icone.background = configuraIcone(item.RazaoSocial)
                txv_pdv.text = item.codigo.plus(" - ").plus(item.RazaoSocial)
                dados_pdv.text = item.cpfCnpj
                if (item.tarefa) {
                    img_tarefa_respondida.visibility = View.VISIBLE
                }
            }
        }

        private fun configuraIcone(nome: String?): Drawable {
            val primeiraLetraDoNome = getPrimeiraLetraDoNome(nome)
            val cor = ContextCompat.getColor(context, R.color.colorPrimary)
            return LayoutUtil.criaIconeLetraComSombraCircular(
                primeiraLetraDoNome,
                cor
            )
        }

        private fun getPrimeiraLetraDoNome(nome: String?) = nome?.first().toString()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_clientes, parent, false)
        return ViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pdvs = lista[position]
        holder.bindView(pdvs)
        holder.itemView.rootView.setOnClickListener { onClick(pdvs) }
    }

}
