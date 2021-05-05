package com.example.lab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class MarksActivity extends AppCompatActivity {

    Button button;
    RecyclerView recyclerView;

    ArrayList<GradeModel> gradeModelArrayList;

    public MarksActivity() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);


        gradeModelArrayList = new ArrayList<>();

        String[] gradesList = getResources().getStringArray(R.array.gradeTable);

        Bundle bundle = getIntent().getExtras();
        final int gradesNumber = bundle.getInt("marksNumber");

        GradeModel.setCount(0);
        for (int i = 0; i < gradesNumber; i++)
        {
            GradeModel grade = new GradeModel(gradesList[i]);
            gradeModelArrayList.add(grade);
        }
        // tworzenie Recycler View
        InteractiveArrayAdapter interactiveArrayAdapter = new InteractiveArrayAdapter(gradeModelArrayList, this);
        recyclerView = findViewById(R.id.gradesList);
        recyclerView.setAdapter(interactiveArrayAdapter);
        //finalne utworzenie layoutu
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        button = findViewById(R.id.countButton);

        button.setOnClickListener(v -> {
            Bundle bundle2 = new Bundle();
            bundle2.putDouble("Mean", calculateMean());
            Intent intent = new Intent();
            intent.putExtras(bundle2);
            setResult(RESULT_OK, intent);
            finish();
        });
        //zapis wpisanych danych do savedInstanceState
        if (savedInstanceState != null)
        {
            int[] savedGrades = savedInstanceState.getIntArray("gradesValue");
            for (int i = 0; i < savedGrades.length; i++)
            {
                gradeModelArrayList.get(i).setValue(savedGrades[i]);
            }
        }


    }
    //wczytanie zapisanych danych z savedInstanceState
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState)
    {
        int[] allGrades = new int[gradeModelArrayList.size()];

        for (int i = 0; i < allGrades.length; i++)
        {
            allGrades[i] = gradeModelArrayList.get(i).getValue();

        }
        outState.putIntArray("gradesValue", allGrades);
        super.onSaveInstanceState(outState);
    }
    //kalkulator średniej
    public double calculateMean()
    {
        double sum = 0;
        for (GradeModel element : gradeModelArrayList)
        {
            int grade = element.getValue();
            sum += grade;
        }
        return sum/(double) gradeModelArrayList.size();
    }
}