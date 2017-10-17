package com.cet.inshorts;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ReadMoreNews extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_more_news);
        Intent intent=getIntent();
        Article article=(Article)intent.getSerializableExtra("article");
        webView=(WebView)findViewById(R.id.webView);
        webView.loadUrl(article.url);
    }
}
