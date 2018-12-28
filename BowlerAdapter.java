package com.mobileblog.user.mobileblogapp.cricket;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mobileblog.user.mobileblogapp.R;
import java.util.ArrayList;


public class BowlerAdapter extends RecyclerView.Adapter<BowlerAdapter.BowlerHolder>{

    private ArrayList<Bowler> arrayList = new ArrayList<>();

    public BowlerAdapter(ArrayList<Bowler> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public BowlerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bowling_row,parent,false);

        BowlerHolder holder = new BowlerHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BowlerHolder holder, int position) {

        holder.txtBowlerName.setText(arrayList.get(position).getBowlerName());
        holder.txtRuns.setText(arrayList.get(position).getRuns());
        holder.txtDotBall.setText(arrayList.get(position).getDots());
        holder.txtEcon.setText(arrayList.get(position).getEconomy());
        holder.txtMaiden.setText(arrayList.get(position).getMedain());
        holder.txtWicket.setText(arrayList.get(position).getWickets());
        holder.txtOver.setText(arrayList.get(position).getOver());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class BowlerHolder extends RecyclerView.ViewHolder{

        protected TextView txtBowlerName,txtWicket,txtMaiden,txtRuns,txtOver,txtDotBall,txtEcon;

        public BowlerHolder(View v) {
            super(v);

            txtBowlerName = v.findViewById(R.id.tv_bowler);
            txtWicket = v.findViewById(R.id.tv_wickets);
            txtMaiden = v.findViewById(R.id.tv_maiden);
            txtRuns = v.findViewById(R.id.tv_runs);
            txtOver = v.findViewById(R.id.tv_overs);
            txtDotBall = v.findViewById(R.id.tv_dot);
            txtEcon = v.findViewById(R.id.tv_econ);
        }
    }

}
