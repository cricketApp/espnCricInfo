package com.mobileblog.user.mobileblogapp.cricket;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mobileblog.user.mobileblogapp.R;

import java.util.ArrayList;

import me.grantland.widget.AutofitTextView;

class CricAdapter extends RecyclerView.Adapter<CricAdapter.ViewHolder> {
        ArrayList<String> list1;
        ArrayList<String> list2;
        ArrayList<String> list3; //receive the String array list to display
        ArrayList<String> list4; //receive the String array list to display
        ArrayList<String> list5; //receive the String array list to display
        private Context context;

    public CricAdapter(ArrayList<String> templist,
                       ArrayList<String> templist2,
                       ArrayList<String> templist3,
                       ArrayList<String> templist4,
                       ArrayList<String> templist5,
                       Context ctx){
        list1 = templist;
        list2 = templist2;
        list3 = templist3;
        list4 = templist4;
        list5 = templist5;
        context = ctx;
    }


    @Override
    public CricAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cric_row_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.text1.setText(list1.get(position));
        holder.text2.setText(list2.get(position));
        holder.text3.setText(list3.get(position));
        holder.text3.setTextColor(Color.parseColor("#000000"));
        holder.text4.setText(list4.get(position));

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,ScoresActivity.class);
                intent.putExtra("match_id",list5.get(position));
                intent.putExtra("team1",list1.get(position));
                intent.putExtra("team2",list2.get(position));
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public AutofitTextView text1,text2;
        public TextView text3,text4;
        public View v;

        public ViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            text1=itemView.findViewById(R.id.text);
            text2=itemView.findViewById(R.id.text2);
            text3=itemView.findViewById(R.id.text3);
            text4=itemView.findViewById(R.id.text4);
        }
    }
}
