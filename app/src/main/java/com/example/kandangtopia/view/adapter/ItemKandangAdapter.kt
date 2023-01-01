package com.example.kandangtopia.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kandangtopia.R
import com.example.kandangtopia.databinding.ItemKandangLayoutBinding
import com.example.kandangtopia.model.Kandang

class ItemKandangAdapter(
    var data: List<Kandang>,
    private val itemClickCallback: onItemClickCallback
): RecyclerView.Adapter<ItemKandangAdapter.ItemHolder>() {
    private lateinit var binding: ItemKandangLayoutBinding

    inner class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(item: Kandang){
            binding = ItemKandangLayoutBinding.bind(itemView)
            binding.tvNameKandang.text = item.nama_kandang
            binding.tvKota.text = item.alamat_kandang
            binding.tnplItem.setStatusView(item.status_aktif, item.jenis_peternak, item.periode)
            binding.tvAmountAge.text = item.umur.replace(" Hari","")
            binding.tvAmountPopulation.text = item.populasi.replace(" Ekor","")
            binding.tvAmountWeight.text = item.body_weight.replace(" gr","")

            when(item.status_aktif){
                true ->{
                    with(binding){
                        Glide.with(itemView.context)
                            .load(R.drawable.active_bg)
                            .into(ivItemkandang)

                        root.setOnClickListener {
                            itemClickCallback.onSelectedItem(item)
                        }

                    }
                }
                else ->{
                    with(binding){
                        Glide.with(itemView.context)
                            .load(R.drawable.nonactive_bg)
                            .into(ivItemkandang)

                        icAge.visibility = View.GONE
                        icWeight.visibility = View.GONE
                        icPopulation.visibility = View.GONE
                        tvUnitAge.visibility = View.GONE
                        tvUnitPopulation.visibility = View.GONE
                        tvUnitWeight.visibility = View.GONE
                        tvAmountWeight.visibility = View.GONE
                        tvAmountPopulation.visibility = View.GONE
                        tvAmountAge.visibility = View.GONE
                    }
                }
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_kandang_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) = holder.bind(data[position])

    override fun getItemCount(): Int = data.size

    interface onItemClickCallback{
        fun onSelectedItem(item: Kandang){}
    }
}