package com.app.newzo;

import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String API = "c7b01d0072804865b51719b8807170c6";

    RecyclerView recyclerView;
    List<Modals> data;
    Adapter adapter;

    NewsApiClient newsApiClient = new NewsApiClient(API);

    private String countryCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerView);
        data = new ArrayList<>();

        TelephonyManager tm = (TelephonyManager) getSystemService(getApplicationContext().TELEPHONY_SERVICE);
        countryCode = tm.getNetworkCountryIso();


        adapter = new Adapter(data, this);

        recyclerView.setAdapter(adapter);

        getNews();


    }

    private void getNews() {

        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                        //.q("bitcoin")
                        .country(countryCode)
                        .language("en")
                        .pageSize(100)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {

                        List<Article> allArticles = response.getArticles();

                        for (int i = 0; i < allArticles.size(); i++) {

                            data.add(new Modals(
                                    allArticles.get(i).getTitle(),
                                    allArticles.get(i).getDescription(),
                                    allArticles.get(i).getSource().getName(),
                                    allArticles.get(i).getPublishedAt().substring(0, 10),
                                    allArticles.get(i).getUrlToImage()
                            ));
                            adapter.notifyDataSetChanged();
                        }


                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                        Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        );


    }

}