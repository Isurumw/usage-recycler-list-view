package com.example.kotlinsampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinsampleapp.utils.RecyclerItemClickListener
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), RecyclerItemClickListener.OnRecyclerClickListener {
    private val TAG = "MainActivity"
    private val viewModel: MainViewModel by inject()
    private val recyclerViewAdapter = MainRecyclerViewAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = recyclerViewAdapter
        recycler_view.addOnItemTouchListener(RecyclerItemClickListener(this, recycler_view, this))

        observeFromViewModal()
        viewModel.fetchCards(this)
    }

    private fun observeFromViewModal() {
        viewModel.cards.observe(this, { cards ->
            recyclerViewAdapter.loadData(cards)
        })
    }

    override fun onItemPress(view: View, position: Int) {
        Log.d(TAG, "The tapped position: $position")
    }
}