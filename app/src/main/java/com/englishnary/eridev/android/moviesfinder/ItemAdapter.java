package com.englishnary.eridev.android.moviesfinder;

/**
 * Created by eridev on 31/01/16.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

public class ItemAdapter extends BaseAdapter {

    private Context context;
    private List<Pelicula> items;

    public ItemAdapter(Context context, List<Pelicula> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Create a new view into the list.
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);

        // Set data into the view.
        ImageView iv_caratula = (ImageView) rowView.findViewById(R.id.iv_caratula);
        TextView tv_titulo = (TextView) rowView.findViewById(R.id.tv_titulo);
        TextView tv_guionistas = (TextView) rowView.findViewById(R.id.tv_guionistas);
        TextView tv_actores = (TextView) rowView.findViewById(R.id.tv_actores);
        TextView tv_plot = (TextView) rowView.findViewById(R.id.tv_plot);

        Pelicula item = this.items.get(position);
        if (item != null) {
            tv_titulo.setText(item.getTitulo());
            tv_guionistas.setText(Arrays.toString(item.getGuionistas()));
            tv_actores.setText(Arrays.toString(item.getActores()));
            tv_plot.setText(item.getPlot_simple());
            if (item.getCaratula() != null && item.getCaratula().getImdb() != null) {
                Picasso.with(context).load(item.getCaratula().getImdb())
                        .into(iv_caratula);
            }
        }

        return rowView;
    }

}
