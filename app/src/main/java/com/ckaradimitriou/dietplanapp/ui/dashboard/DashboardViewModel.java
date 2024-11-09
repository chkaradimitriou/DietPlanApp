package com.ckaradimitriou.dietplanapp.ui.dashboard;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ckaradimitriou.dietplanapp.data.model.Recipe;
import com.ckaradimitriou.dietplanapp.data.model.RecipeItem;
import com.ckaradimitriou.dietplanapp.data.model.RecipeResponse;
import com.ckaradimitriou.dietplanapp.data.network.ApiClient;
import com.ckaradimitriou.dietplanapp.model.User;
import com.ckaradimitriou.dietplanapp.util.Consts;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardViewModel extends ViewModel {

    private final ApiClient api = new ApiClient();
    private final FirebaseAuth auth = FirebaseAuth.getInstance();
    private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    public MutableLiveData<List<Recipe>> recipes = new MutableLiveData<>();
    public MutableLiveData<User> user = new MutableLiveData<>();

    void getDashboardInformation() {
        getUsername();
        getRecipesByMealType();
    }

    private void getRecipesByMealType() {
        int timeOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        String mealType;
        if (timeOfDay >= 7 && timeOfDay <= 11) {
            mealType = "Breakfast";
        } else if (timeOfDay > 11 && timeOfDay <= 16) {
            mealType = "Lunch";
        } else {
            mealType = "Dinner";
        }

        getResultsFromRemoteApiByMealType(mealType);
    }

    private void getResultsFromRemoteApiByMealType(String mealType) {
        api.getService().searchForRecipesByMealType(
                Consts.TYPE,
                Consts.APP_ID,
                Consts.API_KEY,
                mealType
        ).enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Recipe> dataFromServer = response.body().getHits()
                            .stream().map(RecipeItem::getRecipe)
                            .collect(Collectors.toList());

                    recipes.postValue(dataFromServer);
                } else {
                    recipes.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable throwable) {
                recipes.postValue(null);
            }
        });
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
