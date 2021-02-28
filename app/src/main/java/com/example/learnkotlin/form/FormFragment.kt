package com.example.learnkotlin.form

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    private var itemValue: Item? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemValue = it.getParcelable<Item>("edit_item")
        }
        initModel()
        subscribe()

    }

    private fun subscribe() {
        viewModel.itemLiveData.observe(this) {
            findNavController().navigate(R.id.action_formFragment_to_listFragment)
        }
    }

    private fun initModel() {
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
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

            //update
            itemValue?.apply {
                submitBtn.text = "UPDATE"
                titleItem.text = "EDIT ITEM"

                dateTiet.setText(date)
                nameTiet.setText(name)
                quantityTiet.setText(quantity.toString())
                noteTiet.setText(note)
            }

            dateTiet.inputType = InputType.TYPE_NULL
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            dateTiet.setOnClickListener {

                dateTiet.setOnClickListener(View.OnClickListener {
                    val datePickerDialog = activity?.let { it1 ->
                        DatePickerDialog(
                                it1, DatePickerDialog.OnDateSetListener
                        { view, year, monthOfYear, dayOfMonth ->
                            val date = "$dayOfMonth/$monthOfYear/$year"
                            dateTiet.setText(date.toString())
                        }, year, month, day
                        )
                    }
                    datePickerDialog?.show()
                })
            }

            arguments?.let {
                val itemUpdate = it.getParcelable<Item>("edit_item")
                itemUpdate?.let {

                    Toast.makeText(requireContext(), "Success update ${it.id}", Toast.LENGTH_LONG)
                            .show()
                }
            }

            submitBtn.setOnClickListener {
                val quantity: Int =
                        if (quantityEt.editText?.text.toString().isNullOrEmpty()) {
                            0
                        } else {
                            quantityEt.editText?.text.toString().toInt()
                        }

                if(itemValue == null) {
                    //add
                    itemValue = Item(
                            id = "",
                            name = nameEt.editText?.text.toString(),
                            date = dateEt.editText?.text.toString(),
                            quantity = quantity,
                            note = noteEt.editText?.text.toString()
                    )
                }  else {
                    //edit
                    itemValue?.id?.let { it ->
                        itemValue = Item(
                                note = noteEt.editText?.text.toString(),
                                name = nameEt.editText?.text.toString(),
                                date = dateEt.editText?.text.toString(),
                                quantity = quantity,
                                id = it
                        )
                    }
                }
                viewModel.save(itemValue!!)
            }
            cancelBtn.setOnClickListener {
                Navigation.findNavController(requireView()).popBackStack()
            }
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FormFragment()
    }
}