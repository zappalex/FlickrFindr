package com.example.aashworth.flickrfindr.presentation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aashworth.flickrfindr.R;
import com.example.aashworth.flickrfindr.data.models.Photo;

import java.util.List;

public class PhotoSearchAdapter extends RecyclerView.Adapter<PhotoSearchAdapter.PhotoViewHolder> {

    private List<Photo> photoList;
    private final PhotoSearchAdapterOnClickHandler photoClickHandler;

    public interface PhotoSearchAdapterOnClickHandler {
        void onClick(Photo photo);
    }

    public PhotoSearchAdapter(PhotoSearchAdapterOnClickHandler clickHandler) {
        photoClickHandler = clickHandler;
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //TODO: add member values here
        public PhotoViewHolder(View view) {
            super(view);
            // TODO: declare member variable
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            // TODO: Check null
            Photo clickedPhoto = photoList.get(adapterPosition);
            photoClickHandler.onClick(clickedPhoto);
        }
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.photo_list_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder photoViewHolder, int i) {
        // TODO: check null
        Photo currentPhoto = photoList.get(i);
        String currentPhotoImgPath = currentPhoto.getFullPhotoUrl();

        // Use Picasso Here
    }

    @Override
    public int getItemCount() {
        if (photoList == null) {
            return 0;
        } else {
            return photoList.size();
        }
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
        notifyDataSetChanged();
    }
}
