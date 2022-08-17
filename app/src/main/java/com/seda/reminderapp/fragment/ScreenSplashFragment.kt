package com.seda.reminderapp.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.seda.reminderapp.R
import com.seda.reminderapp.databinding.FragmentScreenSplashBinding


class ScreenSplashFragment : Fragment() {
    private lateinit var  binding: FragmentScreenSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding= FragmentScreenSplashBinding.inflate(layoutInflater,container,false)
        Handler(Looper.myLooper()!!).postDelayed({
            findNavController().navigate(R.id.hiFragment)
        },3000)
findNavController().popBackStack()
        return binding.root

    }


}