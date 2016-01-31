package com.englishnary.eridev.android.moviesfinder;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private EditText et_pelicula;

    private ImageView iv_caratula;
    private TextView tv_titulo, tv_guionistas, tv_actores, tv_plot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarUI();
    }

    private void inicializarUI() {
        et_pelicula = (EditText) findViewById(R.id.et_pelicula);

        iv_caratula = (ImageView) findViewById(R.id.iv_caratula);
        tv_titulo = (TextView) findViewById(R.id.tv_titulo);
        tv_guionistas = (TextView) findViewById(R.id.tv_guionistas);
        tv_actores = (TextView) findViewById(R.id.tv_actores);
        tv_plot = (TextView) findViewById(R.id.tv_plot);
    }

    public void buscarPelicula(View view) {
        String titulo = et_pelicula.getText().toString();
        if (!TextUtils.isEmpty(titulo)) {
            String url = String.format(
                    "http://mymovieapi.com/?title=%1$s&type=json&limit=10",
                    titulo);
            new LoadFilmTask().execute(url);
        }
    }

    public static final String TAG = "com.englishnary.eridev.android.moviesfinder";

    private class LoadFilmTask extends AsyncTask<String, Long, String> {
        protected String doInBackground(String... urls) {
            try {
                return HttpRequest.get(urls[0]).accept("application/json")
                        .body();
            } catch (HttpRequestException exception) {
                return null;
            }
        }

        protected void onPostExecute(String response) {
            List<Pelicula> peliculas = getPeliculas(response);

            if (!peliculas.isEmpty())
                mostrarPelicula(peliculas.get(0));
        }
    }

    private void mostrarPelicula(Pelicula pelicula) {
        tv_titulo.setText(pelicula.getTitulo());
        tv_guionistas.setText(Arrays.toString(pelicula.getGuionistas()));
        tv_actores.setText(Arrays.toString(pelicula.getActores()));
        tv_plot.setText(pelicula.getPlot_simple());
        if (pelicula.getCaratula() != null
                && pelicula.getCaratula().getImdb() != null) {
            Picasso.with(getApplicationContext())
                    .load(pelicula.getCaratula().getImdb()).into(iv_caratula);
        }
    }

    private List<Pelicula> getPeliculas(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Pelicula>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    private String prettyfyJSON(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);
        return gson.toJson(element);
    }
}
