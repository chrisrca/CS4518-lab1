package com.bignerdranch.android.geoquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bignerdranch.android.geoquiz.databinding.FragmentQuestionDetailBinding

class QuestionDetailFragment : Fragment() {

    private var _binding: FragmentQuestionDetailBinding? = null
    private val binding get() = _binding!!

    private val args: QuestionDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.questionTextView.setText(args.questionTextResId)

        binding.trueButton.setOnClickListener {
            showResult(args.answer == true)
        }
        binding.falseButton.setOnClickListener {
            showResult(args.answer == false)
        }
    }

    private fun showResult(isCorrect: Boolean) {
        val messageResId = if (isCorrect) R.string.correct_toast else R.string.incorrect_toast
        binding.resultTextView.setText(messageResId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}