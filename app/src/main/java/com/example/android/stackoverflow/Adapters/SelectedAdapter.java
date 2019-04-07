package com.example.android.stackoverflow.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.stackoverflow.Models.Tags;
import com.example.android.stackoverflow.R;

import java.util.ArrayList;
import java.util.List;

public class SelectedAdapter extends RecyclerView.Adapter<SelectedAdapter.ViewHolder> {

    ArrayList<String> selectedTagList;
    Context context;

    public SelectedAdapter(ArrayList<String> tagList, Context context){
        this.selectedTagList=tagList;
        this.context=context;
    }

    @NonNull
    @Override
    public SelectedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tag_item,viewGroup,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    public void removeItem(int position){
        selectedTagList.remove(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tagTextView.setText(selectedTagList.get(i));
    }

    @Override
    public int getItemCount() {
        return selectedTagList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tagTextView;
        ImageView removeButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tagTextView = itemView.findViewById(R.id.selected_tag_text_view);
            removeButton = itemView.findViewById(R.id.remove_button);
        }

        public void onClick(View view){
            removeItem(getAdapterPosition());
        }
    }
}
