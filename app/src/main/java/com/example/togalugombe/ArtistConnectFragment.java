package com.example.togalugombe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ArtistConnectFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artist_connect, container, false);

        setupCard(view.findViewById(R.id.ws1), "Mastering Leather Puppetry", "10 Oct 2026 | Mysuru", "Book Now", "Booking Confirmed!");
        setupCard(view.findViewById(R.id.ws2), "Shadow Play Basics", "15 Nov 2026 | Bengaluru", "Book Now", "Booking Confirmed!");

        setupCard(view.findViewById(R.id.store1), "Miniature Rama", "Rs 499", "Buy Now", "Added to Cart!");
        setupCard(view.findViewById(R.id.store2), "Miniature Hanuman", "Rs 599", "Buy Now", "Added to Cart!");
        setupCard(view.findViewById(R.id.store3), "Lanka Set", "Rs 999", "Buy Now", "Added to Cart!");

        setupCard(view.findViewById(R.id.hist1), "History of Togalu Gombeyaata", "A documentary on the ancient art.", "Play", "Playing video...");
        setupCard(view.findViewById(R.id.hist2), "Making of a Leather Puppet", "See how the skin is treated and painted.", "Play", "Playing video...");
        setupCard(view.findViewById(R.id.hist3), "Behind the Screen", "The lighting techniques used.", "Play", "Playing video...");

        return view;
    }

    private void setupCard(View cardView, String title, String subtitle, String btnText, String toastMsg) {
        TextView tvTitle = cardView.findViewById(R.id.card_title);
        TextView tvSubtitle = cardView.findViewById(R.id.card_subtitle);
        Button btn = cardView.findViewById(R.id.card_btn);

        tvTitle.setText(title);
        tvSubtitle.setText(subtitle);
        btn.setText(btnText);
        
        btn.setOnClickListener(v -> 
            Toast.makeText(getContext(), toastMsg, Toast.LENGTH_SHORT).show()
        );
    }
}
