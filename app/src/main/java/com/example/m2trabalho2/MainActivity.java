package com.example.m2trabalho2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Connection connect;
    String ConnectionResult = "";

    RecyclerView recyclerView;
    ArrayList<Contatos> arrayList = new ArrayList<Contatos>();
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();


        //        Spinner tipo_telefone = findViewById(R.id.tipo_telefone);
        //        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        //                R.array.tipo_telefone, android.R.layout.simple_spinner_item);
        //
        //        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        //        tipo_telefone.setAdapter(adapter);
        //        tipo_telefone.setOnItemSelectedListener(this);

        Button nome = findViewById(R.id.Nome);
        nome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragment = getSupportFragmentManager();

                fragment.beginTransaction()
                        .replace(R.id.fragmentContainerView, NomeFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("nome")
                        .commit();
            }
        });


        Button novo = findViewById(R.id.novo);
        novo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, Novo.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("novo")
                        .commit();
            }
        });
    }

    private void checkPermission() {

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS)!=
        PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions( MainActivity.this,
                    new String[]{Manifest.permission.READ_CONTACTS}, 100);

        }
        else{
            listaContatos();
        }
    }

    private void listaContatos() {
        Uri uri = ContactsContract.Contacts.CONTENT_URI;

        String ordenar = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME +" ";

        Cursor cursor = getContentResolver().query(uri, null, null, null, ordenar );

        if (cursor.getCount() > 0){

            while (cursor.moveToNext()){
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                @SuppressLint("Range") String nome = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                Uri uriTelefone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

                String selecao = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " ";

                Cursor cursorTelefone = getContentResolver().query(uriTelefone, null,
                        selecao, new String[]{id}, null);

                if(cursorTelefone.moveToNext()){

                 @SuppressLint("Range") String numero = cursorTelefone.getString
                         (cursorTelefone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                    Contatos contatos = new Contatos();

                    contatos.setNome(nome);
                    contatos.setTelefone(numero);

                    arrayList.add(contatos);

                    cursorTelefone.close();

                }
            }

        }
    }

    public void GerTextFromSQL(View v) {
        TextView tx1 = (TextView) findViewById(R.id.textView);
        TextView tx2 = (TextView) findViewById(R.id.textView2);

        try {
            Connector connector = new Connector();
            connect = connector.connectionClass();
            if (connect != null) {
                 String query = "Select";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);

                while (rs.next()){
                    tx1.setText(rs.getString(1));
                    tx2.setText(rs.getString(2));
                }
            }
        }
        catch (Exception ex) {

        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}