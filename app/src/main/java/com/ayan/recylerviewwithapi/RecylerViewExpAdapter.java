package com.ayan.recylerviewwithapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecylerViewExpAdapter extends RecyclerView.Adapter<RecylerViewExpAdapter.ViewHolder> {
    private List<ObjectModel> mObjectList;

    public RecylerViewExpAdapter(List<ObjectModel> objList) {
        this.mObjectList = objList;
    }

    @NonNull
    @Override
    public RecylerViewExpAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecylerViewExpAdapter.ViewHolder holder, int position) {
        ObjectModel obj = mObjectList.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(obj.getLogin());
        Button button = holder.messageButton;
        button.setText(obj.getSiteAdmin() ? "SiteAdmin" : "Not SiteAdmin");
        button.setEnabled(obj.getSiteAdmin());
    }

    @Override
    public int getItemCount() {
        return mObjectList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public Button messageButton;


        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.contact_name);
            messageButton = (Button) itemView.findViewById(R.id.message_button);
        }
    }
}
