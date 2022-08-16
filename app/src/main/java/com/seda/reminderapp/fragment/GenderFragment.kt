package com.seda.reminderapp.fragment

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.seda.reminderapp.R
import com.seda.reminderapp.databinding.FragmentGenderBinding
import com.seda.reminderapp.databinding.FragmentHiBinding


class GenderFragment : Fragment() {
    private lateinit var  binding: FragmentGenderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding= FragmentGenderBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val paint = binding.kelime.paint
        val width = paint.measureText(binding.kelime.text.toString())
        val textShader: Shader = LinearGradient(0f, 0f, width, binding.kelime.textSize, intArrayOf(
            Color.parseColor("#F97C3C"),
            Color.parseColor("#FDB54E"),
            /*Color.parseColor("#64B678"),
            Color.parseColor("#478AEA"),*/
            Color.parseColor("#8446CC")
        ), null, Shader.TileMode.REPEAT)

        binding.kelime.paint.setShader(textShader)

        binding.nextbutton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.weighMaleFragment)
        }
    }



}