package com.seda.reminderapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.seda.reminderapp.R
import com.seda.reminderapp.databinding.FragmentMaleWeightBinding
import java.math.RoundingMode
import java.text.DecimalFormat


class MaleWeightFragment : Fragment() {
    private lateinit var binding: FragmentMaleWeightBinding
 var kilo :Double =0.0
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


        setupNumberPicker()


    }
    fun setupNumberPicker() {
        val numberPicker = binding.numberpicker
        numberPicker.minValue = 35
        numberPicker.maxValue = 200
        numberPicker.wrapSelectorWheel = true
        numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            binding.sayi.text = " $newVal  kg"
          kilo = (newVal.toDouble()* 0.033)
            val dec = DecimalFormat("#.##")
            dec.roundingMode = RoundingMode.CEILING
          kilo(dec.format(kilo))
        }
    }
    fun kilo(Kilo:String){
        Log.e("kilo","$Kilo")
        binding.nextbutton1.setOnClickListener {

val action = MaleWeightFragmentDirections.actionMaleWeightFragmentToGoalWaterFragment(Kilo)
            Navigation.findNavController(it).navigate(action)
        }
    }
}