package com.example.school.moorhuhn;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundPlayer {
    private static SoundPool soundPool;
    private static int fireSound;
    private static int reloadSound;
    private static int roosterSound;

    public SoundPlayer(Context context){
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC,0);
        fireSound = soundPool.load(context, R.raw.fire,1);
        reloadSound = soundPool.load(context, R.raw.reload,1);
        roosterSound = soundPool.load(context, R.raw.rooster,1);
    }
    public void playFireSound(){
        soundPool.play(fireSound, 1.0f, 1.0f, 1,0,1.0f);
    }
    public void playReloadSound(){
        soundPool.play(reloadSound, 1.0f, 1.0f, 1,0,1.0f);
    }
    public void playRoosterSound(){
        soundPool.play(roosterSound, 1.0f, 1.0f, 1,0,1.0f);
    }

}
