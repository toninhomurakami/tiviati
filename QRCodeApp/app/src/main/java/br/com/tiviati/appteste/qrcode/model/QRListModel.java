package br.com.tiviati.appteste.qrcode.model;

import android.app.Activity;

import java.util.List;

import br.com.tiviati.appteste.qrcode.adapter.StableArrayAdapter;
import br.com.tiviati.appteste.qrcode.beans.QRCodeBean;

/**
 * Created by Antonio Murakami on 17/07/2016.
 */
public class QRListModel {

    private Activity activity;
    private StableArrayAdapter<QRCodeBean> adapter;

    public QRListModel(Activity activity) {
        this.activity = activity;
    }

    public StableArrayAdapter<QRCodeBean> create(List<QRCodeBean> valueList) {
        adapter = new StableArrayAdapter(activity, android.R.layout.simple_list_item_1, valueList);
        return adapter;
    }

    public void add(QRCodeBean bean) {
        adapter.add(bean);
        activity.runOnUiThread(new Runnable() {
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    public QRCodeBean getItem(int position) {
        return adapter.getItem(position);
    }
}
