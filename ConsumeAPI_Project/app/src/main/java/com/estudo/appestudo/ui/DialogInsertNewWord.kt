package com.estudo.appestudo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import com.estudo.appestudo.databinding.DialogInsertNewWordBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DialogInsertNewWord: DialogFragment() {

    private var _binding: DialogInsertNewWordBinding? = null
    private val binding: DialogInsertNewWordBinding get() = _binding!!
    private val viewModel by activityViewModels<InsertNewWordViewModel>()

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog?.setCancelable(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogInsertNewWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btInsertDefition.setOnClickListener {
            val definitionWord = binding.edDefinition.text.toString()
            viewModel.insertDefinitionWord(definitionWord)
            binding.edDefinition.text?.clear()
        }

        binding.btInsertWord.setOnClickListener {
            val newWord = binding.edNewWord.text.toString()
            val translatedWord = binding.edWordTranslation.text.toString()

            viewModel.salvarNewWord(newWord, translatedWord)
        }

        binding.btInsertWordApi.setOnClickListener {
            val newWord = binding.edNewWord.text.toString()
            val translatedWord = binding.edWordTranslation.text.toString()

            viewModel.salvarNewWordApi(newWord, translatedWord)
        }

        viewModel.mensagem.observe(this) { status ->
            if (status) {
                Toast.makeText(context, "Salvo com sucesso", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(context, "Não foi possivel salvar", Toast.LENGTH_SHORT).show()
        }

        binding.btCancel.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}