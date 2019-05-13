package com.example.weatherproject.settings.controllers;

import android.util.Log;

import com.example.weatherproject.settings.subSettings.BaseFragment;

import java.util.ArrayList;

public class FragmentController {
    private static final String TAG = "FragmentController";
    private static int counter = 0;
    private ArrayList<Entry> entrys;

    public FragmentController() {
        entrys = new ArrayList<>();
    }

    public void add(BaseFragment fragment) {
        if (!containsFragment(fragment)) {
            entrys.add(new Entry(fragment));
            counter++;
        }
        Log.d(TAG, "add: " + showInfo());
    }

    private String showInfo() {
        String size = " Size is " + entrys.size() + "\n";
        String fragmentInStack = " In stack: ";
        for (Entry e : entrys) {
            fragmentInStack = fragmentInStack.concat(e.getFragment().getName() + " \n");
        }
        return size + fragmentInStack;
    }

    public void add(BaseFragment fragment, int resId) {
        if (!(containsFragment(fragment) && containsId(resId))) {
            Entry entry = new Entry(fragment, resId);
            entrys.add(entry);
            counter++;
        }
    }

    public void popFragment() {
        if (!isEmpty()) {
            entrys.remove(--counter);
        }
        Log.d(TAG, "popFragment: " + showInfo());
    }

    public BaseFragment getLast() {
        int lastPosition = counter;
        BaseFragment fragment = !isEmpty() ? entrys.get(lastPosition - 1).getFragment() : null;
        Log.d(TAG, "getLast: " + showInfo());
        return fragment;
    }

    public boolean isEmpty() {
        return counter == 0;
    }

    public int getSize(){
        return entrys.size();
    }

    private boolean containsFragment(BaseFragment fragment) {
        boolean isContain = false;
        if (counter > 0) {
            for (Entry entry : entrys) {
                isContain = entry.getFragment().equals(fragment);
            }
        }
        return isContain;
    }

    private boolean containsId(int id) {
        boolean isContain = false;
        if (counter > 0) {
            for (Entry entry : entrys) {
                isContain = entry.getResId() == id;
            }
        }
        return isContain;
    }


    static class Entry {
        private BaseFragment fragment;
        private int resId;

        public Entry(BaseFragment fragment, int resId) {
            this.fragment = fragment;
            this.resId = resId;
        }

        public Entry(BaseFragment fragment) {
            this.fragment = fragment;
        }

        public void setResId(int resId) {
            this.resId = resId;
        }

        public BaseFragment getFragment() {
            return fragment;
        }

        public int getResId() {
            return resId;
        }
    }
}
