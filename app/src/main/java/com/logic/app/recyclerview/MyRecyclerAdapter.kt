package com.logic.app.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.logic.app.recyclerview.databinding.RecItemBinding

class MyRecyclerAdapter(
    private val models: List<MyModel>,
    private val listener:   OnItemClickListener

) :
    RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(RecItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {
            binding.recId.text = models[position].id.toString()
            binding.recTittle.text = models[position].tittle
            binding.recDescription.text = models[position].description
            binding.recImage.setImageResource(models[position].img)
            binding.recImage.setOnClickListener(this)
        }
    }

    override fun getItemCount(): Int = models.size

    inner class MyViewHolder(val binding: RecItemBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION)
                listener.onItemClick(position)
        }
    }


    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}


