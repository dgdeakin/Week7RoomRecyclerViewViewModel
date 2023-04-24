package com.application.week7complete.recyclerview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.application.week7complete.R;
import com.application.week7complete.activities.UpdateActivity;
import com.application.week7complete.data.Student;
import com.application.week7complete.listener.AdapterListener;
import com.application.week7complete.model.StudentViewModel;

import java.io.Serializable;



public class StudentListAdapter extends ListAdapter<Student, StudentListAdapter.MyViewHolder > {


    Context context;
    StudentViewModel studentViewModel;
    public StudentListAdapter(@NonNull DiffUtil.ItemCallback<Student> diffCallback, Context context, StudentViewModel studentViewModel) {
        super(diffCallback);
        this.context = context;
        this.studentViewModel = studentViewModel;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_view_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Student current = getItem(position);
        holder.name.setText(current.getName());
        holder.id.setText(String.valueOf(current.getId()));
        holder.description.setText(current.getDescription());

        holder.updateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);

                intent.putExtra("model", current);
                ((Activity) context).startActivityForResult(intent,2);
            }
        });

        holder.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentViewModel.delete(current);
            }
        });
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView id, name, description;
        ImageView updateImage, deleteImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewName);
            id = itemView.findViewById(R.id.textViewID);
            description = itemView.findViewById(R.id.textViewDescription);
            updateImage = itemView.findViewById(R.id.updateImage);
            deleteImage = itemView.findViewById(R.id.deleteImage);

        }
    }


    public static class StudentDiff extends DiffUtil.ItemCallback<Student>{

        @Override
        public boolean areItemsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }
}
