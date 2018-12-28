package com.mobileblog.user.mobileblogapp.cricket;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobileblog.user.mobileblogapp.R;

import java.util.ArrayList;


public class BatsmanAdapter extends RecyclerView.Adapter<BatsmanAdapter.BatsmanHolder>{

    private ArrayList<Batsman> arrayList = new ArrayList<>();

    public BatsmanAdapter(ArrayList<Batsman> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public BatsmanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.batsman_row,parent,false);

        BatsmanHolder holder = new BatsmanHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BatsmanHolder holder, int position) {

        holder.txtOut.setText(arrayList.get(position).getDismisalInfo());
        holder.txtRuns.setText(arrayList.get(position).getRuns());
        holder.txtFours.setText(arrayList.get(position).getFours());
        holder.txtBatsName.setText(arrayList.get(position).getBatsmanName());
        holder.txtBalls.setText(arrayList.get(position).getBalls());
        holder.txtSixs.setText(arrayList.get(position).getSixs());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class BatsmanHolder extends RecyclerView.ViewHolder{

        protected TextView txtBatsName,txtFours,txtSixs,txtOut,txtBalls,txtRuns;

        public BatsmanHolder(View v) {
            super(v);

            txtBatsName = v.findViewById(R.id.tv_batsman);
            txtBalls = v.findViewById(R.id.tv_ball);
            txtFours = v.findViewById(R.id.tv_4s);
            txtSixs = v.findViewById(R.id.tv_6s);
            txtRuns = v.findViewById(R.id.tv_runs);
            txtOut = v.findViewById(R.id.tv_out);
        }
    }

}
