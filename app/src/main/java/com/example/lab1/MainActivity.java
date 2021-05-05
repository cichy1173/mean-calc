package com.example.lab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.ReferenceQueue;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    protected boolean istextEditIsCool1, isTextEditIsCool2, isTextEditIsCool3;
    protected Handler handler = new Handler();
    protected Runnable runnable;
    protected double meanNumber;
    protected Button marksButton;
    protected EditText nameEditText;
    protected EditText surnameEditText;
    protected EditText numberOfMarksEditText;
    protected TextView meanText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        marksButton = findViewById(R.id.marksButton);
        nameEditText = findViewById(R.id.NameEditText);
        surnameEditText = findViewById(R.id.surnameEditText);
        numberOfMarksEditText = findViewById(R.id.NumberOfMarksEditTextNumber);
        meanText = findViewById(R.id.meanTextView);



        istextEditIsCool1 = false;
        isTextEditIsCool2 = false;
        isTextEditIsCool3 = false;

       nameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View v, boolean hasFocus) {
               if (!nameEditText.hasFocus())
               {
                   if (nameEditText.getText().toString().matches(""))
                   {
                       nameEditText.setError(getString(R.string.error));
                       istextEditIsCool1 = false;
                   }
                   else istextEditIsCool1 = true;
               }
           }
       });







       surnameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View v, boolean hasFocus) {
               if (!surnameEditText.hasFocus())
               {
                   if (surnameEditText.getText().toString().matches(""))
                   {
                       surnameEditText.setError(getString(R.string.error));

                       isTextEditIsCool2 = false;
                   }
                   else isTextEditIsCool2 = true;

               }
           }
       });




       numberOfMarksEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View v, boolean hasFocus) {
               if (!numberOfMarksEditText.hasFocus())
               {
                   if (numberOfMarksEditText.getText().toString().matches(""))
                   {
                       numberOfMarksEditText.setError(getString(R.string.error));

                       isTextEditIsCool3 = false;
                   }


                   else if (!(Integer.parseInt(numberOfMarksEditText.getText().toString()) <= 15 &&
                           Integer.parseInt(numberOfMarksEditText.getText().toString()) >=5))
                   {
                       numberOfMarksEditText.setError(getString(R.string.error2));

                       isTextEditIsCool3 = false;
                   }
                   else  isTextEditIsCool3 = true;


               }
           }
       });



        marksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MarksActivity.class);
                intent.putExtra("marksNumber", Integer.parseInt(numberOfMarksEditText.
                        getText().toString()));
                startActivityForResult(intent, 2);

            }
        });




       numberOfMarksEditText.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {


               {
                   if (numberOfMarksEditText.getText().toString().matches(""))
                   {
                       numberOfMarksEditText.setError(getString(R.string.error));

                       isTextEditIsCool3 = false;
                   }


                   else if (!(Integer.parseInt(numberOfMarksEditText.getText().toString()) <= 15 &&
                           Integer.parseInt(numberOfMarksEditText.getText().toString()) >=5))
                   {
                       numberOfMarksEditText.setError(getString(R.string.error2));

                       isTextEditIsCool3 = false;
                   }
                   else isTextEditIsCool3 = true;


               }
           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });

       nameEditText.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

               if (nameEditText.getText().toString().matches(""))
               {
                   nameEditText.setError(getString(R.string.error));
                   istextEditIsCool1 = false;
               }
               else istextEditIsCool1 = true;
           }

           @Override
           public void afterTextChanged(Editable s) {

               marksButton.setVisibility(View.GONE);
           }
       });

       surnameEditText.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

               if (surnameEditText.getText().toString().matches(""))
               {
                   surnameEditText.setError(getString(R.string.error));
                   marksButton.setVisibility(View.GONE);
                   isTextEditIsCool2 = false;
               }
               else  isTextEditIsCool2 = true;
           }

           @Override
           public void afterTextChanged(Editable s) {}
       });

       handler.postDelayed(runnable = new Runnable() {
           @Override
           public void run() {
               handler.postDelayed(runnable, 100);
               if ((istextEditIsCool1 && isTextEditIsCool2 && isTextEditIsCool3))
               {
                   marksButton.setVisibility(View.VISIBLE);
               }
               else marksButton.setVisibility(View.GONE);
           }
       }, 100);





    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState)
    {
        outState.putString("name_edit", nameEditText.getText().toString());
        outState.putString("surname_edit", surnameEditText.getText().toString());
        outState.putString("number_of_marks", numberOfMarksEditText.getText().toString());
        outState.putString("srednia", meanText.getText().toString());

        outState.putString("name_edit_enabled", String.valueOf(nameEditText.isEnabled()));
        outState.putString("surname_edit_enabled", String.valueOf(surnameEditText.isEnabled()));
        outState.putString("number_of_marks_enabled", String.valueOf(numberOfMarksEditText.isEnabled()));

        outState.putString("button_text", marksButton.getText().toString());

        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);

        nameEditText.setText(savedInstanceState.getString("name_edit"));
        surnameEditText.setText(savedInstanceState.getString("surname_edit"));
        numberOfMarksEditText.setText((savedInstanceState.getString("number_of_marks")));
        meanText.setText(savedInstanceState.getString("srednia"));

        nameEditText.setEnabled(Boolean.parseBoolean(savedInstanceState.getString("name_edit_enabled")));
        surnameEditText.setEnabled(Boolean.parseBoolean(savedInstanceState.getString("surname_edit_enabled")));
        numberOfMarksEditText.setEnabled(Boolean.parseBoolean(savedInstanceState.getString("number_of_marks_enabled")));
        marksButton.setText(savedInstanceState.getString("button_text"));

        if (savedInstanceState.getString("button_text").equals(getString(R.string.success)))
        {
            marksButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Toast.makeText(MainActivity.this, getString(R.string.toastSucces), Toast.LENGTH_LONG).show();
                        finish();
                }
            });
        }

        else if (savedInstanceState.getString("button_text").equals(getString(R.string.failed)))
        {
            marksButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, getString(R.string.toastFailed), Toast.LENGTH_LONG).show();
                    finish();
                }
            });
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
             Bundle bundle2 = data.getExtras();
             meanNumber = bundle2.getDouble("Mean");
             @SuppressLint("DefaultLocale") String outputInfo = getString(R.string.srednia) + " " + String.format("%.2f", meanNumber);
             meanText.setText(outputInfo);
        }

        if (meanNumber < 3)
        {
            marksButton.setText(getString(R.string.failed));
        }
        else
        {
            marksButton.setText(getString(R.string.success));
        }

        nameEditText.setEnabled(false);
        surnameEditText.setEnabled(false);
        numberOfMarksEditText.setEnabled(false);

        marksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (marksButton.getText().equals(getString(R.string.failed)))
                {
                    Toast.makeText(MainActivity.this, getString(R.string.toastFailed), Toast.LENGTH_LONG).show();
                    finish();
                }
                else if (marksButton.getText().equals(getString(R.string.success)))
                {
                    Toast.makeText(MainActivity.this, getString(R.string.toastSucces), Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }





    protected void onRestart()
    {
        super.onRestart();
    }
    @Override
    protected void onStart()
    {
        super.onStart();
    }
    @Override
    protected void onResume()
    {
        super.onResume();
    }
    @Override
    protected void onPause()
    {
        super.onPause();
    }
    @Override
    protected void onStop()
    {
        super.onStop();
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }







}