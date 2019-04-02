package com.example.aashworth.flickrfindr.presentation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aashworth.flickrfindr.R;
import com.example.aashworth.flickrfindr.data.models.Photo;
import com.squareup.picasso.Picasso;

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
        public final ImageView photoImageView;
        public final TextView photoTitleTextView;

        public PhotoViewHolder(View view) {
            super(view);
            photoImageView = view.findViewById(R.id.photo_imgview);
            photoTitleTextView = view.findViewById(R.id.photo_title_textview);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            int adapterPosition = getAdapterPosition();
//            // TODO: Check null
//            Photo clickedPhoto = photoList.get(adapterPosition);
//            photoClickHandler.onClick(clickedPhoto);
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
        photoViewHolder.photoTitleTextView.setText(currentPhoto.getTitle());

        String currentPhotoImgPath = currentPhoto.getFullPhotoUrl();

        Picasso.get()
                .load(currentPhotoImgPath)
                .fit()
                .centerCrop()
                .into(photoViewHolder.photoImageView);
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
        // TODO: check null
        this.photoList = photoList;
        notifyDataSetChanged();
    }
}
