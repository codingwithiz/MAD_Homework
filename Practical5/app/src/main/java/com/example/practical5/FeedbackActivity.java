package com.example.practical5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        //Conn ect with UI Widget RateBarFeedback, TVRating, ETFeedback, BtnFeedback
        RatingBar RateBarFeedback = findViewById(R.id.RateBarFeedback);
        TextView TVRating = findViewById(R.id.TVRating);
        EditText ETFeedback = findViewById(R.id.ETFeedback); //needed if you want to collect the feedback later
        Button BtnFeedback = findViewById(R.id.BtnFeedback);

        //The button OnClickListener to toast a message and share the cashback code is user has entered the feedback
        BtnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String message = "Thank you for your feedback!";
                    if(!ETFeedback.getText().toString().isEmpty())
                        message = message + "Please enjoy your RM5 Cashback: ABC123";
                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
            }
        });


        //The rating bar OnRatingBarChangeListener to change the rating whenever it is used by user.
        RateBarFeedback.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                TVRating.setText("You have rated " + rating);
            }
        });
    }
}