package com.example.notesappmvvmkotlin.ui.Fragments

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notesappmvvmkotlin.Model.Notes
import com.example.notesappmvvmkotlin.R
import com.example.notesappmvvmkotlin.ViewModel.NotesViewModel
import com.example.notesappmvvmkotlin.databinding.FragmentEditNotesBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.Date


class EditNotesFragment : Fragment(), MenuProvider {

    val oldNotes by navArgs<EditNotesFragmentArgs>()
    val viewModel : NotesViewModel by viewModels()
    lateinit var binding : FragmentEditNotesBinding
    var priorityString : String = "1"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditNotesBinding.inflate(layoutInflater, container, false)
        binding.editTitle.setText(oldNotes.data.title)
        binding.editSubtitle.setText(oldNotes.data.subTitle)
        binding.editNotes.setText(oldNotes.data.notes)
        when (oldNotes.data.priority) {
            "1" -> {
                priorityString = "1"
                binding.priorityGreen.setImageResource(R.drawable.baseline_done_24)
                binding.priorityRed.setImageResource(0)
                binding.priorityYellow.setImageResource(0)
            }

            "2" -> {
                priorityString = "2"
                binding.priorityYellow.setImageResource(R.drawable.baseline_done_24)
                binding.priorityRed.setImageResource(0)
                binding.priorityGreen.setImageResource(0)
            }

            "3" -> {
                priorityString = "3"
                binding.priorityRed.setImageResource(R.drawable.baseline_done_24)
                binding.priorityYellow.setImageResource(0)
                binding.priorityGreen.setImageResource(0)
            }
        }

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

        binding.btnEditSaveNotes.setOnClickListener {
            updateNotes(it)
        }

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }
        fun updateNotes(it: View?) {
            oldNotes.data.title = binding.editTitle.text.toString()
            oldNotes.data.subTitle = binding.editSubtitle.text.toString()
            oldNotes.data.notes = binding.editNotes.text.toString()
            oldNotes.data.priority = priorityString
            viewModel.updateNotes(oldNotes.data)

            val title = binding.editTitle.text.toString()
            val subtitle = binding.editSubtitle.text.toString()
            val notes = binding.editNotes.text.toString()

            val d = Date()
            val sdf = SimpleDateFormat("MMMM d, YYYY")
            val notesDate = sdf.format(Date()).toString()
            val notesData = Notes(oldNotes.data.id, title = title, subTitle = subtitle, notes = notes, date = notesDate, priority = priorityString)
            viewModel.updateNotes(notesData)
            Toast.makeText(requireContext(),"Notes updated successfully", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)
        }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if(menuItem.itemId == R.id.menu_delete) {
            val bottomSheet : BottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)

            val btnYes = bottomSheet.findViewById<Button>(R.id.dialog_yes)
            val btnNo = bottomSheet.findViewById<Button>(R.id.dialog_no)
            btnYes?.setOnClickListener {
                viewModel.deleteNotes(oldNotes.data.id!!)
                bottomSheet.dismiss()
            }

            btnNo?.setOnClickListener {
                bottomSheet.dismiss()
            }
            bottomSheet.show()
        }
        return true
    }
}