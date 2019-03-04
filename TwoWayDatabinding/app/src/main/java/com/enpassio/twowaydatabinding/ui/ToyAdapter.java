package com.enpassio.twowaydatabinding.ui;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.enpassio.twowaydatabinding.R;
import com.enpassio.twowaydatabinding.data.ToyEntry;
import com.enpassio.twowaydatabinding.databinding.ItemToyBinding;

import java.util.List;

public class ToyAdapter extends RecyclerView.Adapter<ToyAdapter.ToyViewHolder>{

    private List<ToyEntry> mToyList;
    private final ToyClickListener mListener;

    ToyAdapter(ToyClickListener listener) {
        mListener = listener;
    }

    void setToyList(List<ToyEntry> articleList) {
        mToyList = articleList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ToyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemToyBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_toy,
                        parent, false);
        //Pass an item click listener to each item layout.
        binding.setToyItemClick(mListener);
        return new ToyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ToyViewHolder holder, int position) {
        //For each item, corresponding product object is passed to the binding
        holder.binding.setToy(mToyList.get(position));
        //This is to force bindings to execute right away
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        //If list is null, return 0, otherwise return the size of the list
        return mToyList == null ? 0 : mToyList.size();
    }

    class ToyViewHolder extends RecyclerView.ViewHolder{

        final ItemToyBinding binding;

        ToyViewHolder(@NonNull ItemToyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface ToyClickListener {
        void onToyClicked(int toyID);
    }
}
