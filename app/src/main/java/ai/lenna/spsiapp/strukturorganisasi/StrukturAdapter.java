package ai.lenna.spsiapp.strukturorganisasi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import ai.lenna.spsiapp.R;

public class StrukturAdapter extends RecyclerView.Adapter<StrukturAdapter.MyViewHolder> {

    ArrayList<Struktur> beritas;

    public  StrukturAdapter( ArrayList<Struktur> beritas){
        this.beritas = beritas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_struktur, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTitle.setText(beritas.get(position).getJabatan());
        holder.tvDeskripsi.setText(beritas.get(position).getNama());
    }

    @Override
    public int getItemCount() {
        return beritas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDeskripsi;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDeskripsi = (TextView)itemView.findViewById(R.id.tv_deskripsi);
        }
    }
}
