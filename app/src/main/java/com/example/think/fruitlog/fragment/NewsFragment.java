package com.example.think.fruitlog.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.think.fruitlog.R;
import com.example.think.fruitlog.adapter.NewsItemAdapter;
import com.example.think.fruitlog.model.News;
import com.example.think.fruitlog.model.NewsItem;
import com.example.think.fruitlog.requestinterface.NewsGetAPI;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressLint("ValidFragment")
public class NewsFragment extends Fragment {
    private RecyclerView recyclerView;
    private Retrofit GRetrofit;
    private NewsGetAPI newsGetAPI;
    private final String getNewsBaseUrl="https://api.tianapi.com/";
    private NewsItemAdapter newsItemAdapter;
    private List<NewsItem> mNewsList=new ArrayList<>();
    private String mContent;
    private SpinKitView loadingView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_news,container,false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_news);
        loadingView=(SpinKitView)view.findViewById(R.id.spin_kit);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        newsItemAdapter=new NewsItemAdapter(mNewsList);
        recyclerView.setAdapter(newsItemAdapter);

        final SwipeRefreshLayout swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.refresh_news);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String mURL=getNewsBaseUrl+mContent+"/";
                        Log.e( "currentURL",mURL);
                        GRetrofit= new Retrofit.Builder()
                                .baseUrl(mURL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                .build();
                        newsGetAPI=GRetrofit.create(NewsGetAPI.class);
                        newsGetAPI.getNews()
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<News>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(News news) {
                                        swipeRefreshLayout.setRefreshing(false);
                                        if(mNewsList!=null)
                                            mNewsList.clear();
                                        mNewsList.addAll(news.newsList);
                                        newsItemAdapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        swipeRefreshLayout.setRefreshing(false);
                                        Toast.makeText(getContext(),"网络异常，加载失败",Toast.LENGTH_SHORT).show();
                                        Log.e( "onError: ", e.toString());
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }
                }).start();
            }
        });
    }

    public void setContent(String content) {
        mContent=content;
        String currentURL=getNewsBaseUrl+content+"/";
        Log.e( "currentURL",currentURL);
        loadingView.setVisibility(View.VISIBLE);
        GRetrofit= new Retrofit.Builder()
                .baseUrl(currentURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        newsGetAPI=GRetrofit.create(NewsGetAPI.class);
        newsGetAPI.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<News>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(News news) {
                        loadingView.setVisibility(View.GONE);
                        if(mNewsList!=null)
                            mNewsList.clear();
                        mNewsList.addAll(news.newsList);
                        newsItemAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadingView.setVisibility(View.GONE);
                        Toast.makeText(getContext(),"网络异常，加载失败",Toast.LENGTH_SHORT).show();
                        Log.e( "onError: ", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
