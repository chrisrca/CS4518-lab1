package com.bignerdranch.android.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.geoquiz.databinding.ActivityCheatBinding

const val EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE =
    "com.bignerdranch.android.geoquiz.answer_is_true"

class CheatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheatBinding
    
    private var answerIsTrue = false
    
    private var isAnswerShown = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        if (savedInstanceState != null) {
            isAnswerShown = savedInstanceState.getBoolean("answer_shown", false)
            if (isAnswerShown) {
                val answerText = if (answerIsTrue) R.string.true_button else R.string.false_button
                binding.answerTextView.setText(answerText)
                setAnswerShownResult(true)
            }
        }

        binding.showAnswerButton.setOnClickListener {
            val answerText = if (answerIsTrue) R.string.true_button else R.string.false_button
            binding.answerTextView.setText(answerText)
            isAnswerShown = true
            setAnswerShownResult(true)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("answer_shown", isAnswerShown)
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}

