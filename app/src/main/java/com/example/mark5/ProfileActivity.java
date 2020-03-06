package com.example.mark5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import timber.log.Timber;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;

public class ProfileActivity<mLayout> extends AppCompatActivity {
    private TextView profileEmail;
    private Button signOut;
    private LinearLayout allergen_list;
    private EditText allergen;

    private boolean isFragmentDisplayed = false;
    public FragmentManager fragmentManager = getSupportFragmentManager();

    User user;
    DBOperationManager dbo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("Checkpoint3 ProfileActivity onCreate");
        setContentView(R.layout.activity_profile);

        dbo= new DBOperationManager(){
            public void onUserProfileFetched(User u)
            {
                setUserProfile(u);
            }
            public void onUserUpdateSuccess()
            {
                Toast.makeText(ProfileActivity.this, "List Saved!!", Toast.LENGTH_SHORT).show();
                Log.d("FireBASE", "DocumentSnapshot successfully written!");
            }
            public void onUserUpdateFail(@NonNull Exception e)
            {
                Log.d("FIREbase", "Error writing document", e);
            }
        };
        dbo.getProfile( );
        profileEmail = findViewById(R.id.profile_email);
        signOut=findViewById(R.id.sign_out);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //shows confirm signout dialog
                confirmSave(getCurrentList());
            }
        });
    }

    /*Checkpoint 3*/
    @Override
    public void onPause() {
        super.onPause();
        Timber.d("Checkpoint3 ProfileActivity onPause");
    }
    @Override
    public void onResume() {
        super.onResume();
        Timber.d("Checkpoint3 ProfileActivity onResume");
    }
    @Override
    public void onStop() {
        super.onStop();
        Timber.d("Checkpoint3 ProfileActivity onStop");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.d("Checkpoint3 ProfileActivity onDestroy");
    }

    private void setUserProfile(User user) {
        this.user=user;
        if(user!=null)
        {
            profileEmail.setText(user.getEmail());
            for (String allergen : user.getAllergens())
            {
                final LinearLayout one_allergen = new LinearLayout(this);
                one_allergen.setOrientation(LinearLayout.HORIZONTAL);
                final TextView tv = new TextView(this);
                tv.setText(allergen);
                final Button delete_one_allergen = new Button(this);

                Resources res = getResources();
                Drawable drawable = res.getDrawable(R.drawable.delete_one_allergen, getTheme());

                delete_one_allergen.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
                delete_one_allergen.setBackgroundColor(Color.TRANSPARENT);
                delete_one_allergen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        allergen_list.removeView(one_allergen);
                    }
                });

                Log.d("Pop LIST",allergen);
                one_allergen.addView(tv);
                one_allergen.addView(delete_one_allergen);
                allergen_list = findViewById(R.id.allergen_list); //linear layout
                allergen_list.addView(one_allergen);
            }
        }


    }



    public void AddAllergen(View view) {
        allergen_list = findViewById(R.id.allergen_list); //linear layout
        final LinearLayout one_allergen = new LinearLayout(this);
        one_allergen.setOrientation(LinearLayout.HORIZONTAL);
        allergen = findViewById(R.id.allergen); //edittext
        final TextView tv = new TextView(this);
        tv.setText(allergen.getText());
        one_allergen.addView(tv);

        final Button delete_one_allergen = new Button(this);
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.delete_one_allergen, getTheme());

        delete_one_allergen.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
        delete_one_allergen.setBackgroundColor(Color.TRANSPARENT);
        delete_one_allergen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allergen_list.removeView(one_allergen);
            }
        });
        one_allergen.addView(delete_one_allergen);

        allergen_list.addView(one_allergen);
        allergen.getText().clear();

    }

    public void SaveList(View view) {
        /*
         * Read from the list
         * */
        user.setAllergens(getCurrentList());
        dbo.updateProfile(user);

    }

    /*getcurrentlist*/
    public ArrayList<String> getCurrentList(){
        LinearLayout allergen_list = findViewById(R.id.allergen_list);
        int count = allergen_list.getChildCount();
        Timber.d("Checkpoint4: Inside SaveList:Count"+count);

        TextView allergen_view;
        LinearLayout one_allergen;
        ArrayList<String> firebase_allergens_list = new ArrayList<String>();

        for (int i = 0; i < count; i++) {
            Timber.d("Checkpoint4: Inside SaveList: i value: "+i);
            one_allergen = (LinearLayout) allergen_list.getChildAt(i);
            Timber.d("Checkpoint4: Inside SaveList:2");

            allergen_view =(TextView) one_allergen.getChildAt(0);
            Timber.d("Checkpoint4: Inside SaveList: 3");

            firebase_allergens_list.add( allergen_view.getText().toString());
            Timber.d("Checkpoint4: Inside SaveList: 4");

        }

        return firebase_allergens_list;
    }

    /*Checkpoint4 Save Check*/
    public void confirmSave(ArrayList<String> currentList){
        /*
         * Check with the inital list
         * */
        if(!currentList.equals(user.getAllergens())){
            displayFragment("Exit without saving changes?","Yes","No");
        }else{
            displayFragment("Confirm Signout?","Yes","No");
        }
    }
    public void displayFragment(String title, String positiveButtontext, String negativeButtontext) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Timber.d(" Checkpoint Adding "+title+" to transaction");
        confirmExitFragment dialogFragment = confirmExitFragment.newInstance(title,positiveButtontext,negativeButtontext);
        dialogFragment.show(fragmentTransaction,"Qwerty");
    }

}