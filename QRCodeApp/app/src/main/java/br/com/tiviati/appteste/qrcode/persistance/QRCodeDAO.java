package br.com.tiviati.appteste.qrcode.persistance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.tiviati.appteste.qrcode.beans.QRCodeBean;
import br.com.tiviati.appteste.qrcode.util.LogUtil;

/**
 * Created by Antonio Murakami on 17/07/2016.
 */
public class QRCodeDAO extends AbstractDAO<QRCodeBean> {

    private static final String TABLE = "qr_code";
    private static final String[] ALL_COLUMNS = {"qrcode", "latitude", "longitude", "register_date"};
    private static final String ORDER_COLUMN = ALL_COLUMNS[3];

    public QRCodeDAO(Context context) {
        super(context);
    }

    @Override
    public QRCodeBean gravar(QRCodeBean bean) {
        ContentValues cv = new ContentValues();
        cv.put(ALL_COLUMNS[0], bean.getQrcodeData());
        cv.put(ALL_COLUMNS[1], bean.getLatitude());
        cv.put(ALL_COLUMNS[2], bean.getLongitude());
        cv.put(ALL_COLUMNS[3], bean.getDataCriacao().getTime());
        //
        dbUtils.getDatabase().insert(TABLE, null, cv);
        //
        return bean;
    }

    @Override
    public boolean remove(QRCodeBean bean) {
        int quantidadeRegistros = -1;
        try {
            quantidadeRegistros = dbUtils.getDatabase().delete(TABLE, "qrcode="+bean.getQrcodeData(), null);
        } catch (Exception e) {
            LogUtil.error(e.getMessage());
        }
        return quantidadeRegistros>0;
    }

    @Override
    public int removeAll(QRCodeBean bean) {
        int quantidade = dbUtils.getDatabase().delete(TABLE, null, null);
        return quantidade;
    }

    @Override
    public QRCodeBean findByName(String name) {
        try {
            Cursor cursor = dbUtils.getDatabase().query(TABLE, ALL_COLUMNS, "qrcode=?", new String[] {name}, null, null, null);
            if (cursor.moveToFirst()) {
                return getBeanFrom(cursor);
            }
        } catch (Exception e) {
            LogUtil.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<QRCodeBean> listAll() {
        List<QRCodeBean> list = new ArrayList<QRCodeBean>();
        try {
            Cursor cursor = dbUtils.getDatabase().query(TABLE, ALL_COLUMNS, null, null, null, null, ORDER_COLUMN);
            if (cursor.moveToFirst()) {
                do {
                    list.add( getBeanFrom(cursor) );
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            LogUtil.error(e.getMessage(), e);
        }
        return list;
    }

    /**
     * Metodo utilizado para facilitar o parse de informações no modelo Relacional para Objeto
     */
    private QRCodeBean getBeanFrom(Cursor cursor) {
        QRCodeBean bean = new QRCodeBean();
        bean.setQrcodeData(cursor.getString(0));
        bean.setLatitude(cursor.getDouble(1));
        bean.setLongitude(cursor.getDouble(2));
        bean.setDataCriacao( new Date(cursor.getLong(3)) );
        //
        return bean;
    }
}
