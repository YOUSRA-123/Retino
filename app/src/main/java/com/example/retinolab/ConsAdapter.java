package com.example.retinolab;


import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.content.Context;
        import android.widget.TextView;
        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;
        import java.util.List;

public class ConsAdapter extends RecyclerView.Adapter<ConsAdapter.RequestViewAdapter> {

    private List<DemandeConsNew> listeCons;
    private Context context;

    public ConsAdapter(Context context, List<DemandeConsNew> requests){
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
    public ConsAdapter.RequestViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.activity_one_item, parent, false);
        return new RequestViewAdapter(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsAdapter.RequestViewAdapter holder, int position) {
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