package com.example.learnkotlin.menus

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.learnkotlin.R
import com.example.learnkotlin.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(layoutInflater)
        binding.apply {
            addItem.setOnClickListener{
                Log.d("ADD ITEM", "to Form")
                findNavController().navigate(R.id.action_menuFragment_to_formFragment)

            }
            menuItemList.setOnClickListener {
                Log.d("LIST ITEM", "to list")
                findNavController().navigate(R.id.action_menuFragment_to_listFragment3)

            }
        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = MenuFragment()

    }
}