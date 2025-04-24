package com.example.my_application2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class InicioFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_inicio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener el nombre del usuario de SharedPreferences
        val sharedPrefs = requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val userName = sharedPrefs.getString("name", "")

        // Actualizar el texto de bienvenida con el nombre del usuario
        val welcomeText = view.findViewById<TextView>(R.id.welcomeText)
        welcomeText.text = "Bienvenido, $userName"
    }

    companion object {
        fun newInstance() = InicioFragment()
    }
} 