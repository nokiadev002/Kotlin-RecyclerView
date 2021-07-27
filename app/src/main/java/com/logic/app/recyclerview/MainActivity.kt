package com.logic.app.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.logic.app.recyclerview.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity(), MyRecyclerAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val modelList = generateFak(15)

    private fun generateFak(size: Int): ArrayList<MyModel> {

        val list = ArrayList<MyModel>()
        for (i in 0 until size) {

            val draw: Int = when (i % 7) {
                0 -> R.drawable.ic1
                1 -> R.drawable.ic2
                2 -> R.drawable.ic3
                3 -> R.drawable.ic4
                4 -> R.drawable.ic5
                5 -> R.drawable.ic6
                6 -> R.drawable.ic7
                else -> R.drawable.ic_launcher_foreground

            }
            val item = MyModel(i, "$i   des", "$i   tittle", draw)

            list.add(item)
        }

        return list
    }


    private val myAdapter: MyRecyclerAdapter by lazy { MyRecyclerAdapter(modelList, this) }

    private lateinit var rec: RecyclerView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var fabRemove: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rec = binding.rec
        fabAdd = binding.fabAdd
        fabRemove = binding.fabRemove

        linearLayoutManager = LinearLayoutManager(applicationContext)
        rec.layoutManager = linearLayoutManager

        rec.adapter = myAdapter

        rec.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.HORIZONTAL
            )
        )

        fabAdd.setOnClickListener {
            val index: Int = Random.nextInt(modelList.size)
            val tes = MyModel(
                Random.nextInt(modelList.size - 1),
                "${Random.nextInt(modelList.size)} description",
                "${Random.nextInt(modelList.size)}  tittle",
                R.drawable.ic_launcher_background
            )
            modelList.add(index, tes)
            myAdapter.notifyItemInserted(index)
            Toast.makeText(this, "Add Item $index", Toast.LENGTH_SHORT).show()

        }

        fabRemove.setOnClickListener {
            val index: Int = Random.nextInt(modelList.size - 1)
            modelList.removeAt(index)
            myAdapter.notifyItemRemoved(index)
            Toast.makeText(this, "Remove Item $index", Toast.LENGTH_SHORT).show()
        }

    }


    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Items $position clicked", Toast.LENGTH_SHORT).show()

        val clickItem = modelList[position]
        clickItem.tittle = "CLICK"
        myAdapter.notifyItemChanged(position)
    }
}