package co.edu.udea.compumovil.gr08_20171.Lab2.Activity.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.compumovil.gr08_20171.Lab2.Activity.Models.DAO.DbHelper;
import co.edu.udea.compumovil.gr08_20171.Lab2.Activity.Models.DTO.Event;
import co.edu.udea.compumovil.gr08_20171.Lab2.Activity.adapters.AdapterEvents;
import co.edu.udea.compumovil.gr08_20171.Lab2.Activity.adapters.EventViewHolder;
import co.edu.udea.compumovil.gr08_20171.Lab2.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentEvents.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentEvents#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentEvents extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerViewEvents;
    private List<Event> eventList;
    private DbHelper dbHelper;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentEvents() {
        // Required empty public constructor
        dbHelper = new DbHelper(getContext());
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentEvents.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentEvents newInstance(String param1, String param2) {
        FragmentEvents fragment = new FragmentEvents();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_events, container, false);
        //eventList = dbHelper.getAllEvents();
        eventList = this.dummiContentEvents();
        recyclerViewEvents = (RecyclerView) rootView.findViewById(R.id.recycler_view_events);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerViewEvents.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerViewEvents.setLayoutManager(linearLayoutManager);

        //specify an adapter
        AdapterEvents adapter = new AdapterEvents(eventList);
        recyclerViewEvents.setAdapter(adapter);

        // Inflate the layout for this fragment
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof EventViewHolder.OnEventSelectedListener) {
            EventViewHolder.setCallback((EventViewHolder.OnEventSelectedListener) context);
        } else {
//            throw new RuntimeException(context.toString()
  //                  + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private List<Event> dummiContentEvents(){
        List<Event> events = new ArrayList<>();

        events.add(new Event(1,String.valueOf(R.drawable.hockey),"Patinaje sobre Hielo","Este deporte es un deporte olimpico",1.0,"Responsable","26/05/2000",2000.0,300.0,"infromacion general del evento"));
        events.add(new Event(1,String.valueOf(R.drawable.australia_20122),"Formula 1","Este deporte es un deporte olimpico",4.0,"Responsable","26/05/2000",2000.0,300.0,"infromacion general del evento"));
        events.add(new Event(1,String.valueOf(R.drawable.balonmano),"Bolos","Este deporte nompico",3.0,"Responsable","26/05/2000",2000.0,300.0,"infromacion general del evento"));
        events.add(new Event(1,String.valueOf(R.drawable.campeonato_mundial_de_patinaje_artstico_junior_isu),"Patinaje sobre Hielo","Este deporte es un deporte olimpico",4.0,"Responsable","26/05/2000",2000.0,300.0,"infromacion general del evento"));
        events.add(new Event(1,String.valueOf(R.drawable.tirodeportivo),"Patinaje sobre Hielo","Este deporte es un deporte olimpico",4.0,"Responsable","26/05/2000",2000.0,300.0,"infromacion general del evento"));
        events.add(new Event(1,String.valueOf(R.drawable.watercolor_running_man_23_2147492712),"Patinaje sobre Hielo","Este deporte es un deporte olimpico",4.0,"Responsable","26/05/2000",2000.0,300.0,"infromacion general del evento"));
        events.add(new Event(1,String.valueOf(R.drawable.hockey),"Patinaje sobre Hielo","Este deporte es un deporte olimpico",4.0,"Responsable","26/05/2000",2000.0,300.0,"infromacion general del evento"));
        events.add(new Event(1,String.valueOf(R.drawable.hockey),"Patinaje sobre Hielo","Este deporte es un deporte olimpico",4.0,"Responsable","26/05/2000",2000.0,300.0,"infromacion general del evento"));
        events.add(new Event(1,String.valueOf(R.drawable.hockey),"Patinaje sobre Hielo","Este deporte es un deporte olimpico",4.0,"Responsable","26/05/2000",2000.0,300.0,"infromacion general del evento"));
        events.add(new Event(1,String.valueOf(R.drawable.hockey),"Patinaje sobre Hielo","Este deporte es un deporte olimpico",4.0,"Responsable","26/05/2000",2000.0,300.0,"infromacion general del evento"));
        events.add(new Event(1,String.valueOf(R.drawable.hockey),"Patinaje sobre Hielo","Este deporte es un deporte olimpico",4.0,"Responsable","26/05/2000",2000.0,300.0,"infromacion general del evento"));
        events.add(new Event(1,String.valueOf(R.drawable.hockey),"Patinaje sobre Hielo","Este deporte es un deporte olimpico",4.0,"Responsable","26/05/2000",2000.0,300.0,"infromacion general del evento"));
        events.add(new Event(1,String.valueOf(R.drawable.hockey),"Patinaje sobre Hielo","Este deporte es un deporte olimpico",4.0,"Responsable","26/05/2000",2000.0,300.0,"infromacion general del evento"));
        events.add(new Event(1,String.valueOf(R.drawable.hockey),"Patinaje sobre Hielo","Este deporte es un deporte olimpico",4.0,"Responsable","26/05/2000",2000.0,300.0,"infromacion general del evento"));
        events.add(new Event(1,String.valueOf(R.drawable.hockey),"Patinaje sobre Hielo","Este deporte es un deporte olimpico",4.0,"Responsable","26/05/2000",2000.0,300.0,"infromacion general del evento"));
        events.add(new Event(1,String.valueOf(R.drawable.hockey),"Patinaje sobre Hielo","Este deporte es un deporte olimpico",4.0,"Responsable","26/05/2000",2000.0,300.0,"infromacion general del evento"));
        events.add(new Event(1,String.valueOf(R.drawable.hockey),"Patinaje sobre Hielo","Este deporte es un deporte olimpico",4.0,"Responsable","26/05/2000",2000.0,300.0,"infromacion general del evento"));

        return events;
    }

}
