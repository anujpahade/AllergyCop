package com.example.mark5;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;

public class DBOperationManager implements SyncDBCallBacks {

    FirebaseFirestore db = FirebaseFirestore.getInstance();



    boolean flag=true;

    void getProfile()
    {

        final String user_email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        DocumentReference docRef = db.collection("users").document(user_email);


        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot  document = task.getResult();
                    if (document.exists()) {
                        User current_user= new User( user_email, document.getData().get("allergies"));
                        onUserProfileFetched(current_user);
                        Log.d("FETCH ALLERGENS", "DocumentSnapshot data: ");
                    } else {
                        Log.d("EMPTY DOC", "No such document");
                    }
                } else {
                    Log.d("READ HAGGA", "get failed with ", task.getException());
                }

            }});



    }




    void updateProfile(User user)
    {
        HashMap<String, List<String>> user_allergies = new HashMap<String, List<String>>();
        user_allergies.put("allergies",user.getAllergens());
        CollectionReference clf = db.collection("users");

        String user_email = user.getEmail();
        Log.e("INSIDE SAVE LIST","USEREMAIL AA GAYA "+user);

        clf.document(user_email)
                .set(user_allergies)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        onUserUpdateSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        onUserUpdateFail( e);
                    }
                });


    }

    public void onUserProfileFetched(User u)
    {

    }

    public void onUserUpdateSuccess()
    {

    }
    public void onUserUpdateFail(@NonNull Exception e)
    {

    }

}
