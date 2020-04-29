package edu.quinnipiac.pildora;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class HelpFragment extends Fragment {
    private static View _layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_help, container, false);
        _layout = layout;//save as static variable to be used around the class
        return _layout;
    }

}
