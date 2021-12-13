package com.example.agentesprosaude_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.agentesprosaude_app.MainActivity;

public class EscolhaQuestionario extends AppCompatActivity {

    //iniciar variavel
    DrawerLayout drawerLayout;

    private TextView nomeAgente;
    private LayoutInflater inflater;

    private Button confirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_questionario);

        //relacionar variáveis
        drawerLayout = findViewById(R.id.drawer_layout);

        nomeAgente = findViewById(R.id.agenteConectado);
        inflater = (LayoutInflater) EscolhaQuestionario.this.getSystemService(LAYOUT_INFLATER_SERVICE);

        confirmar = (Button) findViewById(R.id.confirmarBtn);

        //ir para a activity Questionario ao clicar no botão
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trocarAct = new Intent(EscolhaQuestionario.this, Questionario.class);
                startActivity(trocarAct);
            }
        });
    }

    //abrir drawer
    public void acessarMenu(View view){
        abrirDrawer(drawerLayout);
    }

    //abrir drawer layout
    private void abrirDrawer(DrawerLayout drawerLayout) {
        //inflar o layout com nome do agente conectado
        /*MainActivity main = new MainActivity();
        View itemView = inflater.inflate(R.layout.main_nav_drawer, null);
        ((TextView) itemView.findViewById(R.id.agenteConectado)).setText(main.pegarNomeAgente());*/

        drawerLayout.openDrawer(GravityCompat.START);
    }

    //fechar drawer se estiver aberto
    public static void fecharDrawer(DrawerLayout drawerLayout){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    //ir para a tela de login (MainActivity) chamando o método mudarDeActivity
    public void sair(View view){
        mudarDeActivity(this, MainActivity.class);
    }

    //metodo para mudar de activity
    public static void mudarDeActivity(Activity activity, Class aClass){
        Intent intent = new Intent(activity, aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    //fechar o drawer
    @Override
    protected void onPause(){
        super.onPause();
        fecharDrawer(drawerLayout);
    }
}