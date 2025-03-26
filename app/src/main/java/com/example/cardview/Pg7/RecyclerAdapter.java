package com.example.cardview.Pg7;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardview.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<RecyclerData> recyclerDataList;
    public Context context;
    public RecyclerAdapter(Context context,List<RecyclerData> recyclerDataList){
        this.context=context;
        this.recyclerDataList=recyclerDataList;
    }
    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.folder_recycler_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        RecyclerData recyclerData=recyclerDataList.get(position);
        holder.textView.setText(recyclerData.getFname());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, Folder.class);
            intent.putExtra("folderName", recyclerData.getFname());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return recyclerDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text);
        }
    }
}
