package com.estudo.appestudo.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.estudo.appestudo.databinding.UiMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.textTranslate.collect { word ->
                    binding.tvWordTranslate.text = word
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.textDefinition.collect { def ->
                    definitions.add(def)
                    binding.lvDefinition.adapter = ArrayAdapter(
                        this@MainActivity,
                        android.R.layout.simple_list_item_1, definitions
                    )
                }
            }
        }

    }
}

