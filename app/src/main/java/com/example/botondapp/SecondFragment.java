package com.example.botondapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.botondapp.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    // A második háromszög melletti befogója fixen 700
    private static final float ADJACENT2 = 700f;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonCalculate.setOnClickListener(v -> {
            // Bekérés és validálás
            String sD1 = binding.editDiameter1.getText().toString().trim();
            String sD2 = binding.editDiameter2.getText().toString().trim();
            String sAdj1 = binding.editAdjacent1.getText().toString().trim();

            if (TextUtils.isEmpty(sD1) || TextUtils.isEmpty(sD2) || TextUtils.isEmpty(sAdj1)) {
                Toast.makeText(getContext(), "Kérlek, tölts ki minden mezőt!", Toast.LENGTH_SHORT).show();
                return;
            }

            float D1, D2, adj1;
            try {
                D1 = Float.parseFloat(sD1);
                D2 = Float.parseFloat(sD2);
                adj1 = Float.parseFloat(sAdj1);
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Érvénytelen számformátum!", Toast.LENGTH_SHORT).show();
                return;
            }

            // 1) Delta D számítása = szemközti befogó az első háromszögben
            float deltaD = Math.abs(D1 - D2);

            // 2) Alfa szög számítása (radianban és fokban)
            if (adj1 == 0f) {
                Toast.makeText(getContext(), "A melletti befogó nem lehet nulla!", Toast.LENGTH_LONG).show();
                return;
            }
            double alphaRad = Math.atan(deltaD / adj1);
            double alphaDeg = Math.toDegrees(alphaRad);

            // 3) Második háromszög szemközti befogója
            double opp2 = Math.tan(alphaRad) * ADJACENT2;

            // 4) Kiírás – lépések
            StringBuilder steps = new StringBuilder();
            steps.append("1) Átmérők: D1 = ").append(D1)
                    .append(", D2 = ").append(D2).append("\n");
            steps.append("2) ΔD = |D1 - D2| = ").append(deltaD).append("\n");
            steps.append("3) Első háromszög melletti befogó: ").append(adj1).append("\n");
            steps.append(String.format("4) α = arctan(ΔD / adj1) = arctan(" +deltaD + " / " + adj1+") = " +alphaDeg + "°\n"
                    ));
            steps.append("5) Második háromszög melletti befogó = 700\n");
            steps.append(String.format("6) Második háromszög szemközti befogó: opp2 = 700·tan(α) = " + opp2));

            binding.textSteps.setText(steps.toString());

            // Végső eredmény kiírása
            if(D1 < D2 ){
                binding.textResult.setText(String.format("Második háromszög szemközti befogója:\n -" + opp2));

            }
            else{
                binding.textResult.setText(String.format("Második háromszög szemközti befogója:\n " + opp2));

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
