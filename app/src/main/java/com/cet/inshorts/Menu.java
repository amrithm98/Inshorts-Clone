package com.cet.inshorts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        sharedPref=getSharedPreferences("inshorts",0);
        editor=sharedPref.edit();
    }

    public void setTech(View v)
    {
        editor.putString("category","technology");
        editor.commit();

        Intent intent=new Intent(Menu.this,MainActivity.class);
        startActivity(intent);
    }
    public void setBusi(View v)
    {
        editor.putString("category","business");
        editor.commit();

        Intent intent=new Intent(Menu.this,MainActivity.class);
        startActivity(intent);

    }
    public void setSport(View v)
    {
        editor.putString("category","sport");
        editor.commit();

        Intent intent=new Intent(Menu.this,MainActivity.class);
        startActivity(intent);

    }

    public void setHealth(View v)
    {
        editor.putString("category","politics");
        editor.commit();

        Intent intent=new Intent(Menu.this,MainActivity.class);
        startActivity(intent);
    }

    public void setEntertain(View v)
    {
        editor.putString("category","music");
        editor.commit();

        Intent intent=new Intent(Menu.this,MainActivity.class);
        startActivity(intent);
    }
}

