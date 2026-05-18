package com.example.togalugombe;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PuppetDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puppet_detail);

        TextView tvName = findViewById(R.id.detail_name);
        TextView tvRole = findViewById(R.id.detail_role);
        TextView tvDesc = findViewById(R.id.detail_desc);
        TextView tvSymbolism = findViewById(R.id.detail_symbolism);

        tvName.setText(getIntent().getStringExtra("name"));
        tvRole.setText(getIntent().getStringExtra("role"));
        tvDesc.setText(getIntent().getStringExtra("desc"));
        tvSymbolism.setText(getIntent().getStringExtra("symbolism"));
    }
}
