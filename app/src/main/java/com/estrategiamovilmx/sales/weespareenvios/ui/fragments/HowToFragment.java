package com.estrategiamovilmx.sales.weespareenvios.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.estrategiamovilmx.sales.weespareenvios.R;
import com.estrategiamovilmx.sales.weespareenvios.tools.GeneralFunctions;

/**
 * A simple {@link Fragment} subclass.
 */
public class HowToFragment extends Fragment {
    private TextView content;

    public HowToFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_how_to, container, false);
        content = (TextView) v.findViewById(R.id.content);
        content.setText(GeneralFunctions.fromHtml(getString(R.string.promt_how_to)));
        return v;
    }


}
