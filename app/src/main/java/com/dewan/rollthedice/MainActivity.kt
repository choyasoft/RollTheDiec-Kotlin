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

            //Resetear la puntuación de los jugadores
            SCORE_PLAYER_A = 0
            SCORE_PLAYER_B = 0

            //Limpiar el TexyView al valor inicial
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
                //Añadir puntuación al jugador A
                SCORE_PLAYER_A  = SCORE_PLAYER_A +  random_number
                //Setear el texto del TextView
                tvGamePointA.text = SCORE_PLAYER_A.toString()
                //Habilitar el jugador B y deshabilitar el jugador A
                ACTIVE_PLAYER_A = false
                ACTIVE_PLAYER_B = true

                //Comprobar el objetivo
                if (SCORE_PLAYER_A >= 100){
                    GAME_END_MSG_DEFAULT = resources.getText(R.string.game_over_msg).toString()
                    GAME_END_MSG = GAME_END_MSG_DEFAULT + " Jugador A"
                    tvGameOverMsg.text = GAME_END_MSG

                    //Desactivar el ImageView
                    dice_image.isEnabled = false
                }

            }
            else {
                //Añadir puntuación al jugador B
                SCORE_PLAYER_B += random_number
                //Setear el texto del TextView
                tvGamePointB.text = SCORE_PLAYER_B.toString()
                //Habilitar el jugador A y deshabilitar el jugador B
                ACTIVE_PLAYER_B = false
                ACTIVE_PLAYER_A = true

                //Comprobar el objetivo
                if (SCORE_PLAYER_B >= 100){
                    GAME_END_MSG_DEFAULT = resources.getText(R.string.game_over_msg).toString()
                    GAME_END_MSG = GAME_END_MSG_DEFAULT + " Jugador B"
                    tvGameOverMsg.text = GAME_END_MSG

                    //Desactivar el ImageView
                    dice_image.isEnabled = false
                }
            }

            //Mostrar el número aleatorio en el log cat
            Log.e(TAG,random_number.toString())
        }



    }
}
