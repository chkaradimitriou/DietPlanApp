package com.ckaradimitriou.dietplanapp.ui.dashboard;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ckaradimitriou.dietplanapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;
import java.util.Objects;

public class DashboardViewModel extends ViewModel {

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    public MutableLiveData<User> user = new MutableLiveData<>();

    void getDashboardInformation() {
        getUsername();
    }

    private void getUsername() {
        String userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();
        firestore.collection("users")
                .whereEqualTo("userId", userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> data = document.getData();
                                String userId = data.get("userId").toString();
                                String email = data.get("email").toString();
                                String username = data.get("username").toString();
                                String userImg = data.get("userImg").toString();
                                User response = new User(userId, email, username, userImg);
                                user.postValue(response);
                            }
                        } else {
                            user.postValue(null);
                        }
                    }
                });
    }
}
