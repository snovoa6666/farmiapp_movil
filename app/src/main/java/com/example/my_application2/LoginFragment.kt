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

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usernameInput = view.findViewById<TextInputEditText>(R.id.usernameInput)
        val passwordInput = view.findViewById<TextInputEditText>(R.id.passwordInput)
        val loginButton = view.findViewById<MaterialButton>(R.id.loginButton)
        val registerButton = view.findViewById<MaterialButton>(R.id.registerButton)

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Verificar credenciales en SharedPreferences
            val sharedPrefs = requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE)
            val savedUsername = sharedPrefs.getString("username", "")
            val savedPassword = sharedPrefs.getString("password", "")

            if (username == savedUsername && password == savedPassword) {
                Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                // Navegar a la página de inicio
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.content_frame, InicioFragment.newInstance())
                    .commit()
            } else {
                Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        registerButton.setOnClickListener {
            // Navegar a la pantalla de registro
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.content_frame, RegisterFragment.newInstance())
            transaction.addToBackStack(null) // Permite volver atrás con el botón de retroceso
            transaction.commit()
        }
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
} 