package co.edu.udea.compumovil.gr08_20171.Lab2.Activity.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import co.edu.udea.compumovil.gr08_20171.Lab2.Activity.Models.DTO.Event;
import co.edu.udea.compumovil.gr08_20171.Lab2.R;

/**
 * Created by CristianCamilo on 13/03/2017.
 */

public class AdapterEvents extends RecyclerView.Adapter<EventViewHolder>{

    private static List<Event> events;

    public AdapterEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_event, parent, false);
        EventViewHolder eventViewHolder = new EventViewHolder(view);
        return eventViewHolder;
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {

        holder.eventName.setText(events.get(position).getName());
        holder.eventDescription.setText(events.get(position).getDescription());
        holder.eventRating.setRating(events.get(position).getScore());
        //TODO: Organizar la foto en la tarjeta.
       // holder.eventPhoto.setImageResource(R.drawable.balonmano);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static Event getEvent(int position) {
        return events.get(position);
    }

}
