package com.example.mark5;

import androidx.annotation.NonNull;

public interface SyncDBCallBacks {
    public void onUserProfileFetched(User u);
    public void onUserUpdateSuccess();
    public void onUserUpdateFail(@NonNull Exception e);
}
