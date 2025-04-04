package com.bignerdranch.android.geoquiz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bignerdranch.android.geoquiz.databinding.FragmentQuestionDetailBinding

class QuestionDetailFragment : Fragment() {
    private var _binding: FragmentQuestionDetailBinding? = null
    private val binding get() = _binding!!

    private val args: QuestionDetailFragmentArgs by navArgs()
    private var cheated = false

    private val quizViewModel: QuizViewModel by viewModels()

    private val cheatLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val didCheat = result.data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
            if (didCheat) {
                quizViewModel.markCurrentQuestionCheated()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val questionTextResId = args.questionTextResId
        val answer = args.answer
        cheated = args.cheated

        binding.questionTextView.setText(questionTextResId)

        binding.trueButton.setOnClickListener { checkAnswer(true) }
        binding.falseButton.setOnClickListener { checkAnswer(false) }

        binding.cheatButton.setOnClickListener {
            val intent = CheatActivity.newIntent(requireContext(), answer)
            cheatLauncher.launch(intent)
        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = when {
            quizViewModel.isCheater -> R.string.judgment_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }
        Toast.makeText(requireContext(), messageResId, Toast.LENGTH_SHORT).show()
}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
