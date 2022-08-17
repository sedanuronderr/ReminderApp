package com.seda.reminderapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.Toast
import androidx.navigation.Navigation
import com.seda.reminderapp.R
import com.seda.reminderapp.databinding.FragmentWeightMaleBinding

class WeightMaleFragment : Fragment() {
    private lateinit var binding: FragmentWeightMaleBinding
 private lateinit var picker: NumberPicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      binding = FragmentWeightMaleBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


     //   binding.numberPicker.wrapSelectorWheel = true

      setupNumberPicker()
        binding.nextbutton1.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.wakeUpMaleFragment)
        }

        binding.back1.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_weightMaleFragment_to_genderFragment)
        }

    }

    private fun setupNumberPicker() {
        val numberPicker = binding.numberpicker
        numberPicker.minValue = 0
        numberPicker.maxValue = 200
        numberPicker.wrapSelectorWheel = true
        numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            binding.sayi.text = " $newVal  kg"

        }
    }

}