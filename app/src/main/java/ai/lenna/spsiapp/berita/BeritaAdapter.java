package ai.lenna.spsiapp.berita;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ai.lenna.spsiapp.R;
import ai.lenna.spsiapp.network.ApiBuilder;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.MyViewHolder> {

    ArrayList<Berita> beritas;

    public  BeritaAdapter( ArrayList<Berita> beritas){
        this.beritas = beritas;
    }

    @NonNull
    @Override
    public BeritaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_berita, parent, false);
        return new MyViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull BeritaAdapter.MyViewHolder holder, int position) {
        holder.tvTitle.setText(beritas.get(position).getTitle());
        holder.tvDeskripsi.setText(beritas.get(position).getDeskripsi());
        Picasso.get().load(ApiBuilder.BASE_URL + "uploads/"+ beritas.get(position).getImage()).into(holder.ivBerita);

    }

    @Override
    public int getItemCount() {
        return beritas.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDeskripsi;
        ImageView ivBerita;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDeskripsi = (TextView)itemView.findViewById(R.id.tv_deskripsi);
            ivBerita = (ImageView) itemView.findViewById(R.id.iv_berita);
        }
    }
}
