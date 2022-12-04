package com.example.foodnavigator

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.collections.ArrayList



class MainActivity : AppCompatActivity() {

    var versionList = ArrayList<Versions>()
    private lateinit var adapter: VersionAdapter
    private lateinit var addsBtn:FloatingActionButton
    lateinit var saveBtn: Button
    lateinit var databaseHelper : DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addsBtn= findViewById(R.id.addingBtn)
        saveBtn= findViewById(R.id.idBtnSaveList)
        addsBtn.setOnClickListener{addInfo()}
        setRecyclerView()
        saveBtn!!.setOnClickListener{databaseHelper!!.saveRecipes(versionList)}
    }



    private fun addInfo() {
        val inflter = LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.add_item, null)
        val addDialog=AlertDialog.Builder(this)
        val etelNev = v.findViewById<EditText>(R.id.etelNev)
        val etelHozzavalo = v.findViewById<EditText>(R.id.etelHozzavalo)

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok")
        {
            dialog,_->
            val names = etelNev.text.toString()
            val hozz = etelHozzavalo.text.toString()
            versionList.add(Versions("CodeName: $names","$hozz"))
            Toast.makeText(this, "Sikeres hozzáadás", Toast.LENGTH_SHORT).show()

            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel")
        {
            dialog,_->
            dialog.dismiss()
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()

    }

    private fun setRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val versionAdapter = VersionAdapter(versionList)
        recyclerView.adapter = versionAdapter
        recyclerView.setHasFixedSize(true)


    }




}