package mx.edu.tecnm.chihuahuaii.moviles.almacenarpreferencias;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.jetbrains.annotations.TestOnly;

public class MainActivity extends AppCompatActivity {

    EditText editText_nombre;
    RadioGroup radioGroup;
    RadioButton radioButton_planta, radioButton_animal, radioButton_figura;
    CalendarView calendarView_fecha;
    String selectedDate;
    Button button_guardar, button_ver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Vincular Objetos
        editText_nombre = findViewById(R.id.editText_nombre);
        radioGroup = findViewById(R.id.radioGroup);
        radioButton_planta = findViewById(R.id.radioButton_planta);
        radioButton_animal = findViewById(R.id.radioButton_animal);
        radioButton_figura = findViewById(R.id.radioButton_figura);
        calendarView_fecha = findViewById(R.id.calendarView_fecha);
        button_guardar = findViewById(R.id.button_guardar);
        button_ver = findViewById(R.id.button_ver);

        // Código para bóton button_guardar
        button_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                *  El método getSharedPreferences() permite definir un nombre de archivo para guardar preferencias.
                *  Este archivo se podrá usar cualquier actividad de la app.
                * */

                SharedPreferences sharedPreferences = getSharedPreferences("preferencias1", Context.MODE_PRIVATE);

                // Declarar objeto para editar preferencias.
                SharedPreferences.Editor editor = sharedPreferences.edit();

                // Recuperar y Guardar el nombre
                String nombre = editText_nombre.getText().toString();
                editor.putString("nombre", nombre);

                // Recuperar y Guardar el tema
                if (radioButton_planta.isChecked()) {
                    editor.putString("tema", "Planta");
                } else if (radioButton_animal.isChecked()) {
                    editor.putString("tema", "Animal");
                } else if (radioButton_figura.isChecked()) {
                    editor.putString("tema", "Figura");
                }

                // Recuperar y Guardar la fecha
                selectedDate = String.valueOf(calendarView_fecha.getDate());
                editor.putString("fecha", selectedDate);

                // Guardar cambios
                editor.commit();

                // Mensaje de confirmación
                Toast.makeText(MainActivity.this, "Preferencias guardadas: " + selectedDate, Toast.LENGTH_SHORT).show();

            }
        });

        // Agregar escuchador de eventos en CalendarView y guardar fecha en formato personalizado.
        calendarView_fecha.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {

                selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;

            }
        });

        // Escuchador de botón abrir "VerDatos".
        button_ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent_verDatos = new Intent(MainActivity.this, verDatos.class);
                startActivity(intent_verDatos);
            }
        });

    } // Fin onCreate
}