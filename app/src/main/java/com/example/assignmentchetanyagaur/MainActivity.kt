package com.example.assignmentchetanyagaur

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.assignmentchetanyagaur.ui.theme.DatabaseHelper
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var databaseHelper: DatabaseHelper
//    private lateinit var firebaseDatabase: FirebaseDatabase
//    private lateinit var firebaseStorage: FirebaseStorage

    private lateinit var etName: EditText
    private lateinit var etPhone: EditText
    private lateinit var etEmail: EditText
    private lateinit var tvDob: TextView
    private lateinit var ivProfilePic: ImageView
//    private lateinit var btnSaveSQLite: Button
    private lateinit var btnSaveFirebase: Button
    private var profilePictureUri: Uri? = null // To hold the selected image URI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        databaseHelper = DatabaseHelper(this)
//        firebaseDatabase = FirebaseDatabase.getInstance()
//        firebaseStorage = FirebaseStorage.getInstance()

        // Initialize views
        etName = findViewById(R.id.etName)
        etPhone = findViewById(R.id.etPhone)
        etEmail = findViewById(R.id.etEmail)
        tvDob = findViewById(R.id.tvDob)
        ivProfilePic = findViewById(R.id.ivProfilePic)
//        btnSaveSQLite = findViewById(R.id.btnSaveSQLite)
//        btnSaveFirebase = findViewById(R.id.btnSaveFirebase)

        // DatePickerDialog for DOB
        tvDob.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(this, { _, year, month, day ->
                tvDob.text = "$day/${month + 1}/$year"
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
            datePickerDialog.show()
        }

        // Image Picker
        ivProfilePic.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, IMAGE_PICK_CODE)
        }

        // Save to SQLite
//        btnSaveSQLite.setOnClickListener {
//            val name = etName.text.toString()
//            val phone = etPhone.text.toString()
//            val email = etEmail.text.toString()
//            val dob = tvDob.text.toString()
//            // Assuming insertUser is a method in your DatabaseHelper
//            val success = databaseHelper.insertUser(name, phone, email, dob, profilePictureUri.toString())
//            if (success) {
//                Toast.makeText(this, "Saved to SQLite", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(this, "Failed to save", Toast.LENGTH_SHORT).show()
//            }
//        }

        // Save to Firebase

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_PICK_CODE && resultCode == RESULT_OK && data != null) {
            profilePictureUri = data.data // Store the selected image URI
            ivProfilePic.setImageURI(profilePictureUri) // Display the image
        }
    }

    companion object {
        private const val IMAGE_PICK_CODE = 1000
    }
}
