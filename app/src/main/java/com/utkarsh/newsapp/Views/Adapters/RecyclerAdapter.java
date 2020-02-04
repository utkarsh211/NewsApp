package com.utkarsh.newsapp.Views.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.utkarsh.newsapp.Models.Article;
import com.utkarsh.newsapp.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    private List<Article> articlesList;
    private Context context;
    private OnCardClickListener onCardClickListener;

    public RecyclerAdapter(Context context, List<Article> articleList, OnCardClickListener onCardClickListener ) {
        this.articlesList = articleList;
        this.context = context;
        this.onCardClickListener = onCardClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = Objects.requireNonNull(layoutInflater).inflate(R.layout.news_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(v,onCardClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article article = articlesList.get(position);
        holder.titleText.setText(article.getTitle());
        holder.authorText.setText(article.getAuthor());
        DateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy");
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        String convertedDate = null;
        try {
            convertedDate = outputFormat.format(inputFormat.parse(article.getPublishedAt().substring(0,10))) + "\n" + " " + article.getPublishedAt().substring(11,19);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.timeText.setText(convertedDate);
        holder.descriptionText.setText(article.getDescription());
        Glide.with(context)
                .load(article.getUrlToImage())
                .into(holder.newsImage);
    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnCardClickListener onCardClickListener;
        TextView titleText;
        TextView authorText;
        TextView timeText;
        TextView descriptionText;
        ImageView newsImage;
         ViewHolder(@NonNull View itemView, OnCardClickListener onCardClickListener) {
            super(itemView);
            titleText = itemView.findViewById(R.id.title_text);
            authorText = itemView.findViewById(R.id.author_text);
            timeText = itemView.findViewById(R.id.time_text);
            descriptionText = itemView.findViewById(R.id.desc_text);
            newsImage = itemView.findViewById(R.id.news_image);
            this.onCardClickListener = onCardClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onCardClickListener.onCardClick(getAdapterPosition());
        }
    }
    public interface OnCardClickListener {
        void onCardClick(int position);
    }

}
