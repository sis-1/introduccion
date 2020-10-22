package com.example.introduccion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView textos;
    private EditText datos;
    private Button envios;
    private RatingBar puntos;
    private SeekBar seekBar;
    private ProgressBar progressBar;
    private Switch aSwitch;
    private Spinner spinner;
    private ImageView imageView;
    private CheckBox si, no;
    private RadioButton r1, r2;
    private ListView listView;

    private ArrayList<String> cursos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textos = (TextView)findViewById(R.id.tw); // Ver texto en pantallain
        datos = (EditText)findViewById(R.id.info);
        envios = (Button)findViewById(R.id.envio);
        puntos = (RatingBar)findViewById(R.id.estrellas);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        aSwitch = (Switch)findViewById(R.id.switch1);
        spinner = (Spinner)findViewById(R.id.spinner);
        imageView = (ImageView)findViewById(R.id.imageView);
        si = (CheckBox)findViewById(R.id.checkBox);
        no = (CheckBox)findViewById(R.id.checkBox2);
        r1 = (RadioButton)findViewById(R.id.radioButton);
        r2 = (RadioButton)findViewById(R.id.radioButton2);
        listView = (ListView)findViewById(R.id.cursos);

        //Datos para llenar un ListView
        cursos = new ArrayList<String>();
        cursos.add("Java");
        cursos.add("php");
        cursos.add("C++");
        ArrayAdapter<String> adapterList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cursos);
        listView.setAdapter(adapterList);


        envios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valor;
                valor = datos.getText().toString();
                textos.setText(valor);
                //CheckBox
                if (si.isChecked()){
                    textos.setText("ACEPTADO");
                }else if (no.isChecked()){
                    textos.setText("NEGADO");
                }
                //Mensaje informativo
                //Toast.makeText(getApplicationContext(), "HOLA ING", Toast.LENGTH_LONG).show();
                Snackbar.make(view, "HOLA ING", Snackbar.LENGTH_LONG).setAction("ACEPTAR", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "HOLA ING", Toast.LENGTH_LONG).show();
                    }
                }).show();
            }
        });

        puntos.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                textos.setText("Estrellas: " + v);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textos.setText("SeeBar: " + i);
                progressBar.setProgress(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    textos.setText("Aceptar");
                }else{
                    textos.setText("No aceptar");
                }
            }
        });

        //Creamos un adaptador para que el spinner tome los datos desde datos_spinner.xml
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planetas,
                R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String elemento;
                elemento = spinner.getSelectedItem().toString();
                textos.setText(elemento);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //Agregar imagen desde codigo
        //imageView.setImageResource(R.drawable.sls);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                textos.setText(adapterView.getItemAtPosition(i).toString());
            }
        });

    }
    //Determinar el radioButton seleccionado
    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton)view).isChecked();
        //RadioButton seleccionado
        switch (view.getId()){
            case R.id.radioButton:
                if (checked){
                    textos.setText("R1");
                }
                break;
            case R.id.radioButton2:
                if (checked){
                    textos.setText("R2");
                }
                break;
            case R.id.radioButton3:
                if (checked){
                    textos.setText("R3");
                }
                break;
        }
    }
}