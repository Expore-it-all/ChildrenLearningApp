package edu.byui.childrenlearningapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

import edu.byui.childrenlearningapp.Factories.AnimalFactory;
import edu.byui.childrenlearningapp.Models.A_Animal;

public class AnimalQuestionGame extends AppCompatActivity {
    private A_Animal selectedAnswer;
    private ArrayList<A_Animal> wrongAnswers = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<String> animalList = AnimalFactory.getAllPossibleAnimals();
        Random rand =  new Random();
        Log.d("List",animalList.toString());
        String selectedAnimal = animalList.get(rand.nextInt(animalList.size()));
        selectedAnswer = AnimalFactory.getAnimal(selectedAnimal,this,true);

        Log.d("Animal", "onCreate: selected Animal"+selectedAnimal +"  "+ "selected answer "+selectedAnswer);
        assert selectedAnswer != null;
        animalList.remove(selectedAnswer.getAnimalName());
        wrongAnswers.add(AnimalFactory.getAnimal(animalList.get(rand.nextInt(animalList.size())),this,false));
        wrongAnswers.add(AnimalFactory.getAnimal(animalList.get(rand.nextInt(animalList.size())),this,false));
        Log.d("wrong answers ",wrongAnswers.toString());


        setContentView(R.layout.activity_animal_question_game);

        MediaPlayer info = MediaPlayer.create(this, R.raw.quest_select_animal);
        MediaPlayer animal = MediaPlayer.create(this, selectedAnswer.getAnimalSoundRef());
        info.start();
        info.setNextMediaPlayer(animal);

        ImageButton button1;
        ImageButton button2;
        ImageButton button3;
        Random randButton = new Random();
        int randIntButton = randButton.nextInt(3);

        Log.i("AnimalQuestionGame", "Random number "+ randIntButton);

        if(randIntButton ==1) {
            button1 = findViewById(R.id.answer1);
            button2 = findViewById(R.id.answer2);
            button3 = findViewById(R.id.answer3);
        }
        else if(randIntButton ==2){
            button1 = findViewById(R.id.answer2);
            button2 = findViewById(R.id.answer3);
            button3 = findViewById(R.id.answer1);
        }
        else {
            button1 = findViewById(R.id.answer3);
            button2 = findViewById(R.id.answer1);
            button3 = findViewById(R.id.answer2);
        }


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAnswer.playCorrectSound();
                //Move to next game or Correct answer
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent next = new Intent(AnimalQuestionGame.this, AnimalQuestionGame.class);
                        startActivity(next);
                    }
                }, 2000); // Millisecond 1000 = 1 sec

            }
        });
        button1.setImageResource(selectedAnswer.getAnimalImageRef());
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wrongAnswers.get(0).playWrongSound();
            }
        });
        button2.setImageResource(wrongAnswers.get(0).getAnimalImageRef());


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wrongAnswers.get(1).playWrongSound();
            }
        });
        button3.setImageResource(wrongAnswers.get(1).getAnimalImageRef());

    }



    public void GoToMenu(View view) {
        Intent menuGame = new Intent(this, GameMenu.class);
        startActivity(menuGame);
    }
}
