package com.cet.inshorts;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    ProgressDialog progressDialog;
    ImageView image;
    TextView title,author,description,timeStamp;
    Button prev,next,readMore,menu;
    int Currentindex=0;
    ArrayList<Article> articleList=new ArrayList<>();
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog=new ProgressDialog(this);
        image=(ImageView)findViewById(R.id.image);
        title=(TextView)findViewById(R.id.title);
        description=(TextView)findViewById(R.id.desc);
        prev=(Button)findViewById(R.id.prev);
        next=(Button)findViewById(R.id.next);
        menu=(Button)findViewById(R.id.menu);
        timeStamp=(TextView) findViewById(R.id.time);
        readMore=(Button)findViewById(R.id.readMore);

        sharedPref=getSharedPreferences("inshorts",0);
        editor=sharedPref.edit();

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(articleList!=null)
                {
                    if(Currentindex>0)
                        Currentindex-=1;
                    else
                    {
                        Currentindex=articleList.size()-1;
                    }
                    Log.d("Current Index",String.valueOf(Currentindex));
                    displayArticle(articleList.get(Currentindex));
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(articleList!=null)
                {
                    if(Currentindex<articleList.size())
                        Currentindex+=1;
                    else
                        Currentindex=0;
                    Log.d("Current Index",String.valueOf(Currentindex));
                    displayArticle(articleList.get(Currentindex));
                }
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Menu.class);
                startActivity(intent);
            }
        });

        readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ReadMoreNews.class);
                intent.putExtra("article",articleList.get(Currentindex));
                startActivity(intent);
            }
        });

        getNewsData();
    }

    public void getNewsData()
    {
        String reqdUrl="articles?source=the-next-web&sortBy=latest&apiKey=2cf080cea55c4f9d9355efd4a185dcaf&category=";
        reqdUrl+=getSharedPref();
        Toast.makeText(getApplicationContext(),getSharedPref(),Toast.LENGTH_SHORT).show();
        Log.d("SHARED PREF",reqdUrl);
        progressDialog.setMessage("Getting Your News!!");
        progressDialog.show();
        RestApiInterface service=ApiService.getService();
        Call<ResponeModel> call=service.getNews(reqdUrl);
        call.enqueue(new Callback<ResponeModel>() {
            @Override
            public void onResponse(Call<ResponeModel> call, Response<ResponeModel> response) {
                progressDialog.dismiss();
                if(response.code()==200)
                {
                    ResponeModel resp=response.body();
                    articleList=resp.articles;
                    Currentindex=0;
                    displayArticle(articleList.get(Currentindex));
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponeModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_SHORT).show();
                Log.d("ERROR",t.toString());
            }
        });
    }

    public void showArticles(List<Article> articles)
    {
        for(Article e:articles)
        {
            Log.d("ARTICLE "+e.title,e.author);
        }
    }

    public void displayArticle(Article article)
    {
        progressDialog.setMessage("Loading Image");
        title.setText(article.title);
        timeStamp.setText(article.publishedAt);
        description.setText(article.description);
        Picasso.with(this).load(article.urlToImage).placeholder(R.drawable.conv2).into(image);
        progressDialog.dismiss();
    }

    public void setSharedPreferences(String cat)
    {
        editor.putString("category",cat);
        editor.commit();
    }

    public String getSharedPref()
    {
        return sharedPref.getString("category","technology");
    }

    public void Share(View v)
    {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = articleList.get(Currentindex).description;
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, articleList.get(Currentindex).title);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
}
