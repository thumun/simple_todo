package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// responsible for displaying data from the model into a row in the recycler view
public class ItemsAdaptor extends RecyclerView.Adapter<ItemsAdaptor.Viewholder>{

    public interface OnLongClickListener{
        void onItemLongClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;

    public ItemsAdaptor(List<String> items, OnLongClickListener longClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // use layour inflator to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

        // wrap it inside viewholder & return
        return new Viewholder(todoView);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        // grab item at posiiton
        String item = items.get(position);
        // bind item onto specific view holder
        holder.bind(item);
    }

    // tells RV how many items in list
    @Override
    public int getItemCount() {
        return items.size();
    }

    // container to provide easy access to view that represent each row of list
    class Viewholder extends RecyclerView.ViewHolder{

        TextView tvItem;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        // update view inside of viewholder w/ data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v){
                    // notify listener which position was long clicked
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
