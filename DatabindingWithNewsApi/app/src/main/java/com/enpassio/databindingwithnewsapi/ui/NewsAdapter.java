package com.enpassio.databindingwithnewsapi.ui;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.enpassio.databindingwithnewsapi.R;
import com.enpassio.databindingwithnewsapi.databinding.ItemBinding;
import com.enpassio.databindingwithnewsapi.model.Article;

import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<Article> mArticleList;
    private final ArticleClickListener mListener;

    NewsAdapter(ArticleClickListener listener) {
        mListener = listener;
    }

    void setArticleList(List<Article> articleList) {
        mArticleList = articleList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_news_article,
                        parent, false);
        //Pass an item click listener to each item layout.
        binding.setArticleItemClick(mListener);
        return new NewsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        //For each item, corresponding product object is passed to the binding
        holder.binding.setArticle(mArticleList.get(position));
        //This is to force bindings to execute right away
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        //If list is null, return 0, otherwise return the size of the list
        return mArticleList == null ? 0 : mArticleList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        final ItemBinding binding;

        NewsViewHolder(ItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface ArticleClickListener {
        void onArticleClicked(Article chosenArticle);
    }
}
