package com.example.learnkotlin.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnkotlin.R
import com.example.learnkotlin.data.repositories.ItemRepository
import com.example.learnkotlin.databinding.FragmentListBinding


class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: ListViewModel
    private lateinit var rvAdapter: ListViewAdapter
    private var page = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        subscribe()
    }

    private fun subscribe() {
        viewModel.itemsLiveData.observe(this) {
            Log.d("DATA", "$it")
            rvAdapter.setData(it)
        }
        //edit to form item
        viewModel.itemLiveData.observe(this) {
            Navigation.findNavController(requireView()).navigate(R.id.action_listFragment_to_formFragment,
            bundleOf("edit_item" to it))

        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repo = ItemRepository()
                return ListViewModel(repo) as T
            }
        }).get(ListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListBinding.inflate(layoutInflater)
        binding.apply {


            nextBtn.setOnClickListener {
                viewModel.loadItemData(++page)
                pageText.text = page.toString()
            }
            prevBtn.setOnClickListener {
                if (page > 0) {
                    viewModel.loadItemData(--page)
                    pageText.text = page.toString()
                }
            }

            rvAdapter = ListViewAdapter(viewModel)
            recyclerViewItem.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = rvAdapter
            }
        }
        return binding.root
    }


    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()

    }
}