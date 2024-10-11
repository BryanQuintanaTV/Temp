package mx.edu.tecnm.chihuahuaii.moviles.almacenarpreferencias;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class verDatos extends AppCompatActivity {

    TextView textView_nombre, textView_fecha;
    ImageView imageView_tema;
    Button button_regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver_datos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Vincular Objetos
        textView_nombre = findViewById(R.id.textView_nombre);
        textView_fecha = findViewById(R.id.textView_fecha);
        imageView_tema = findViewById(R.id.imageView_tema);
        button_regresar = findViewById(R.id.button_regresar);

        // Recuperar preferencias
        String nombre, tema, fecha;

        // Acceder a las preferencias
        SharedPreferences sharedPreferences = getSharedPreferences("preferencias1", Context.MODE_PRIVATE);
        nombre = sharedPreferences.getString("nombre", "");
        tema = sharedPreferences.getString("tema", "");
        fecha = sharedPreferences.getString("fecha", "");

        // Mostrar datos en la GUI
        textView_nombre.setText(nombre);
        textView_fecha.setText(fecha);
        // Mostrar imagen según el tema
        if (tema.equals("Planta")) {
            imageView_tema.setImageResource(R.drawable.icon_flower);
        } else if (tema.equals("Animal")) {
            imageView_tema.setImageResource(R.drawable.icon_animal);
        } else if (tema.equals("Figura")) {
            imageView_tema.setImageResource(R.drawable.icon_shape);
        }

        // Escuchador para botón "Regresar"
        button_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}