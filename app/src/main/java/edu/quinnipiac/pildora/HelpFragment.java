package edu.quinnipiac.pildora;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * HelpFragment
 * This fragment is a very simple screen that displays 4 instructions on how to use the app.
 * It just uses Text Views, nothing happens in the backend when the user interacts with it.
 * Once on this fragment, the user is only able to navigate backwards.
 * @Author: Katherine Rosell
 * @Date: 4/26/2020
 */
public class HelpFragment extends Fragment {
    private static View _layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_help, container, false);
        _layout = layout;//save as static variable to be used around the class
        return _layout;
    }

}
