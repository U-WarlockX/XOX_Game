package com.example.xox_game

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


class GameActivity : AppCompatActivity(), View.OnClickListener {

    private var playerOneActive = true
    private lateinit var playerOneScore: TextView
    private lateinit var playerTwoScore: TextView
    private lateinit var playerStatus: TextView
    private val buttons = arrayOfNulls<Button>(9)
    private lateinit var reset: Button
    private lateinit var playagain: Button
    private val gameState = IntArray(9) { 2 }
    private val winningPositions = arrayOf(
        intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8), intArrayOf(0, 3, 6),
        intArrayOf(1, 4, 7), intArrayOf(2, 5, 8), intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)
    )
    private var rounds = 0
    private var playerOneScoreCount = 0
    private var playerTwoScoreCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)



        playerOneScore = findViewById(R.id.score_Player1)
        playerTwoScore = findViewById(R.id.score_Player2)
        playerStatus = findViewById(R.id.textStatus)
        reset = findViewById(R.id.btn_reset)
        playagain = findViewById(R.id.btn_play_again)


        for (i in buttons.indices) {
            val buttonId = resources.getIdentifier("btn$i", "id", packageName)
            buttons[i] = findViewById(buttonId)
            buttons[i]?.setOnClickListener(this)
        }

        playerOneScoreCount = 0
        playerTwoScoreCount = 0
        playerOneActive = true
        rounds = 0

        reset.setOnClickListener {
            playAgain()
            playerOneScoreCount = 0
            playerTwoScoreCount = 0
            updatePlayerScore()
        }

        playagain.setOnClickListener { playAgain() }
    }

    override fun onClick(view: View) {
        if ((view as Button).text.toString() != "") {
            return
        } else if (checkWinner()) {
            return
        }

        val buttonID = view.resources.getResourceEntryName(view.id)
        val gameStatePointer = buttonID.substring(buttonID.length - 1).toInt()

        if (playerOneActive) {
            view.text = "X"
            view.setTextColor(Color.parseColor("#ffc34a"))
            gameState[gameStatePointer] = 0
        } else {
            view.text = "O"
            view.setTextColor(Color.parseColor("#70fc3a"))
            gameState[gameStatePointer] = 1
        }
        rounds++

        if (checkWinner()) {
            if (playerOneActive) {
                playerOneScoreCount++
                updatePlayerScore()
                playerStatus.text = "Player-1 has won"
            } else {
                playerTwoScoreCount++
                updatePlayerScore()
                playerStatus.text = "Player-2 has won"
            }
        } else if (rounds == 9) {
            playerStatus.text = "No Winner"
        } else {
            playerOneActive = !playerOneActive
        }
    }

    private fun checkWinner(): Boolean {
        for (winningPos in winningPositions) {
            if (gameState[winningPos[0]] == gameState[winningPos[1]] &&
                gameState[winningPos[1]] == gameState[winningPos[2]] &&
                gameState[winningPos[0]] != 2
            ) {
                return true
            }
        }
        return false
    }

    private fun playAgain() {
        rounds = 0
        playerOneActive = true
        for (i in buttons.indices) {
            gameState[i] = 2
            buttons[i]?.text = ""
        }
        playerStatus.text = "Status"
    }

    private fun updatePlayerScore() {
        playerOneScore.text = playerOneScoreCount.toString()
        playerTwoScore.text = playerTwoScoreCount.toString()
    }


}
