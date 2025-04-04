package com.bignerdranch.android.geoquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.geoquiz.databinding.FragmentQuestionListBinding

class QuestionListFragment : Fragment() {

    private var _binding: FragmentQuestionListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up RecyclerView
        binding.questionRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//        binding.questionRecyclerView.adapter = QuestionAdapter(getSampleQuestions()) { question ->
//            val action = QuestionListFragmentDirections.actionQuestionListFragmentToQuestionDetailFragment(question.textResId)
//            findNavController().navigate(action)
//        }
        binding.questionRecyclerView.adapter = QuestionAdapter(getSampleQuestions()) { question ->
            val action = QuestionListFragmentDirections.actionQuestionListFragmentToQuestionDetailFragment(
                questionTextResId = question.textResId, // Ensure this matches the argument name
                answer = question.answer,
                cheated = question.cheated
            )
            findNavController().navigate(action)
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getSampleQuestions(): List<Question> {
        return listOf(
            Question(R.string.question_australia, true, false),
            Question(R.string.question_oceans, true, false),
            Question(R.string.question_mideast, false, false),
            Question(R.string.question_africa, false, false),
            Question(R.string.question_americas, true, false),
            Question(R.string.question_asia, true, false)
        )
    }
}

//package com.bignerdranch.android.geoquiz
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import androidx.navigation.fragment.findNavController
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.bignerdranch.android.geoquiz.databinding.FragmentQuestionListBinding
//
//class QuestionListFragment : Fragment() {
//
//    private var _binding: FragmentQuestionListBinding? = null
//    private val binding get() = _binding!!
//
//    private val quizViewModel: QuizViewModel by viewModels()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentQuestionListBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding.recyclerView.layoutManager = LinearLayoutManager(context)
//        quizViewModel.questions.observe(viewLifecycleOwner) { questions ->
//            binding.recyclerView.adapter = QuestionAdapter(questions) { question ->
//                val action = QuestionListFragmentDirections.actionQuestionListFragmentToQuestionDetailFragment(question.textResId, question.answer)
//                findNavController().navigate(action)
//            }
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}