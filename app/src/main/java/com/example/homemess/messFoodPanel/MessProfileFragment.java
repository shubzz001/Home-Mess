package com.example.homemess.messFoodPanel;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.homemess.R;

public class MessProfileFragment extends Fragment {

    Button postFood;
    ConstraintLayout backimg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mess_prifile, null);
        getActivity().setTitle("Post Food");


        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.bg2), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img2), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img3), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img4), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img5), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img6), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img7), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img8), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.bg3), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img9), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img10), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img11), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img11), 3000);

        animationDrawable.setOneShot(false);
        animationDrawable.setEnterFadeDuration(850);
        animationDrawable.setEnterFadeDuration(1600);
        backimg = v.findViewById(R.id.back1);
        backimg.setBackgroundDrawable(animationDrawable);
        animationDrawable.start();

        postFood = (Button) v.findViewById(R.id.post_dish);

        postFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), mess_postFood.class));
            }
        });

        return v;
    }
}