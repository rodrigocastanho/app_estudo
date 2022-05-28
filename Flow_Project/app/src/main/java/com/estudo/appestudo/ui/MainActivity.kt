package com.estudo.appestudo.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.estudo.appestudo.databinding.UiMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: UiMainBinding
    private val viewModel by viewModels<WordsTranslatedViewModel>()

    private val definitions = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UiMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btTranslate.setOnClickListener {
            viewModel.translate(binding.edText.text.toString())
            definitions.clear()
        }

        viewModel.textTranslate.observe(this, { word ->
            binding.tvWordTranslate.text = word
        })


        viewModel.textDefinition.observe(this, { def ->
            definitions.add(def)
            binding.lvDefinition.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, definitions)
        })

    }
}

