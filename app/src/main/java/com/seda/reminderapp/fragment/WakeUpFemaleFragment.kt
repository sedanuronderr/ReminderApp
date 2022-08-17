package com.seda.reminderapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.seda.reminderapp.R
import com.seda.reminderapp.databinding.FragmentWakeUpFemaleBinding
import com.seda.reminderapp.databinding.FragmentWakeUpMaleBinding


class WakeUpFemaleFragment : Fragment() {
    private lateinit var binding: FragmentWakeUpFemaleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWakeUpFemaleBinding.inflate(layoutInflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back3.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.weightFemaleFragment)
        }
    }
}