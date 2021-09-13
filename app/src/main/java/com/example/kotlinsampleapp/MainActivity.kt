package com.example.kotlinsampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val viewModel: MainViewModel by inject()
    private val recyclerViewAdapter = MainRecyclerViewAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = recyclerViewAdapter

        observeFromViewModal()
        viewModel.fetchCards(this)
    }

    private fun observeFromViewModal() {
        viewModel.cards.observe(this, { cards ->
            recyclerViewAdapter.loadData(cards)
        })
    }
}