package com.example.lab1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
//plik ten związany jest z działaniem RecyclewView i drugiej aktywności
public class InteractiveArrayAdapter extends RecyclerView.Adapter<InteractiveArrayAdapter.GradesViewHolder>
{

    private List<GradeModel> gradesActivityList;
    private LayoutInflater layoutInflater;
    //tworzenie LayoutInflater
    public InteractiveArrayAdapter(List<GradeModel> gradesActivityList, Activity activity)
    {
        this.gradesActivityList = gradesActivityList;
        this.layoutInflater = activity.getLayoutInflater();
    }

    @NonNull
    @Override
    public InteractiveArrayAdapter.GradesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        //tworzenie layoutu na bazie XMLki
        @SuppressLint("InflateParams") View line = layoutInflater.inflate(R.layout.list_line, null);
        //zwrocenie nowego obiektu
        return new GradesViewHolder(line);
    }
    //wypełnienie danymi
    @Override
    public void onBindViewHolder(@NonNull InteractiveArrayAdapter.GradesViewHolder holder, int position)
    {
        //tworzenie obiektu GradeModel
        GradeModel grade = gradesActivityList.get(position);
        //połączenie przycisków radio z grade
        holder.radioGroup.setTag(grade);
        //połączenie z nazwą przedmiotu
        holder.textView.setText(grade.getName());
        //wybieranie przycisku radio
        switch (gradesActivityList.get(position).getValue())
        {
            case 2:
                holder.radioGroup.check(R.id.twoButton);
                break;
            case 3:
                holder.radioGroup.check(R.id.threeButton);
                break;
            case 4:
                holder.radioGroup.check(R.id.fourButton);
                break;
            case 5:
                holder.radioGroup.check(R.id.fiveButton);
                break;
        }
    }
    //zwrócenie liczby elementów na liście
    @Override
    public int getItemCount() {
        return gradesActivityList.size();
    }

    public class GradesViewHolder extends
            RecyclerView.ViewHolder implements RadioGroup.OnCheckedChangeListener
    {
       private TextView textView;
       private RadioGroup radioGroup;
       private RadioButton radioButton;

       public GradesViewHolder(@NonNull View itemView)
       {
           super(itemView);
            // odczytanie odniesień do elementów wiersza
           this.textView = itemView.findViewById(R.id.gradeName);
           this.radioGroup = itemView.findViewById(R.id.radioGroup);

           radioGroup.setOnCheckedChangeListener(((group, checkedId) -> {
               // odczytanie danych z grupy przycisków radio
               GradeModel element = (GradeModel) group.getTag();
                // zapisanie zmienionej oceny
               radioButton = itemView.findViewById(group.getCheckedRadioButtonId());
               element.setValue(Integer.parseInt(radioButton.getText().toString()));
               gradesActivityList.set(element.getId(), element);
           }));
       }

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

        }
    }
}
