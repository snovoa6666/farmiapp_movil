package com.example.my_application2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class ProductosFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private var category: String = ""

    companion object {
        fun newInstance(category: String): ProductosFragment {
            val fragment = ProductosFragment()
            fragment.category = category
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_productos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        recyclerView = view.findViewById(R.id.recyclerView)
        adapter = ProductAdapter(getProductsByCategory())
        recyclerView.adapter = adapter
    }

    private fun getProductsByCategory(): List<Product> {
        return when (category) {
            "medicamentos" -> listOf(
                Product("Medicamento 1", "Analgésico", 29.99, R.drawable.ic_product),
                Product("Medicamento 2", "Antibiótico", 39.99, R.drawable.ic_product),
                Product("Medicamento 3", "Antiinflamatorio", 19.99, R.drawable.ic_product),
                Product("Medicamento 4", "Antigripal", 15.99, R.drawable.ic_product),
                Product("Medicamento 5", "Antialérgico", 25.99, R.drawable.ic_product),
                Product("Medicamento 6", "Vitaminas", 45.99, R.drawable.ic_product)
            )
            "suplementos" -> listOf(
                Product("Suplemento 1", "Proteína", 49.99, R.drawable.ic_product),
                Product("Suplemento 2", "Vitamina C", 29.99, R.drawable.ic_product),
                Product("Suplemento 3", "Omega 3", 39.99, R.drawable.ic_product),
                Product("Suplemento 4", "Calcio", 19.99, R.drawable.ic_product),
                Product("Suplemento 5", "Magnesio", 24.99, R.drawable.ic_product),
                Product("Suplemento 6", "Zinc", 34.99, R.drawable.ic_product)
            )
            "belleza" -> listOf(
                Product("Belleza 1", "Crema facial", 59.99, R.drawable.ic_product),
                Product("Belleza 2", "Protector solar", 45.99, R.drawable.ic_product),
                Product("Belleza 3", "Shampoo", 29.99, R.drawable.ic_product),
                Product("Belleza 4", "Acondicionador", 29.99, R.drawable.ic_product),
                Product("Belleza 5", "Mascarilla", 19.99, R.drawable.ic_product),
                Product("Belleza 6", "Sérum", 69.99, R.drawable.ic_product)
            )
            else -> emptyList()
        }
    }
} 