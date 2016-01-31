package com.englishnary.eridev.android.moviesfinder;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.kevinsawicki.http.HttpRequest;
import com.github.kevinsawicki.http.HttpRequest.HttpRequestException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

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
    //Metod to initialize componente from UI
    private void inicializarUI() {
        et_pelicula = (EditText) findViewById(R.id.et_pelicula);

        iv_caratula = (ImageView) findViewById(R.id.iv_caratula);
        tv_titulo = (TextView) findViewById(R.id.tv_titulo);
        tv_guionistas = (TextView) findViewById(R.id.tv_guionistas);
        tv_actores = (TextView) findViewById(R.id.tv_actores);
        tv_plot = (TextView) findViewById(R.id.tv_plot);
    }

   //Metod to find movie callin API
    //2 Now the buscarPelicula update () method for a new thread that made the request with the
    // URL to believe from the name of the film is believed introduced:

    public void buscarPelicula(View view) {
        String titulo = et_pelicula.getText().toString();
        if (!TextUtils.isEmpty(titulo)) {
            //you can specify various parameters in the URL, but this app let's just say the name,
            // the type of response and the number of records we want to obtain:
            String url = String.format(
                    "http://mymovieapi.com/?title=%1$s&type=json&limit=10",
                    titulo);
            new LoadFilmTask().execute(url);
        }
    }

    public static final String TAG = "com.englishnary.eridev.android.moviesfinder";

    /*
    To perform the GET request to access the information returned in the response we will use
     the http-request library. To install this library in our application we can
    include our project in Maven dependency or HttpRequest.java download the file and add
    it to our project.
    Only we have to parameterize the value specified in the title parameter because the type
    parameter must be json and limit will be 10. For the GET request should add to our activity
    over AsyncTask task. We define a AsyncTask because they should not use an HttpRequest object
    in the main thread of the application:
    */
    private class LoadFilmTask extends AsyncTask<String, Long, String> {

       /*
       * Perform the HTTP request in doInBackground () method using the get () method which
       * provides the http-library request also specified that we return the response body with
        * the body (method)
       * */
        protected String doInBackground(String... urls) {
            try {
                return HttpRequest.get(urls[0]).accept("application/json")
                        .body();
            } catch (HttpRequestException exception) {
                return null;
            }
        }

// The response of the request will be treated in the onPostExecute () method.
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
