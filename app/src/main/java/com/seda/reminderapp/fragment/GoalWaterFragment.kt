package com.seda.reminderapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.seda.reminderapp.R
import com.seda.reminderapp.databinding.FragmentGoalWaterBinding

import com.seda.reminderapp.databinding.FragmentWeightFemaleBinding


class GoalWaterFragment : Fragment() {
    private lateinit var binding: FragmentGoalWaterBinding
    val bundle:GoalWaterFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGoalWaterBinding.inflate(layoutInflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       binding.bottomNavigationView.background = null
       binding.bottomNavigationView.menu.getItem(2).isEnabled = false

        val sayi = bundle.litre
        binding.litre.text ="${sayi}\n"+"Litre"


    }
}