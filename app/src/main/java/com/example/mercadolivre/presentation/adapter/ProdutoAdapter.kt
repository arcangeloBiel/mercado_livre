package com.example.mercadolivre.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mercadolivre.R
import com.example.mercadolivre.data.model.Produto
import com.example.mercadolivre.data.utils.ItemClickListener


class ProdutoAdapter(
    private var dataSet: MutableList<Produto>
) : RecyclerView.Adapter<ProdutoAdapter.ViewHolder>() {

    var clickListener: ItemClickListener? = null

    fun itemClickListener(itemClickListener: ItemClickListener) {
        this.clickListener = itemClickListener
    }

    fun setDataSet(dados: MutableList<Produto>) {
        this.dataSet = dados
    }

    fun limparDados() {
        val size: Int = dataSet.size
        dataSet.clear()
        notifyItemRangeRemoved(0, size)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        val nomeProduto: TextView = itemView.findViewById(R.id.idNomeProduto)
        val descricaoProduto: TextView = itemView.findViewById(R.id.IdDescricaoProduto)
        val precoProduto: TextView = itemView.findViewById(R.id.IdPrecoProduto)
        val imgProduto: ImageView = itemView.findViewById(R.id.idImageProduto)

        override fun onClick(v: View?) {
            clickListener?.onItemClick(v!!, layoutPosition)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_produto, viewGroup, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {

            holder.nomeProduto.text = dataSet[position].nome
            Log.d("teste", "adapter -> ${ dataSet[position].nome}")
            holder.precoProduto.text = ("Preço: ${dataSet[position].preco}")
            holder.descricaoProduto.text = dataSet[position].descricao
            try {
                val imageItem = dataSet[position].imageUrl
                if (imageItem != null) {
                    Glide.with(holder.itemView)
                        .load("${dataSet[position].imageUrl}")
                        .into(holder.imgProduto)
                } else {
                    holder.imgProduto.setImageResource(R.drawable.produto01)
                }
            } catch (e: Exception) {
                holder.imgProduto.setImageResource(R.drawable.produto01)
            }

        }catch(e: Exception){
            Log.d("Picking","PickingPedidoItemAdapter Error - ${e.message}")
        }
    }

}

