package com.seda.reminderapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.seda.reminderapp.R
import com.seda.reminderapp.databinding.FragmentMaleWeightBinding


class MaleWeightFragment : Fragment() {
    private lateinit var binding: FragmentMaleWeightBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMaleWeightBinding.inflate(layoutInflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.back1.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_maleWeightFragment_to_genderFragment)
        }
        binding.nextbutton1.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.goalWaterFragment)
        }
        setupNumberPicker()
    }
    fun setupNumberPicker() {
        val numberPicker = binding.numberpicker
        numberPicker.minValue = 0
        numberPicker.maxValue = 200
        numberPicker.wrapSelectorWheel = true
        numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            binding.sayi.text = " $newVal  kg"

        }
    }
}