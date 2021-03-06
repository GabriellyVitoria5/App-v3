package com.example.agentesprosaude_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ferramentas.EventosDB;

public class MainActivity extends AppCompatActivity {

    //iniciar variáveis
    private TextView titulo1;
    private TextView textoDeLogin;
    private EditText cpfTxt;
    private EditText senhaTxt;
    private Button confirmar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //relacionar variáveis
        titulo1 = (TextView) findViewById(R.id.titulo1Txt);
        textoDeLogin = (TextView) findViewById(R.id.textoDeLoginTxt);
        cpfTxt = (EditText) findViewById(R.id.cpfTxt);
        senhaTxt = (EditText) findViewById(R.id.senhaTxt);
        confirmar = (Button) findViewById(R.id.confirmarBtn);

        eventos();
        //autenticaUsuario(cpfTxt.getText().toString(), Integer.parseInt(senhaTxt.getText().toString()));
    }

    private boolean autenticaUsuario(String cpf, int senha){

        EventosDB db = new EventosDB(MainActivity.this);
        db.insereAgente();

        //receber o return do método buscaAgente
        boolean resultado = db.buscaAgente(cpf, senha);

        if(resultado == true){
            return true;
        }
        else{
            //mensagem de erro
            Toast.makeText(MainActivity.this, "Falha ao logar", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    public String pegarNomeAgente(){
        EventosDB db = new EventosDB(MainActivity.this);
        String nome = db.nomeAgente(cpfTxt.getText().toString());

        return nome;
    }

    private void eventos(){

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //quando o usuário clicar no botão o método autenticaUsuario será chamado
                //ele verifica se a buscaAgente encontrou um agente com os dados correspondentes aos informados no campo de texto
                if(autenticaUsuario(cpfTxt.getText().toString(), Integer.parseInt(senhaTxt.getText().toString())) == true){
                    Intent trocarAct = new Intent(MainActivity.this, EscolhaQuestionario.class);
                    trocarAct.putExtra("acao", 0);
                    startActivity(trocarAct);
                }

            }
        });
    }

}