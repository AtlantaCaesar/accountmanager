package com.accountingmanager.Fragment.Accounting.Fund;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.accountingmanager.Base.ContentBaseFragment;
import com.accountingmanager.Base.ServiceBaseActivity;
import com.accountingmanager.R;
import com.accountingmanager.Sys.Communication.OnItemRecyclerViewClickListener;
import com.accountingmanager.Sys.Config.AppConfig;
import com.accountingmanager.Sys.Model.AssetsElementModel;
import com.accountingmanager.Sys.Utils.ImgUtils;
import com.accountingmanager.Utils.FundContentInputAdapter;

/**
 * 基金手动选择页面
 * Created by Home-Pc on 2017/5/9.
 */

public class FundContentInputFragment extends ContentBaseFragment implements View.OnClickListener {

    private RecyclerView FundContent_Show;
    private Button fund_content_search;

    private FundContentInputAdapter adapter;

    private AssetsElementModel model = new AssetsElementModel();
    private Bundle bundle;

    @Override
    protected View initViews(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fm_fund_content_layout, null);
    }

    @Override
    protected void initWidgets(View fgView) {
        FundContent_Show = (RecyclerView) fgView.findViewById(R.id.FundContent_Show);
        fund_content_search = (Button) fgView.findViewById(R.id.fund_content_search);
    }

    @Override
    protected void initEvent() {
        fund_content_search.setOnClickListener(this);

        observeMessage(AppConfig.getInstance().ADD_INPUT_GET_CONTENT_TAG_6);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        adapter = new FundContentInputAdapter(getActivity());
        bundle = getArguments();

        FundContent_Show.setLayoutManager(gridLayoutManager);
        FundContent_Show.setAdapter(adapter);

        adapter.setOnItemRecyclerViewClickListener(new OnItemRecyclerViewClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                LinearLayout linearLayout = (LinearLayout) view;
                TextView textView = (TextView) linearLayout.getChildAt(1);

                model.setMenuName(textView.getText() == null ? "" : textView.getText().toString());
                bundle.putSerializable(AppConfig.getInstance().FRAGMENT_OBJECT_TAG,model);
                ServiceBaseActivity.startActivity(getActivity(),FundDetailsContentInputFragment.class.getName(),bundle);
            }
        });


        if (bundle.containsKey(AppConfig.getInstance().FRAGMENT_OBJECT_TAG) &&
                bundle.getSerializable(AppConfig.getInstance().FRAGMENT_OBJECT_TAG) != null) {
            model = (AssetsElementModel) bundle.getSerializable(AppConfig.getInstance().FRAGMENT_OBJECT_TAG);
            setTitleWhiteStyle(model.getMenuName() == null ? "" : model.getMenuName());
        }

    }

    @Override
    public void onResponsSuccess(int TAG, Object result) {

    }

    @Override
    public void onResponsFailed(int TAG, String result) {

    }

    @Override
    public void onNetConnectFailed(int TAG, String result) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fund_content_search:
                break;
        }
    }

    @Override
    protected void onReceiveMessage(String msgkey, Object msgObject) {
        super.onReceiveMessage(msgkey, msgObject);
        if(msgkey.equals(AppConfig.getInstance().ADD_INPUT_GET_CONTENT_TAG_6)){
            sendMessage(AppConfig.getInstance().ADD_INPUT_GET_CONTENT_TAG_3,msgObject);
            getActivity().finish();
        }
    }
}
