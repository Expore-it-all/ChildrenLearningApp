package edu.byui.childrenlearningapp.Models;

import android.content.Context;
import android.media.MediaPlayer;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Random;

import edu.byui.childrenlearningapp.R;

public class Color extends A_Color {
    String colorName = "";
    WeakReference<Context> contextWeakReference;
    int soundRef;
    boolean isCorrect = false;
    int imgRef;


    public Color(String colorName, Context context, int soundRef, boolean correct ,int imageRef) {
        this.colorName = colorName;
        this.contextWeakReference = new WeakReference<>(context);
        this.soundRef = soundRef;
        this.isCorrect = correct;
        this.imgRef = imageRef;
    }


    @Override
    public void playWrongSound() {
        if(contextWeakReference.get() == null){
            return;
        }
        ArrayList<MediaPlayer> players = new ArrayList<>();

        MediaPlayer player = MediaPlayer.create(contextWeakReference.get(), R.raw.warning_oh_no);
        players.add(player);
        player = MediaPlayer.create(contextWeakReference.get(), R.raw.warning_oh_oh);
        players.add(player);
        player = MediaPlayer.create(contextWeakReference.get(), R.raw.warning_try_again);
        players.add(player);

        Random rand = new Random();
        players.get(rand.nextInt(players.size())).start();

    }

    @Override
    public void playCorrectSound() {
        if(contextWeakReference.get() == null){
            return;
        }
        ArrayList<MediaPlayer> players = new ArrayList<>();

        MediaPlayer player = MediaPlayer.create(contextWeakReference.get(), R.raw.compliments_awesome);
        players.add(player);
        player = MediaPlayer.create(contextWeakReference.get(), R.raw.compliments_fantastic);
        players.add(player);
        player = MediaPlayer.create(contextWeakReference.get(), R.raw.compliments_great);
        players.add(player);
        player = MediaPlayer.create(contextWeakReference.get(), R.raw.compliments_well_done);
        players.add(player);

        Random rand = new Random();
        players.get(rand.nextInt(players.size())).start();
    }

    @Override
    public void playColorSound() {
        if(contextWeakReference.get() == null){
            return;
        }

        MediaPlayer.create(contextWeakReference.get(),soundRef).start();
    }

    @Override
    public String getColorName() {
        return this.colorName;
    }

    @Override
    public int getColorSoundRef() {
        return soundRef;
    }
}
