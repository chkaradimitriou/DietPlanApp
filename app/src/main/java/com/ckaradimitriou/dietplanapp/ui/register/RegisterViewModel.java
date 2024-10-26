package com.ckaradimitriou.dietplanapp.ui.register;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegisterViewModel extends ViewModel {

    private final FirebaseAuth auth = FirebaseAuth.getInstance();
    private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    MutableLiveData<Boolean> userHasBeenCreated = new MutableLiveData<>();

    void registerNewUserWithCredentials(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            createUserInformationInDb(email);
                        } else {
                            userHasBeenCreated.postValue(false);
                        }
                    }
                });
    }

    private void createUserInformationInDb(String email) {
        String username = email.substring(0, email.lastIndexOf("@"));
        String userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();

        Map<String, String> user = new HashMap<>();
        user.put("userId", userId);
        user.put("email", email);
        user.put("username", username);
        user.put("userImg", "");

        firestore.collection("users")
                .document(userId)
                .set(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            userHasBeenCreated.postValue(true);
                        } else {
                            userHasBeenCreated.postValue(false);
                        }
                    }
                });
    }
}