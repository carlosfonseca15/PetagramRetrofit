package com.pruebas123.petagram.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pruebas123.petagram.R;
import com.pruebas123.petagram.db.ConstructorMascotas;
import com.pruebas123.petagram.pojo.Mascota;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> implements View.OnClickListener{

    ArrayList<Mascota> mascotas;
    Activity activity;
    private View.OnClickListener listener;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);

        v.setOnClickListener(this);
        return new MascotaViewHolder(v);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull MascotaViewHolder mascotaViewHolder, int position) {
        Mascota mascota = mascotas.get(position);
        //mascotaViewHolder.imgFoto.setImageResource(mascota.getImagen());
        Picasso.with(activity)
                .load(mascota.getUrlFoto())
                .placeholder(R.drawable.max)
                .into(mascotaViewHolder.imgFoto);
        mascotaViewHolder.tvNombreCV.setText(mascota.getNombre());
        mascotaViewHolder.tvCalificacionCV.setText(String.valueOf(mascota.getCalificacion()));
        mascotaViewHolder.ibCalificacionCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cal = mascota.getCalificacion() + 1;
                mascota.setCalificacion(cal);
                ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
                constructorMascotas.actualizarCalificacion(mascota);

                Toast.makeText(activity, "Diste a " + mascota.getNombre() + "C= " + mascota.getCalificacion(), Toast.LENGTH_LONG).show();
                notifyDataSetChanged();  //hace actulizar el valor
            }
        });
    }

    @Override
    public int getItemCount() {  //cant de elem de la lista
        return mascotas.size();
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFoto;
        private ImageButton ibCalificacionCV;
        private TextView tvNombreCV;
        private TextView tvCalificacionCV;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            ibCalificacionCV = (ImageButton) itemView.findViewById(R.id.ibCalificacionCV);
            tvNombreCV = (TextView) itemView.findViewById(R.id.tvNombreCV);
            tvCalificacionCV = (TextView) itemView.findViewById(R.id.tvCalificacionCV);

        }
    }
}
