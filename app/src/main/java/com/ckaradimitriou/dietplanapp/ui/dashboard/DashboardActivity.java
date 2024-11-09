package com.ckaradimitriou.dietplanapp.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.ckaradimitriou.dietplanapp.R;
import com.ckaradimitriou.dietplanapp.databinding.ActivityDashboardBinding;
import com.ckaradimitriou.dietplanapp.ui.profile.ProfileActivity;

public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding binding;
    private DashboardViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        binding.userImgView.setOnClickListener(view -> {
            Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        viewModel.user.observe(this, user -> {
            if (user != null) {
                binding.setUser(user);
            } else {
                Toast.makeText(
                        this,
                        (getString(R.string.user_error_message)),
                        Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.recipes.observe(this, recipes -> {
            //recyclerview, adapter & click listener gia na ta deikseis
            // https://m.youtube.com/watch?v=DlaSiftrWeA
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getDashboardInformation();
    }
}