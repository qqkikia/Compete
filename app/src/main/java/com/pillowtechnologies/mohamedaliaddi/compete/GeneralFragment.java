package com.pillowtechnologies.mohamedaliaddi.compete;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class GeneralFragment extends Fragment {

    public GeneralFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_general, container, false);
    }

    public static GeneralFragment newInstance() {

        GeneralFragment g = new GeneralFragment();




        return g;
    }

    public void toPlanned(View view){
        Intent intent = new Intent(getActivity(),PlannedActivity.class);
        startActivity(intent);
    }

    public void toLadder(View view){
        Intent intent = new Intent(getActivity(),LadderActivity.class);
        startActivity(intent);
    }

    public void toCurrent(View view){
        Intent intent = new Intent(getActivity(),CurrentActivity.class);
        startActivity(intent);
    }
}
