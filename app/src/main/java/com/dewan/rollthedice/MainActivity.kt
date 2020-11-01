package com.dewan.rollthedice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    var TAG:String = "MainActivity"
    var random_number:Int = 0
    var SCORE_PLAYER_A:Int = 0
    var SCORE_PLAYER_B:Int = 0
    var ACTIVE_PLAYER_A:Boolean = true
    var ACTIVE_PLAYER_B:Boolean = false
    var GAME_POINT:Int = 100
    var GAME_END_MSG:String? = null
    var GAME_END_MSG_DEFAULT:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvGameOverMsg: TextView = findViewById<Button>(R.id.tvGameOverMsg)
        val tvGamePointA: TextView = findViewById<Button>(R.id.tvGamePointA)
        val tvGamePointB: TextView = findViewById<Button>(R.id.tvGamePointB)
        val dice_image: ImageView = findViewById(R.id.dice_image)
        val btnGamePlay: Button = findViewById<Button>(R.id.btnGamePlay)


        //deshabilitar dice_image
        dice_image.isEnabled = false

        btnGamePlay.setOnClickListener {

            //habilitar dice_image
            dice_image.isEnabled = true

            //reset the score for the player
            SCORE_PLAYER_A = 0
            SCORE_PLAYER_B = 0

            //clear the text view and set the default value
            tvGameOverMsg.text = ""
            tvGamePointA.text = "0"
            tvGamePointB.text = "0"

            //enable the player A
            ACTIVE_PLAYER_A = true
            ACTIVE_PLAYER_B = false

            //reset the image with dice 1
            dice_image.setImageResource(R.drawable.dice1)

        }


        dice_image.setOnClickListener {
            // generate random number
            random_number = (1..6).random()

            //when expression
            when(random_number){
                1 -> {
                    dice_image.setImageResource(R.drawable.dice1)
                }
                2 -> {
                    dice_image.setImageResource(R.drawable.dice2)
                }
                3 -> {
                    dice_image.setImageResource(R.drawable.dice3)
                }
                4 -> {
                    dice_image.setImageResource(R.drawable.dice4)
                }
                5 ->{
                    dice_image.setImageResource(R.drawable.dice5)
                }
                6 ->{
                    dice_image.setImageResource(R.drawable.dice6)
                }
            }

            if (ACTIVE_PLAYER_A){
                //add the score to player a
                SCORE_PLAYER_A  = SCORE_PLAYER_A +  random_number
                //set the text of the text view
                tvGamePointA.text = SCORE_PLAYER_A.toString()
                //enable player b and disable player a
                ACTIVE_PLAYER_A = false
                ACTIVE_PLAYER_B = true

                //check the game point
                if (SCORE_PLAYER_A >= 100){
                    GAME_END_MSG_DEFAULT = resources.getText(R.string.game_over_msg).toString()
                    GAME_END_MSG = GAME_END_MSG_DEFAULT + " Jugador A"
                    tvGameOverMsg.text = GAME_END_MSG

                    //disable the image view
                    dice_image.isEnabled = false
                }

            }
            else {
                //add the score to player b
                SCORE_PLAYER_B += random_number
                //set the text of the text view
                tvGamePointB.text = SCORE_PLAYER_B.toString()
                //enable player a and disable player b
                ACTIVE_PLAYER_B = false
                ACTIVE_PLAYER_A = true

                //check the game point
                if (SCORE_PLAYER_B >= 100){
                    GAME_END_MSG_DEFAULT = resources.getText(R.string.game_over_msg).toString()
                    GAME_END_MSG = GAME_END_MSG_DEFAULT + " Jugador B"
                    tvGameOverMsg.text = GAME_END_MSG

                    //disable the image view
                    dice_image.isEnabled = false
                }
            }

            //show the random number in our log cat
            Log.e(TAG,random_number.toString())
        }



    }
}
