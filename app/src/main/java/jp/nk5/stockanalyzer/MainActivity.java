package jp.nk5.stockanalyzer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import jp.nk5.stockanalyzer.viewmodel.MainViewModel;
import jp.nk5.stockanalyzer.adapter.CurrentStockAdapter;
import jp.nk5.stockanalyzer.application.MainApplication;
import jp.nk5.stockanalyzer.domain.CurrentStock;

public class MainActivity extends BaseActivity {

    private MainApplication application;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new MainViewModel(new ArrayList<CurrentStock>());
        application = new MainApplication(this,this, this.viewModel);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        application.getCurrentPrices();
    }

    public void onClickStockButton(View view)
    {
        Intent intent = new Intent(this, StockActivity.class);
        startActivity(intent);
    }

    @Override
    public void updateView()
    {
        CurrentStockAdapter adapter = new CurrentStockAdapter(this, android.R.layout.simple_list_item_1, viewModel.getCurrentStocks());
        ListView listView = this.findViewById(R.id.listView1);
        listView.setAdapter(adapter);
    }
}
