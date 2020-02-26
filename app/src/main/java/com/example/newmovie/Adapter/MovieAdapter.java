package com.example.newmovie.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newmovie.DetailActivity;
import com.example.newmovie.Model.Movie;
import com.example.newmovie.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private Context mContext;
    private List<Movie> movieList;

    public MovieAdapter(Context mContext, List<Movie> movieList){
        this.mContext = mContext;
        this.movieList = movieList;
    }

    public MovieAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_card, viewGroup, false);

        return new MyViewHolder(view);
    }

    public void onBindViewHolder(final MovieAdapter.MyViewHolder viewHolder, int i){
        viewHolder.title.setText(movieList.get(i).getOriginalTitle());
        String vote = Double.toString(movieList.get(i).getVoteAverage());
        viewHolder.rating.setText(vote);

        String poster = "https://image.tmdb.org/t/p/w500" + movieList.get(i).getPosterPath();

        Glide.with(mContext)
                .load(poster)
                .placeholder(R.drawable.load)
                .into(viewHolder.imagethum);

    }

    @Override
    public int getItemCount(){
        return movieList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title, rating;
        public ImageView imagethum;

        public MyViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            rating = (TextView) view.findViewById(R.id.rating);
            imagethum = (ImageView) view.findViewById(R.id.imagethum);

            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        Movie clickedDataItem = movieList.get(pos);
                        Intent intent = new Intent(mContext, DetailActivity.class);
                        intent.putExtra("movies", clickedDataItem );
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getOriginalTitle(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
