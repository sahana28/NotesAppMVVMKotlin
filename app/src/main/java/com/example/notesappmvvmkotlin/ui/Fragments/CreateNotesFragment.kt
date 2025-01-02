package com.example.notesappmvvmkotlin.ui.Fragments

import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.notesappmvvmkotlin.Model.Notes
import com.example.notesappmvvmkotlin.R
import com.example.notesappmvvmkotlin.ViewModel.NotesViewModel
import com.example.notesappmvvmkotlin.databinding.FragmentCreateNotesBinding
import java.util.Date


class CreateNotesFragment : Fragment() {

    lateinit var binding : FragmentCreateNotesBinding
    var priorityString : String = "1"
    val viewModel : NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)

        binding.priorityGreen.setImageResource(R.drawable.baseline_done_24)

        binding.priorityGreen.setOnClickListener {
            priorityString = "1"
            binding.priorityGreen.setImageResource(R.drawable.baseline_done_24)
            binding.priorityRed.setImageResource(0)
            binding.priorityYellow.setImageResource(0)
        }

        binding.priorityYellow.setOnClickListener {
            priorityString = "2"
            binding.priorityYellow.setImageResource(R.drawable.baseline_done_24)
            binding.priorityRed.setImageResource(0)
            binding.priorityGreen.setImageResource(0)
        }

        binding.priorityRed.setOnClickListener {
            priorityString = "3"
            binding.priorityRed.setImageResource(R.drawable.baseline_done_24)
            binding.priorityYellow.setImageResource(0)
            binding.priorityGreen.setImageResource(0)
        }
        binding.btnSaveNotes.setOnClickListener {
              createNotes(it)
        }
        return binding.root
    }

    fun createNotes(it: View?) {
       val title = binding.editTitle.text.toString()
        val subtitle = binding.editSubtitle.text.toString()
        val notes = binding.createNotes.text.toString()

        val d = Date()
        val sdf = SimpleDateFormat("MMMM d, YYYY")
        val notesDate = sdf.format(Date()).toString()
        val notesData = Notes(null, title = title, subTitle = subtitle, notes = notes, date = notesDate, priority = priorityString)
        viewModel.addNotes(notesData)
        Toast.makeText(requireContext(),"Notes added",Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)
    }
}