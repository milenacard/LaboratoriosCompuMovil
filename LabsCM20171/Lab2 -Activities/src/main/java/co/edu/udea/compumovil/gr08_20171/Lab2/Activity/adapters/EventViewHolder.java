package co.edu.udea.compumovil.gr08_20171.Lab2.Activity.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import co.edu.udea.compumovil.gr08_20171.Lab2.R;

/**
 * Created by CristianCamilo on 13/03/2017.
 */

public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    static OnEventSelectedListener mCallback;

    public CardView cardView;
    public ImageView eventPhoto;
    public TextView eventName;
    public RatingBar eventRating;
    public TextView eventDescription;


    EventViewHolder(View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);
        cardView = (CardView) itemView.findViewById(R.id.card_view);
        eventPhoto = (ImageView) itemView.findViewById(R.id.event_photo);
        eventName = (TextView) itemView.findViewById(R.id.event_name);
        eventRating = (RatingBar) itemView.findViewById(R.id.event_rating);
        eventDescription = (TextView) itemView.findViewById(R.id.event_description);
    }

    @Override
    public void onClick(View cardView) {

        mCallback.onEventSelected(getAdapterPosition());
    }

    // The container Activity must implement this interface so the frag can deliver messages
    public interface OnEventSelectedListener {
        /** Called by FragmentEvents when a list item is selected */
        public void onEventSelected(int position);
    }
    public static void setCallback(OnEventSelectedListener callback){
        mCallback = callback;
    }

}
