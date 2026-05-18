package com.example.togalugombe;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        RecyclerView recyclerGallery = view.findViewById(R.id.recycler_gallery);
        recyclerGallery.setLayoutManager(new GridLayoutManager(getContext(), 2));

        List<Puppet> puppets = new ArrayList<>();
        puppets.add(new Puppet("Rama", "Hero", "The ideal king and seventh avatar of Vishnu.", 
                "Lord Rama is depicted with blue skin (represented by dark intricate patterns). He represents Righteousness, Dharma, and the ideal human standard."));
        puppets.add(new Puppet("Sita", "Heroine", "Symbol of purity, devotion and strength.", 
                "Sita's puppet is elegantly adorned. She symbolizes unyielding devotion, resilience in the face of suffering, and natural purity."));
        puppets.add(new Puppet("Ravana", "Villain", "The ten-headed demon king of Lanka.", 
                "Ravana is the largest puppet with ten heads and twenty arms. He symbolizes massive intellect ruined by immense ego and materialism."));
        puppets.add(new Puppet("Hanuman", "God", "The devoted monkey god with divine strength.", 
                "Hanuman's puppet is dynamic and athletic. He represents absolute selfless service, courage, and unwavering devotion to the divine."));
        puppets.add(new Puppet("Lakshmana", "Hero", "Rama's loyal younger brother and protector.", 
                "Lakshmana is portrayed as alert and armed. He symbolizes unwavering loyalty, sacrifice, and duty towards family."));
        puppets.add(new Puppet("Dasharatha", "King", "The noble king of Ayodhya and father of Rama.", 
                "Depicted as a grand, older royal figure. He symbolizes the tragedy of worldly attachment and the bind of unthinking promises."));

        PuppetAdapter adapter = new PuppetAdapter(puppets, puppet -> {
            Intent intent = new Intent(getContext(), PuppetDetailActivity.class);
            intent.putExtra("name", puppet.name);
            intent.putExtra("role", puppet.role);
            intent.putExtra("desc", puppet.fullDesc);
            intent.putExtra("symbolism", puppet.symbolism);
            startActivity(intent);
        });
        
        recyclerGallery.setAdapter(adapter);
        return view;
    }

    static class Puppet {
        String name, role, shortDesc, fullDesc, symbolism;
        Puppet(String n, String r, String sd, String sym) { 
            name = n; role = r; shortDesc = sd; symbolism = sym; fullDesc = sd; 
        }
    }

    interface OnPuppetClickListener { void onClick(Puppet p); }

    static class PuppetAdapter extends RecyclerView.Adapter<PuppetAdapter.ViewHolder> {
        List<Puppet> list;
        OnPuppetClickListener listener;

        PuppetAdapter(List<Puppet> list, OnPuppetClickListener listener) { 
            this.list = list; this.listener = listener; 
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_puppet, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Puppet p = list.get(position);
            holder.tvName.setText(p.name);
            holder.tvRole.setText(p.role);
            holder.tvDesc.setText(p.shortDesc);
            holder.itemView.setOnClickListener(v -> listener.onClick(p));
        }

        @Override
        public int getItemCount() { return list.size(); }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvName, tvRole, tvDesc;
            ViewHolder(View v) {
                super(v);
                tvName = v.findViewById(R.id.tv_puppet_name);
                tvRole = v.findViewById(R.id.tv_puppet_role);
                tvDesc = v.findViewById(R.id.tv_puppet_desc);
            }
        }
    }
}
