package com.estudo.appestudo.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle.*
import androidx.lifecycle.Lifecycle.State.*
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UiMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btNewWord.setOnClickListener {
            val dialog = DialogInsertNewWord()
            dialog.show(supportFragmentManager, dialog.tag)
        }

        binding.btTranslate.setOnClickListener {
            viewModel.fetchWordWithDefinitions(binding.edText.text.toString())
        }

        lifecycleScope.launch {
            repeatOnLifecycle(STARTED) {
                launch {
                    viewModel.textTranslate.collect { word ->
                        binding.tvWordTranslate.text = word
                    }
                }
                launch {
                    viewModel.textDefinition.collect { definitions ->
                            binding.lvDefinition.adapter = ArrayAdapter(
                                this@MainActivity,
                                android.R.layout.simple_list_item_1,
                                definitions
                        )
                    }
                }
            }
        }

    }
}

