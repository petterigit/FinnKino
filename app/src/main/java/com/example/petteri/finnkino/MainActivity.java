package com.example.petteri.finnkino;


import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Spinner teatterit_spinner;
    ListView elokuvat_listView;
    EditText pvm_text;
    EditText alkaa_text;
    EditText paattyy_text;
    EditText nimi_txt;
    Button haku_button;

    ArrayList<Elokuva> elokuva_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        teatterit_spinner = (Spinner) findViewById(R.id.Teatterispinner);
        elokuvat_listView = (ListView) findViewById(R.id.listViewElokuvat);
        pvm_text = (EditText) findViewById(R.id.editText);
        alkaa_text = (EditText) findViewById(R.id.editText2);
        paattyy_text = (EditText) findViewById(R.id.editText4);
        nimi_txt = (EditText) findViewById(R.id.editText3);


        ArrayList<Teatteri> teatteri_lista = Teatteri_lista.getInstance().getList();

        ArrayAdapter<Teatteri> adapter_t = new ArrayAdapter<Teatteri>(this,
                android.R.layout.simple_spinner_item, teatteri_lista);
        adapter_t.setDropDownViewResource(android.R.layout.simple_list_item_1);
        teatterit_spinner.setAdapter(adapter_t);


        elokuva_list = new ArrayList<Elokuva>();
        ArrayAdapter<Elokuva> adapter_e = new ArrayAdapter<Elokuva>(this,
                android.R.layout.simple_list_item_1, elokuva_list);
        elokuvat_listView.setAdapter(adapter_e);






    }
    public void update(View v) {
        Teatteri teatteri = (Teatteri) teatterit_spinner.getSelectedItem();

        int id = teatteri.getTeatteri_id();
        System.out.println("Haetaan elokuvia teatterista: " + id);
        String pvm = pvm_text.getText().toString();
        if(pvm.isEmpty()){
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            pvm = sdf.format(new Date());
        }

        String alkaa = alkaa_text.getText().toString();
        String paattyy = paattyy_text.getText().toString();
        String nimi = nimi_txt.getText().toString();

        elokuva_list.clear();
        elokuva_list.addAll(Xml_handler.getInstance().getElokuvaList(""+id, pvm));


        if(alkaa.isEmpty()) {
            alkaa = "00:00:00";
        }
        if(paattyy.isEmpty()) {
            paattyy = "23:59:59";
        }
        String[] nlist = alkaa.split(":");
        int n = Integer.parseInt(nlist[0] + nlist[1]);

        String[] xlist = paattyy.split(":");
        int x = Integer.parseInt(xlist[0] + xlist[1]);

        String[] enlist;
        int e;

        for (int i = elokuva_list.size() - 1; i >= 0; i--) {
            Elokuva elk = elokuva_list.get(i);

            enlist = elk.getAlku().split(":");
            e = Integer.parseInt(enlist[0] + enlist[1]);
            if (e<n || e>x || !elk.getNimi().contains(nimi)) {
                elokuva_list.remove(elk);
            }

        }

        ((ArrayAdapter) elokuvat_listView.getAdapter()).notifyDataSetChanged();

    }
}
