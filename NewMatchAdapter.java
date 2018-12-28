package com.mobileblog.user.mobileblogapp.cricket;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobileblog.user.mobileblogapp.R;

import java.util.ArrayList;


public class NewMatchAdapter extends RecyclerView.Adapter<NewMatchAdapter.NewViewHolder>{

    private ArrayList<NewMatch> matches = new ArrayList<>();

    public NewMatchAdapter(ArrayList<NewMatch> matches) {
        this.matches = matches;
    }

    @NonNull
    @Override
    public NewMatchAdapter.NewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_matches_row_layout,parent,false);

        NewViewHolder newViewHolder = new NewViewHolder(v);

        return newViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewMatchAdapter.NewViewHolder holder, int position) {

        holder.txtMatchName.setText(matches.get(position).getName());
        holder.txtMatchDate.setText(matches.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public static  class NewViewHolder extends RecyclerView.ViewHolder {

        private TextView txtMatchName,txtMatchDate;

        public NewViewHolder(View v) {
            super(v);

            txtMatchName = v.findViewById(R.id.name);
            txtMatchDate = v.findViewById(R.id.date);
        }

    }
}
