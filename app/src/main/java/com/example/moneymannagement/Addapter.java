package com.example.moneymannagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymannagement.Modalclass.Modal;

import java.util.ArrayList;


public class Addapter extends RecyclerView.Adapter<Addapter.viewHolder> {

    ArrayList<Modal>list;
    Context context;

    public Addapter(ArrayList<Modal> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.samplehistory,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Modal post= list.get(position);
        holder.typetextview.setText(post.getType());
        holder.datetextview.setText(post.getTodaydate());
        holder.amounttextview.setText("₹"+post.getAmount());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

          TextView typetextview;
          TextView datetextview;
          TextView amounttextview;

          public viewHolder(@NonNull View itemView) {
              super(itemView);
              typetextview=itemView.findViewById(R.id.typetextview);
              datetextview=itemView.findViewById(R.id.datetextview);
              amounttextview=itemView.findViewById(R.id.amounttextview);

          }
      }
}
