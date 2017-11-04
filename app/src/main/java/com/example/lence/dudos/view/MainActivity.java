package com.example.lence.dudos.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.lence.dudos.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MVP {


    RecyclerView mRecyclerView;
    @BindView(R.id.post_button)
    Button mPostButton;
    @BindView(R.id.get_button)
    Button mGetButton;
    @BindView(R.id.insert_button)
    Button mInsertButton;


    GetAdapter mGetAdapter;
    PostAdapter mPostAdapter;
    Presenter mPresenter;
    int k = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new Presenter(this);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);


    }

    @OnClick(R.id.post_button)
    public void onMPostButtonClicked() {
        mPostAdapter = new PostAdapter(mPresenter, new ArrayList<String>());
        mRecyclerView.setAdapter(mPostAdapter);
        mPresenter.loadPost(k);
        mRecyclerView.setAdapter(mPostAdapter);
    }

    @OnClick(R.id.get_button)
    public void onMGetButtonClicked() {
        mGetAdapter = new GetAdapter(mPresenter);
        mRecyclerView.setAdapter(mGetAdapter);
        mPresenter.loadGet();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mGetAdapter = new GetAdapter(mPresenter);
        mRecyclerView.setAdapter(mGetAdapter);
    }

    @OnClick(R.id.insert_button)
    public void onMInsertButtonClicked() {
    }

    @Override
    public void showGet(List<String> getList) {
        mGetAdapter.addGet(getList);
        mRecyclerView.getAdapter().notifyDataSetChanged();

    }


    @Override
    public void showIsEmpty() {

    }


    @Override
    public void showPost(List<String> postList) {
        mPostAdapter.addPost(postList);
        mRecyclerView.getAdapter().notifyDataSetChanged();
        if (k <= 10) {
            k++;
            mPresenter.loadPost(k);
        }
    }


}