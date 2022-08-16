package com.seda.reminderapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.seda.reminderapp.R
import com.seda.reminderapp.databinding.FragmentWeightFemaleBinding
import com.seda.reminderapp.databinding.FragmentWeightMaleBinding

class WeightFemaleFragment : Fragment() {
    private lateinit var binding: FragmentWeightFemaleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWeightFemaleBinding.inflate(layoutInflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupNumberPicker()
        binding.nextbutton2.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.wakeUpMaleFragment)
        }
    }
    private fun setupNumberPicker() {
        val numberPicker = binding.numberpicker1
        numberPicker.minValue = 0
        numberPicker.maxValue = 200
        numberPicker.wrapSelectorWheel = true
        numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            binding.sayi1.text = " $newVal  kg"

        }
    }

}