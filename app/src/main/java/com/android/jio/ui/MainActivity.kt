package com.android.jio.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.jio.adapter.MainAdapter
import com.android.jio.databinding.ActivityMainBinding
import com.android.jio.presenter.MainViewModel
import com.android.jio.util.OnScrollListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainAdapter: MainAdapter
    private lateinit var onScrollListener: OnScrollListener


    private val loadMoreLeadItems = object : OnScrollListener.LoadMoreItems {
        override fun onLoadMore() {
            val nextPageNo = mainAdapter.getCurrentPageNo() + 1
            mainViewModel.getUserData(nextPageNo)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObserver()
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        setupRecyclerView()
        mainViewModel.getUserData()
    }

    private fun setupObserver() {
        mainViewModel.data.observe(this, { data -> data?.let { mainAdapter.loadData(it) } })
    }

    private fun setupRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        onScrollListener = OnScrollListener(linearLayoutManager, loadMoreLeadItems)
        mainAdapter = MainAdapter()
        binding.rvUserList.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = mainAdapter
            addOnScrollListener(onScrollListener)
        }
    }
}