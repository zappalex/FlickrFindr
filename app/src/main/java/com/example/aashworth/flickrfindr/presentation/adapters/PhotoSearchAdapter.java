package com.example.aashworth.flickrfindr.presentation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.aashworth.flickrfindr.R;
import com.example.aashworth.flickrfindr.data.models.Photo;
import com.example.aashworth.flickrfindr.network.ImageDownloader;

import java.util.List;

public class PhotoSearchAdapter extends RecyclerView.Adapter<PhotoSearchAdapter.PhotoViewHolder> {
    private List<Photo> photoList;
    private final PhotoSearchAdapterOnClickHandler photoClickHandler;
    private static final String TAG = "PhotoSearchAdapter";

    public interface PhotoSearchAdapterOnClickHandler {
        void onClick(Photo photo);
    }

    public PhotoSearchAdapter(PhotoSearchAdapterOnClickHandler clickHandler) {
        photoClickHandler = clickHandler;
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final ImageView photoImageView;

        public PhotoViewHolder(View view) {
            super(view);
            photoImageView = view.findViewById(R.id.photo_imgview);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Photo clickedPhoto = getPhotoForPosition(adapterPosition);
            photoClickHandler.onClick(clickedPhoto);
        }
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.photo_list_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder photoViewHolder, int i) {
        Photo currentPhoto = getPhotoForPosition(i);
        ImageDownloader.INSTANCE.downloadMediumImage(currentPhoto, photoViewHolder.photoImageView);
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
        if (photoList != null) {
            this.photoList = photoList;
            notifyDataSetChanged();
        } else {
            Log.e(TAG, "Attempting to set photoList, but found null");
        }
    }

    private Photo getPhotoForPosition(int position) {
        if (photoList != null) {
            Photo photoForPosition = photoList.get(position);
            if (photoForPosition != null) {
                return photoForPosition;
            }
        }
        Log.e(TAG, "Trying to use null photo, substituting default");
        return getDefaultPhoto();
    }

    private Photo getDefaultPhoto() {
        return new Photo(
                "33654303778",
                "Image Not Found",
                "2ae54d65d6",
                "7819",
                "8"
        );
    }
}
