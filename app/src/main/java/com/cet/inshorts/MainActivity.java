package com.cet.inshorts;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    ImageView image;
    TextView title,author,description;
    Button prev,next;
    int Currentindex=0;
    List<Article> articleList;
    SharedPreferences sharedPref;

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

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Currentindex>0)
                    Currentindex-=1;
                Log.d("Current Index",String.valueOf(Currentindex));
                displayArticle(articleList.get(Currentindex));
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Currentindex<articleList.size())
                    Currentindex+=1;
                Log.d("Current Index",String.valueOf(Currentindex));
                displayArticle(articleList.get(Currentindex));
            }
        });
        getNewsData();
    }

    public void getNewsData()
    {
        String category="technology";
        progressDialog.show();
        RestApiInterface service=ApiService.getService();
        Call<ResponeModel> call=service.getNews(Global.defaultGet);
        call.enqueue(new Callback<ResponeModel>() {
            @Override
            public void onResponse(Call<ResponeModel> call, Response<ResponeModel> response) {
                progressDialog.dismiss();
                if(response.code()==200)
                {
                    ResponeModel resp=response.body();
                    List<Article> articles=resp.articles;
                    articleList=articles;
                    Currentindex=0;
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
        title.setText(article.title);
        description.setText(article.description);
        Picasso.with(this).load(article.urlToImage).into(image);
    }
}
