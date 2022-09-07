package com.seda.reminderapp.RecyclerView

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.seda.reminderapp.databinding.CardviewBinding
import com.seda.reminderapp.model.saat

class recyclerviewalarm(val context: Context, val saatbilgi:ArrayList<saat>): RecyclerView.Adapter<recyclerviewalarm.AlarmViewHolder>(){
    class AlarmViewHolder(val binding: CardviewBinding) :RecyclerView.ViewHolder(binding.root){

    }
    private val diffCallback= object : DiffUtil.ItemCallback<saat>(){
        override fun areItemsTheSame(oldItem: saat, newItem:saat): Boolean {

            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: saat, newItem: saat): Boolean {

            return oldItem == newItem
        }


    }
    val differ = AsyncListDiffer(this, diffCallback)
    var onLongClickListener: ((saat )->Unit)?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {

        return AlarmViewHolder(CardviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        val current = saatbilgi.get(position)

        holder.binding.apply {
           saat.text = current.saat.toString()
            dk.text = current.dk.toString()

            cardAlarm.setOnClickListener {
                Toast.makeText(context,"${current.saat} dk sonra alarm çalacak",Toast.LENGTH_SHORT).show()
                onLongClickListener?.let { it1 -> it1(saatbilgi.get(position))}
            }

       }
        holder.itemView.setOnClickListener {
            onLongClickListener?.let { it1 -> it1(saatbilgi.get(position)) }
        }

    }



    override fun getItemCount(): Int {

        return saatbilgi.size
    }


}