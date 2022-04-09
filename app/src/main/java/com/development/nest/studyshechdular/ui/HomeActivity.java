package com.development.nest.studyshechdular.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.development.nest.studyshechdular.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private static Context context;
    CardView cvNotes;
    CardView cvSchedule;
    CardView cvGPA;
    CardView cvCalculator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        HomeActivity.context = getApplicationContext();
        cvNotes = findViewById(R.id.cvNotes);
        cvSchedule = findViewById(R.id.cvSchedule);
        cvGPA = findViewById(R.id.cvGPA);
        cvCalculator = findViewById(R.id.cvCalculator);
        cvSchedule.setOnClickListener(view -> {
            startActivity(new Intent(this, SchedulerActivity.class));
        });
        cvNotes.setOnClickListener(view -> {
            startActivity(new Intent(this, NotesActivity.class));
        });
        cvGPA.setOnClickListener(view -> {
            startActivity(new Intent(this, CgpaActivity.class));
        });
        cvCalculator.setOnClickListener(view -> {
            startActivity(new Intent(this, CalculatorActivity.class));
        });
    }

    public static Context getAppContext() {
        return HomeActivity.context;
    }

    public static int getPx(Context context, int dimensionDp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dimensionDp * density + 0.5f);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cvNotes:
                startActivity(new Intent(this, SchedulerActivity.class));
                break;
            case R.id.cvCalculator:
                startActivity(new Intent(this, NotesActivity.class));
                break;
            case R.id.cvSchedule:
                // startActivity(new Intent(this, CalculatorActivity.class));
                break;
            case R.id.cvGPA:
                //  startActivity(new Intent(this, CgpaActivity.class));
                break;
        }
    }
}