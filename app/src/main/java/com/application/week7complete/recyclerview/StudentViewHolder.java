package com.application.week7complete.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.week7complete.R;

public class StudentViewHolder extends RecyclerView.ViewHolder {

    private TextView id, name, description;

    public StudentViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.textViewName);
        id = itemView.findViewById(R.id.textViewID);
        description = itemView.findViewById(R.id.textViewDescription);
    }

    public void bind(int idValue, String nameValue, String descriptionValue){
        name.setText(nameValue);
        id.setText(String.valueOf(idValue));
        description.setText(descriptionValue);
    }


    public static StudentViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        return new StudentViewHolder(view);
    }




}
