package com.rolando.casapaico.laboratoriocalificado03

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rolando.casapaico.laboratoriocalificado03.databinding.ItemTeacherBinding

class TeacherAdapter(
    var list: List<TeachersResponse>
): RecyclerView.Adapter<TeacherAdapter.ViewHolder>() {


    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemTeacherBinding.bind(view)

        fun bind(teacher: TeachersResponse) {
            binding.tvName.text = teacher.last_name
            binding.tvEmail.text = teacher.email
            binding.tvPhone.text = teacher.phone_number

            Glide
                .with(itemView)
                .load(teacher.image_url)
                .into(binding.ivPokemon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemTeacherBinding.inflate(LayoutInflater.from(parent.context),parent ,false)
        return ViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemTeacher = list[position]
        holder.bind(itemTeacher)
    }

    override fun getItemCount(): Int = list.size

}

