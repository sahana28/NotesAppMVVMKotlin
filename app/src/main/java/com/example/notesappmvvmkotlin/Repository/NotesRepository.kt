package com.example.notesappmvvmkotlin.Repository

import androidx.lifecycle.LiveData
import com.example.notesappmvvmkotlin.Dao.NotesDao
import com.example.notesappmvvmkotlin.Model.Notes

class NotesRepository(val dao: NotesDao) {

    fun getAllNotes() = dao.getNotes()

    fun getHighNotes() = dao.getHighNotes()

    fun getMediumNotes() = dao.getMediumNotes()

    fun getLowNotes() = dao.getLowNotes()

    fun insertNotes(notes: Notes) {
        dao.insertNotes(notes)
    }

    fun deleteNotes(id: Int) {
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes) {
        dao.updateNotes(notes)
    }
}