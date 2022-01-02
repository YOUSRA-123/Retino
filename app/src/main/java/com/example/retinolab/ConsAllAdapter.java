package com.example.retinolab;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ConsAllAdapter extends RecyclerView.Adapter<ConsAllAdapter.RequestViewAdapter> {

    private List<DemandeConsNew> listeCons;
    private Context context;

    public ConsAllAdapter(Context context, List<DemandeConsNew> requests){
        this.listeCons = requests;
        this.context = context;
    }

    public class RequestViewAdapter extends RecyclerView.ViewHolder{

        TextView consultation, nom, date, phone,email;
        public RequestViewAdapter(final View view) {
            super(view);
            consultation = view.findViewById(R.id.cons);
            nom = view.findViewById(R.id.nom);
            date = view.findViewById(R.id.date);
            email = view.findViewById(R.id.emaiil);
            phone = view.findViewById(R.id.phoone);

        }
    }

    @NonNull
    @Override
    public ConsAllAdapter.RequestViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.activity_one_item, parent, false);
        return new RequestViewAdapter(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsAllAdapter.RequestViewAdapter holder, int position) {
        DemandeConsNew request = listeCons.get(position);

        holder.nom.setText((request.getName()));
        holder.date.setText((request.getDate()));
        holder.phone.setText((request.getPhone()));
        holder.email.setText((request.getEmail()));


    }

    @Override
    public int getItemCount() {
        return listeCons.size();
    }


}