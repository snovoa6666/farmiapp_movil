package com.example.my_application2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameInput = view.findViewById<TextInputEditText>(R.id.nameInput)
        val emailInput = view.findViewById<TextInputEditText>(R.id.emailInput)
        val phoneInput = view.findViewById<TextInputEditText>(R.id.phoneInput)
        val usernameInput = view.findViewById<TextInputEditText>(R.id.usernameInput)
        val passwordInput = view.findViewById<TextInputEditText>(R.id.passwordInput)
        val confirmPasswordInput = view.findViewById<TextInputEditText>(R.id.confirmPasswordInput)
        val registerButton = view.findViewById<MaterialButton>(R.id.registerButton)
        val loginButton = view.findViewById<MaterialButton>(R.id.loginButton)

        registerButton.setOnClickListener {
            val name = nameInput.text.toString()
            val email = emailInput.text.toString()
            val phone = phoneInput.text.toString()
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()
            val confirmPassword = confirmPasswordInput.text.toString()

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || 
                username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(context, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Guardar los datos en SharedPreferences
            val sharedPrefs = requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE)
            with(sharedPrefs.edit()) {
                putString("username", username)
                putString("password", password)
                putString("name", name)
                putString("email", email)
                putString("phone", phone)
                apply()
            }

            Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
            // Volver a la pantalla de login
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, LoginFragment.newInstance())
                .commit()
        }

        loginButton.setOnClickListener {
            // Volver a la pantalla de login
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, LoginFragment.newInstance())
                .commit()
        }
    }

    companion object {
        fun newInstance() = RegisterFragment()
    }
} 