package com.example.retinolab;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class AideAdapter extends RecyclerView.Adapter<AideAdapter.RequestViewAdapter> {

    private List<AideNew> listeAide;
    private Context context;

    public AideAdapter(Context context, List<AideNew> requests){
        this.listeAide = requests;
        this.context = context;
    }

    public class RequestViewAdapter extends RecyclerView.ViewHolder{

        TextView aide, nomTut, nomEnf, msg,phone, email;
        public RequestViewAdapter(final View view) {
            super(view);
            aide = view.findViewById(R.id.aide);
            nomTut = view.findViewById(R.id.nomTut);
            nomEnf = view.findViewById(R.id.nomEnf);
            phone = view.findViewById(R.id.phoone);
            msg = view.findViewById(R.id.msg);
            email = view.findViewById(R.id.emaiil);

        }
    }

    @NonNull
    @Override
    public AideAdapter.RequestViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.activity_aide_one_item, parent, false);
        return new RequestViewAdapter(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AideAdapter.RequestViewAdapter holder, int position) {
        AideNew request = listeAide.get(position);

        holder.nomTut.setText((request.getNameTut()));
        holder.nomEnf.setText((request.getNameEnf()));
        holder.phone.setText((request.getPhone()));
        holder.msg.setText((request.getMsg()));
        holder.email.setText((request.getEmail()));


    }

    @Override
    public int getItemCount() {
        return listeAide.size();
    }


}