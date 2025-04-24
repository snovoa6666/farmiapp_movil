package com.example.my_application2

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import java.io.File
import java.io.FileOutputStream

class ProfileFragment : Fragment() {

    private lateinit var profileImage: ImageView
    private lateinit var nameInput: TextInputEditText
    private lateinit var emailInput: TextInputEditText
    private lateinit var phoneInput: TextInputEditText
    private lateinit var saveButton: MaterialButton
    private lateinit var changePhotoButton: FloatingActionButton

    private val pickImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                // Guardar la imagen seleccionada
                saveImage(uri)
                // Mostrar la imagen en el ImageView
                profileImage.setImageURI(uri)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileImage = view.findViewById(R.id.profileImage)
        nameInput = view.findViewById(R.id.nameInput)
        emailInput = view.findViewById(R.id.emailInput)
        phoneInput = view.findViewById(R.id.phoneInput)
        saveButton = view.findViewById(R.id.saveButton)
        changePhotoButton = view.findViewById(R.id.changePhotoButton)

        // Cargar datos del usuario
        loadUserData()

        // Cargar imagen de perfil si existe
        loadProfileImage()

        changePhotoButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            pickImage.launch(intent)
        }

        saveButton.setOnClickListener {
            saveUserData()
        }
    }

    private fun loadUserData() {
        val sharedPrefs = requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE)
        nameInput.setText(sharedPrefs.getString("name", ""))
        emailInput.setText(sharedPrefs.getString("email", ""))
        phoneInput.setText(sharedPrefs.getString("phone", ""))
    }

    private fun saveUserData() {
        val sharedPrefs = requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE)
        with(sharedPrefs.edit()) {
            putString("name", nameInput.text.toString())
            putString("email", emailInput.text.toString())
            putString("phone", phoneInput.text.toString())
            apply()
        }
        Toast.makeText(context, "Datos guardados correctamente", Toast.LENGTH_SHORT).show()
    }

    private fun saveImage(uri: Uri) {
        try {
            val inputStream = requireContext().contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            
            val file = File(requireContext().filesDir, "profile_image.jpg")
            FileOutputStream(file).use { out ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Error al guardar la imagen", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadProfileImage() {
        val file = File(requireContext().filesDir, "profile_image.jpg")
        if (file.exists()) {
            val bitmap = BitmapFactory.decodeFile(file.absolutePath)
            profileImage.setImageBitmap(bitmap)
        }
    }

    companion object {
        fun newInstance() = ProfileFragment()
    }
} 