package com.example.geoquiz

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

private const val Tag = "MainActivity"
private const val KEY_INDEX= "index"

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(Tag, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)
        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0)?: 0
        quizViewModel.currentIndex= currentIndex

        trueButton= findViewById(R.id.true_button)
        falseButton= findViewById(R.id.false_button)
        nextButton= findViewById(R.id.next_button)
        questionTextView= findViewById(R.id.question_text_view)

        trueButton.setOnClickListener { checkAnswer(true)
        }

        falseButton.setOnClickListener { checkAnswer(false)
        }

        nextButton.setOnClickListener { quizViewModel.moveToNext()
        updateQuestion()
        }

        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(Tag, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(Tag, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(Tag, "onPause() called")
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(Tag, "onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(Tag, "onDestroy() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(Tag, "onStop() called")
    }
    private fun updateQuestion(){
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = if (userAnswer == correctAnswer){
            R.string.correct_toast
        }else{
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
}