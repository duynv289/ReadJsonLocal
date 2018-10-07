package com.example.liz.readjsonlocal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> mStudents;

    public StudentAdapter(List<Student> mStudents) {
        this.mStudents = mStudents;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_student, viewGroup, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int i) {
        holder.bindData(mStudents.get(i));
    }

    @Override
    public int getItemCount() {
        return mStudents != null ? mStudents.size() : 0;
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName, txtAge, txtCity;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtAge = itemView.findViewById(R.id.txtAge);
            txtCity = itemView.findViewById(R.id.txtCity);
        }

        private void bindData(Student student) {
            txtName.setText(student.getName());
            txtAge.setText(student.getAge()+"");
            txtCity.setText(student.getCity());
        }
    }
}
