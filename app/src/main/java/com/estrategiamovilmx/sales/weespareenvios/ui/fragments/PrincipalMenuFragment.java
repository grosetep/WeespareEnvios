package com.estrategiamovilmx.sales.weespareenvios.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estrategiamovilmx.sales.weespareenvios.R;
import com.estrategiamovilmx.sales.weespareenvios.tools.ApplicationPreferences;
import com.estrategiamovilmx.sales.weespareenvios.tools.Constants;
import com.estrategiamovilmx.sales.weespareenvios.ui.activities.LoginActivity;
import com.estrategiamovilmx.sales.weespareenvios.ui.activities.MainActivity;
import com.estrategiamovilmx.sales.weespareenvios.ui.activities.NewOrderActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrincipalMenuFragment extends Fragment {
    private AppCompatButton button_new_order;

    public PrincipalMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_principal_menu, container, false);
        ViewPager pager = v.findViewById(R.id.photos_viewpager);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ShippingServicesFragment());
        //fragments.add(new ShippingServicesFragment());
        pager.setAdapter(new PrincipalSlidePagerAdapter(getFragmentManager(), fragments));

        TabLayout tabLayout = v.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pager, true);

        init(v);
        return v;
    }
    static class PrincipalSlidePagerAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> mFragments = null;

        public PrincipalSlidePagerAdapter(FragmentManager fm,List<Fragment> fragments) {
            super(fm);
            mFragments = fragments;
        }

        public void addFragment(Fragment fragment) {
            mFragments.add(fragment);
            notifyDataSetChanged();
        }

        public void removeFragment(int index) {
            mFragments.remove(index);
            notifyDataSetChanged();
        }
        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        public Fragment getFragment(int key){
            return mFragments.get(key);
        }
        @Override
        public int getCount() {
            return mFragments.size();
        }

    }
    private void init(View v){
        button_new_order = v.findViewById(R.id.button_new_order);
        button_new_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String json_user = ApplicationPreferences.getLocalStringPreference(getContext(), Constants.user_object);
                Log.d("PrincipalMenuFragment", "json_object:" + json_user);
                if (json_user==null || json_user.isEmpty()){
                    Intent i = new Intent(getContext(),LoginActivity.class);
                    i.putExtra(Constants.flow, MainActivity.flow_no_registered);
                    startActivity(i);
                }else {
                    Intent i = new Intent(getContext(), NewOrderActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}
