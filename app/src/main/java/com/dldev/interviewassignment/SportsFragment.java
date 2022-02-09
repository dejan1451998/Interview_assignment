package com.dldev.interviewassignment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SportsFragment extends Fragment {

    String api = "72c0d8e8731545c197762550a771e67c";
    ArrayList<Model> modelList;
    Adapter adapter;
    String country = "rs";
    private RecyclerView recyclerViewSports;
    private String category = "sports";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sportsfragment, null);

        recyclerViewSports = view.findViewById(R.id.recyclerViewSport);
        modelList = new ArrayList<>();
        recyclerViewSports.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), modelList);
        recyclerViewSports.setAdapter(adapter);

        findNews();

        return view;
    }


    private void findNews() {
        ApiUtilities.getApiInterface().getCategoryNews(country, category, 100, api).enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful())
                {
                    modelList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
    }
}
