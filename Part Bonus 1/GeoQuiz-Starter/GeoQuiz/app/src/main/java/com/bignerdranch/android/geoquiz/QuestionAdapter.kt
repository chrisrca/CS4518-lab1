package com.bignerdranch.android.geoquiz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.geoquiz.databinding.QuestionListItemBinding

class QuestionAdapter(
    private val questions: List<Question>,
    private val onClick: (Question) -> Unit
) : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    inner class QuestionViewHolder(private val binding: QuestionListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(question: Question) {
            binding.questionTextView.setText(question.textResId)
            binding.root.setOnClickListener { onClick(question) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val binding = QuestionListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    override fun getItemCount() = questions.size
}