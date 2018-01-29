package com.cipherflux.malac.helpr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Main extends AppCompatActivity {
    Button logout;
    FirebaseAuth mAuth;
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logout = (Button)findViewById(R.id.btn_logout);
        mAuth = FirebaseAuth.getInstance();
        list = (ListView)findViewById(R.id.card_list);

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card("drawable://" + R.drawable.a1,"high"));
        cards.add(new Card("drawable://" + R.drawable.a2,"high"));
        cards.add(new Card("drawable://" + R.drawable.a3,"high"));
        cards.add(new Card("drawable://" + R.drawable.a4,"high"));
        cards.add(new Card("drawable://" + R.drawable.a5,"high"));
        cards.add(new Card("drawable://" + R.drawable.a6,"high"));


        CustomListAdapter adapter = new CustomListAdapter(getApplicationContext(), R.layout.card, cards);
        list.setAdapter(adapter);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });
    }


    private void signOut() {
        if(mAuth.getCurrentUser() != null){
            mAuth.signOut();
            finish();
            startActivity(new Intent(this, LogIn.class));
        }

    }
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LogIn.class));
        }
    }
}
