package com.example.seminarkp1.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.seminarkp1.FirebaseDBCreateActivity;
import com.example.seminarkp1.FirebaseDBReadActivity;
import com.example.seminarkp1.FirebaseDBReadSingleActivity;
import  com.example.seminarkp1.R;
import com.example.seminarkp1.model.Pegawai;
import com.github.ivbaranov.mli.MaterialLetterIcon;

import androidx.recyclerview.widget.RecyclerView;

public class AdapterBarangRecyclerView extends RecyclerView.Adapter<AdapterBarangRecyclerView.ViewHolder> implements Filterable {

    private ArrayList<Pegawai> daftarBarang;
    //private ArrayList<Pegawai> mFilteredList; tambahan
    private Context context;
    FirebaseDBReadActivity listener;
    private int[] mMaterialColors;

    public AdapterBarangRecyclerView(ArrayList<Pegawai> barangs, Context ctx){
        /**
         * Inisiasi data dan variabel yang akan digunakan
         */
        daftarBarang = barangs;
        context = ctx;
        listener = (FirebaseDBReadActivity)ctx;
        mMaterialColors = ctx.getResources().getIntArray(R.array.color);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * Inisiasi View
         * Di tutorial ini kita hanya menggunakan data String untuk tiap item
         * dan juga view nya hanyalah satu TextView
         */
        MaterialLetterIcon mIcon;
        TextView tvTitle;
        TextView tvnip;

        ViewHolder(View v) {
            super(v);
            mIcon = itemView.findViewById(R.id.mMaterialLetterIcon);
            tvTitle = (TextView) v.findViewById(R.id.tv_namabarang);
            tvnip=(TextView) v.findViewById(R.id.tv_merkBarang);        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         *  Inisiasi ViewHolder
         */
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        /**
         *  Menampilkan data pada view
         */
        final Pegawai s= daftarBarang.get(position);
        final String name = daftarBarang.get(position).getNama();
        final String nip= daftarBarang.get(position).getMerk();
        System.out.println("BARANG DATA one by one "+position+daftarBarang.size());
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 *  Kodingan untuk tutorial <span data-mce-type="bookmark" style="display: inline-block; width: 0px; overflow: hidden; line-height: 0;" class="mce_SELRES_start"></span>Read detail data
                 */
                context.startActivity(FirebaseDBReadSingleActivity.getActIntent((Activity) context).putExtra("data", daftarBarang.get(position)));
            }
        });
       holder.tvTitle.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View view) {
               /**
                *  Kodingan untuk tutorial delete dan update data
                */
               final Dialog dialog = new Dialog(context);
               dialog.setContentView(R.layout.dialog_view);
               dialog.setTitle("Pilih Aksi");
               dialog.show();

               Button editButton = (Button) dialog.findViewById(R.id.bt_edit_data);
               Button delButton = (Button) dialog.findViewById(R.id.bt_delete_data);

               //apabila tombol edit diklik
               editButton.setOnClickListener(
                       new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               dialog.dismiss();
                               context.startActivity(FirebaseDBCreateActivity.getActIntent((Activity) context).putExtra("data", daftarBarang.get(position)));
                           }
                       }
               );

               //apabila tombol delete diklik
               delButton.setOnClickListener(
                       new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               dialog.dismiss();
                               listener.onDeleteData(daftarBarang.get(position), position);
                           }
                       }
               );
               return true;
           }
       });
        holder.tvTitle.setText(name);
        holder.tvnip.setText(nip);
        holder.mIcon.setInitials(true);
        holder.mIcon.setInitialsNumber(2);
        holder.mIcon.setLetterSize(25);
        holder.mIcon.setShapeColor(mMaterialColors[new Random().nextInt(mMaterialColors.length)]);
        holder.mIcon.setLetter(s.getNama());
    }
    @Override
    public int getItemCount() {
        /**
         * Mengembalikan jumlah item pada barang
         */
        return daftarBarang.size();
    }
    public interface FirebaseDataListener{
        void onDeleteData(Pegawai barang, int position);
    }
//tambahan

    public Filter getFilter()
    {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Pegawai> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(daftarBarang);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Pegawai item : daftarBarang) {
                    if (item.getNama().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            daftarBarang.clear();
            daftarBarang.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };
}





