package co.edu.udea.compumovil.gr08_20171.Lab2.Activity.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import co.edu.udea.compumovil.gr08_20171.Lab2.Activity.Models.DTO.Event;
import co.edu.udea.compumovil.gr08_20171.Lab2.Activity.adapters.AdapterEvents;
import co.edu.udea.compumovil.gr08_20171.Lab2.Activity.adapters.EventViewHolder;
import co.edu.udea.compumovil.gr08_20171.Lab2.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentEventDetail.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentEventDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentEventDetail extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_POSITION = "position";
    int mCurrentPosition = -1;




    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentEventDetail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentEventDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentEventDetail newInstance(String param1, String param2) {
        FragmentEventDetail fragment = new FragmentEventDetail();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }
        return inflater.inflate(R.layout.fragment_event_detail, container, false);
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
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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

    public void updateDetailEventInformation(int position){

        Event event = AdapterEvents.getEvent(position);

        ImageView detailEventPhoto = (ImageView) getActivity().findViewById(R.id.detail_event_photo);
        TextView detailEventName = (TextView) getActivity().findViewById(R.id.detail_event_name);
        RatingBar detailEventRating = (RatingBar) getActivity().findViewById(R.id.detail_event_rating);
        TextView detailEventDescription = (TextView) getActivity().findViewById(R.id.detail_event_description);

        detailEventName.setText(event.getName());
        detailEventDescription.setText(event.getDescription());
        detailEventRating.setRating(event.getScore());
        //TODO: Organizar la foto en la tarjeta.
        detailEventPhoto.setImageResource(R.drawable.hockey);
        //detailEventPhoto.setImageResource(Integer.getInteger(event.getPhoto()));
        mCurrentPosition = position;
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateDetailEventInformation(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // Set article based on saved instance state defined during onCreateView
            updateDetailEventInformation(mCurrentPosition);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }
}
