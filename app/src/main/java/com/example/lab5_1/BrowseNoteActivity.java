package com.example.lab5_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

/**
 * BrowseNoteActivity implementing Multitasking (Thread) for simulated search operations.
 */
public class BrowseNoteActivity extends AppCompatActivity {

    private TextInputEditText etSearchUser;
    private Button btnSearchUser;
    private LinearLayout llSearchLoadingContainer;
    private TextView tvSearchResultStatus;
    private RecyclerView rvSearchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_note);

        initViews();
        setupListeners();
    }

    private void initViews() {
        etSearchUser = findViewById(R.id.etSearchUser);
        btnSearchUser = findViewById(R.id.btnSearchUser);
        llSearchLoadingContainer = findViewById(R.id.llSearchLoadingContainer);
        tvSearchResultStatus = findViewById(R.id.tvSearchResultStatus);
        rvSearchResults = findViewById(R.id.rvSearchResults);
    }

    private void setupListeners() {
        btnSearchUser.setOnClickListener(v -> performMultitaskingSearch());
    }

    /**
     * Requirement 3: Multitasking search using Thread.
     * Displays ProgressBar, delays 2 seconds, and displays "ไม่พบข้อมูล".
     */
    private void performMultitaskingSearch() {
        // Hide result view and show ProgressBar
        llSearchLoadingContainer.setVisibility(View.VISIBLE);
        tvSearchResultStatus.setVisibility(View.GONE);
        rvSearchResults.setVisibility(View.GONE);
        btnSearchUser.setEnabled(false);

        // Multitasking via Background Thread
        new Thread(() -> {
            try {
                // Delay 2 seconds as specified in requirement
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Update UI on main thread
            runOnUiThread(() -> {
                llSearchLoadingContainer.setVisibility(View.GONE);
                btnSearchUser.setEnabled(true);
                tvSearchResultStatus.setVisibility(View.VISIBLE);
                tvSearchResultStatus.setText(getString(R.string.msg_not_found));
            });
        }).start();
    }
}
