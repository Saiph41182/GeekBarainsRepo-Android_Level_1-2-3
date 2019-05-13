package com.example.weatherproject.settings.subSettings;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

import com.example.weatherproject.settings.SettingsActivity;
import com.example.weatherproject.settings.controllers.FragmentController;

import java.util.List;
import java.util.Objects;

import static com.example.weatherproject.settings.subSettings.SettingsParams.SHARED_NAME;

public class BaseFragment extends Fragment {
    protected static final String TAG = "BaseFragment";

    private static final FragmentController fragmentController = new FragmentController();
    private String name;
    protected List items;

    public BaseFragment(){
        name = this.getClass().getSimpleName();
    }

    public void showFragment(BaseFragment initiatedFrag, int conteinerResId,String name){
        Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction()
                .replace(conteinerResId,initiatedFrag,name)
                .commit();
        fragmentController.add(initiatedFrag);
    }

    public String getName() {
        return name;
    }

    public void setItems(List items) {
        this.items = items;
    }

    public static FragmentController getFragmentController() {
        return fragmentController;
    }


    public boolean equals(BaseFragment fragment) {
        if (this == fragment) return true;
        if (fragment == null) return false;
        if (!super.equals(fragment)) return false;
        return Objects.equals(getName(),  fragment.getName()) &&
                Objects.equals(items, fragment.items);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        SettingsParams.loadDataFromPreferences(
                context.getSharedPreferences(SettingsActivity.APP_PREFERENCES,Context.MODE_PRIVATE)
                        .getString(SHARED_NAME,null));
    }
    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences =
                getActivity().getSharedPreferences(SettingsActivity.APP_PREFERENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(SHARED_NAME,SettingsParams.getJsonSettingsParams()).apply();
    }
    /*@Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentController.popFragment();
    }*/

   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: in the" + getName());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: in the" + getName());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: in the" + getName());
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: in the" + getName());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: in the" + getName());
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: in the" + getName());
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: in the" + getName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: in the" + getName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: in the" + getName());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: in the" + getName());
    }*/
}
