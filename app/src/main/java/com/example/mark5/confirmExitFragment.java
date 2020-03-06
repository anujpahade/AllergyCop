package com.example.mark5;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import timber.log.Timber;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;

import static java.lang.String.valueOf;


/**
 * A simple {@link Fragment} subclass.
 */
public class confirmExitFragment extends DialogFragment {

    FirebaseAuth mAuth =  FirebaseAuth.getInstance();;
    private  String title = "title", positiveButtonText = "yes",negativeButtonText = "no";

    private FragmentManager fm;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        Timber.d("Checkpoint3 onCreateDialog of confirmExitFragment");
        super.onCreate(savedInstanceState);
        title = getArguments().getString("title");
        positiveButtonText = getArguments().getString("positiveButtonText");
        negativeButtonText = getArguments().getString("negativeButtonText");
        fm = getActivity().getSupportFragmentManager();
        Timber.d("Checkpoint4 postive button"+positiveButtonText);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(title)
                .setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Timber.d("Fragment Signout clicked Qwerty");
                        mAuth.signOut();
                        Intent intent = new Intent(getContext(), SignInActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        getActivity().finish();
                    }
                })
                .setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.cancel();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }


    public confirmExitFragment() {
        // Required empty public constructor
    }

    public static confirmExitFragment newInstance(String title, String postiveButtonText,String negativeButtonText) {
        confirmExitFragment dialog = new confirmExitFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        args.putString("positiveButtonText",postiveButtonText);
        args.putString("negativeButtonText",negativeButtonText);
        dialog.setArguments(args);
        return dialog;
    }

    /*Checkpoint 3*/
    @Override
    public void onPause() {
        super.onPause();
        Timber.d("Checkpoint3 confirmExitFragment onPause");
    }
    @Override
    public void onResume() {
        super.onResume();
        Timber.d("Checkpoint3 confirmExitFragment onResume");
    }
    @Override
    public void onStop() {
        super.onStop();
        Timber.d("Checkpoint3 confirmExitFragment onStop");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.d("Checkpoint3 confirmExitFragment onDestroy");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Timber.d("Checkpoint3 Oncreateview of confirmExitFragment");

        return inflater.inflate(R.layout.fragment_confirm_exit, container, false);
    }
}
