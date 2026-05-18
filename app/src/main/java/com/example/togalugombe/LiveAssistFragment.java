package com.example.togalugombe;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class LiveAssistFragment extends Fragment {

    private RecyclerView recyclerScenes;
    private SceneAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live_assist, container, false);

        Spinner spinnerPlays = view.findViewById(R.id.spinner_plays);
        recyclerScenes = view.findViewById(R.id.recycler_scenes);
        recyclerScenes.setLayoutManager(new LinearLayoutManager(getContext()));

        String[] plays = {"Ramayana", "Mahabharata", "Harishchandra"};
        
        // Simple adapter to make text white for Dark Theme
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, plays) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTextColor(Color.WHITE);
                return v;
            }
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                ((TextView) v).setTextColor(Color.BLACK); // Dropdown text
                return v;
            }
        };
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPlays.setAdapter(spinnerAdapter);

        spinnerPlays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadScenes(plays[position]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        return view;
    }

    private void loadScenes(String playName) {
        List<Scene> scenes = new ArrayList<>();
        if (playName.equals("Ramayana")) {
            scenes.add(new Scene("Scene 1: Rama's Birth", "King Dasharatha performs a grand yagna and Rama is born in Ayodhya."));
            scenes.add(new Scene("Scene 2: Sita's Swayamvara", "Rama lifts the divine bow and wins Sita's hand in marriage."));
            scenes.add(new Scene("Scene 3: Exile Begins", "Kaikeyi's wish sends Rama, Sita and Lakshmana to the forest."));
            scenes.add(new Scene("Scene 4: Ravana's Trap", "The golden deer lures Sita away and Ravana abducts her."));
            scenes.add(new Scene("Scene 5: Lanka War", "Rama builds a bridge and defeats Ravana to rescue Sita."));
        } else if (playName.equals("Mahabharata")) {
            scenes.add(new Scene("Scene 1: The Dice Game", "Yudhishthira loses everything in the rigged game of dice."));
            scenes.add(new Scene("Scene 2: Exile", "The Pandavas spend 13 years in the forest."));
            scenes.add(new Scene("Scene 3: Kurukshetra Begins", "Arjuna is hesitant, Krishna delivers the Bhagavad Gita."));
            scenes.add(new Scene("Scene 4: The Final Day", "Bhima defeats Duryodhana, ending the great war."));
        } else {
            scenes.add(new Scene("Scene 1: The Dream", "King Harishchandra dreams he gives away his kingdom to Sage Vishwamitra."));
            scenes.add(new Scene("Scene 2: The Promise", "He fulfills the dream and leaves his kingdom with his family."));
            scenes.add(new Scene("Scene 3: Hardships", "He works at a cremation ground to pay off debts."));
            scenes.add(new Scene("Scene 4: Truth Prevails", "The Gods appear, reward his truthfulness, and restore his kingdom."));
        }

        adapter = new SceneAdapter(scenes);
        recyclerScenes.setAdapter(adapter);
    }

    // --- Inner Classes for simplicity ---
    static class Scene {
        String title, desc;
        Scene(String title, String desc) { this.title = title; this.desc = desc; }
    }

    static class SceneAdapter extends RecyclerView.Adapter<SceneAdapter.ViewHolder> {
        List<Scene> list;
        SceneAdapter(List<Scene> list) { this.list = list; }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scene, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Scene s = list.get(position);
            holder.tvTitle.setText(s.title);
            holder.tvDesc.setText(s.desc);
        }

        @Override
        public int getItemCount() { return list.size(); }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvTitle, tvDesc;
            ViewHolder(View v) {
                super(v);
                tvTitle = v.findViewById(R.id.tv_scene_title);
                tvDesc = v.findViewById(R.id.tv_scene_desc);
            }
        }
    }
}
