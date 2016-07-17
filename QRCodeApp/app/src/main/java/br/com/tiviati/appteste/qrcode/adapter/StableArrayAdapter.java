package br.com.tiviati.appteste.qrcode.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Antonio Murakami on 17/07/2016.
 * @param <QRCodeBean>
 */
public class StableArrayAdapter<QRCodeBean> extends ArrayAdapter<QRCodeBean> {

    private HashMap<Integer, QRCodeBean> map = new HashMap<Integer, QRCodeBean>();

    public StableArrayAdapter(Context context, int textViewResourceId, List<QRCodeBean> objects) {
        super(context, textViewResourceId, objects);
        for (int i = 0; i < objects.size(); ++i) {
            map.put(i, objects.get(i));
        }
    }

}
