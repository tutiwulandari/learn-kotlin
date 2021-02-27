package com.example.learnkotlin.form

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.learnkotlin.R
import com.example.learnkotlin.data.models.Item
import com.example.learnkotlin.data.repositories.ItemRepository
import com.example.learnkotlin.databinding.FragmentFormBinding
import java.util.*


class FormFragment : Fragment() {
    private lateinit var binding: FragmentFormBinding
    private lateinit var viewModel: FormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initModel()
        subscribe()

    }

    private fun subscribe() {
        viewModel.itemLiveData.observe( this) {
            findNavController().navigate(R.id.action_formFragment_to_listFragment)
        }
    }

    private fun initModel() {
        viewModel = ViewModelProvider(this, object :ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repo = ItemRepository()
                return FormViewModel(repo) as T
            }
        }).get(FormViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFormBinding.inflate(layoutInflater)
        binding.apply {
            submitBtn.setOnClickListener {
                val quantity: Int =
                    if (quantityEt.editText?.text.toString().isNullOrEmpty()) {
                    0
                } else {
                    quantityEt.editText?.text.toString().toInt()
                }
                val item = Item(
                    id = "",
                    name = nameEt.editText?.text.toString(),
                    date = dateEt.editText?.text.toString(),
                    quantity = quantity,
                    note = noteEt.editText?.text.toString()
                )
                viewModel.save(item)
            }
            cancelBtn.setOnClickListener {
                findNavController().navigate(R.id.action_formFragment_to_listFragment)
            }
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FormFragment()
    }
}