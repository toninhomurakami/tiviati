package br.com.tiviati.appteste.qrcode;


import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import java.util.Date;
import java.util.List;

import br.com.tiviati.appteste.qrcode.beans.QRCodeBean;
import br.com.tiviati.appteste.qrcode.dialog.GpsDialog;
import br.com.tiviati.appteste.qrcode.facade.GpsFacade;
import br.com.tiviati.appteste.qrcode.model.QRListModel;
import br.com.tiviati.appteste.qrcode.persistance.DAO;
import br.com.tiviati.appteste.qrcode.persistance.FactoryDAO;
import br.com.tiviati.appteste.qrcode.type.QrDataTargetEnum;

/**
 * Created by Antonio Murakami on 17/07/2016.
 */
public class MainActivity extends Activity {

    private static final int SCAN_ACTIVITY = 112;

    private GpsFacade gpsFacade;

    private DAO<QRCodeBean> qrDAO;
    private ListView listView;
    private QRListModel listModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents() {
        gpsFacade = new GpsFacade(this);
        qrDAO = FactoryDAO.getInstance(this).getQRCodeDAO();
        //
        List<QRCodeBean> qrCodeList = qrDAO.listAll();
        listModel = new QRListModel(this);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(listModel.create(qrCodeList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                QRCodeBean bean = listModel.getItem(position);
                QrDataTargetEnum qrTarget = QrDataTargetEnum.getQrDataTargetEnum(bean.getQrcodeData());
                if (qrTarget==null) {
                    Toast.makeText(MainActivity.this, R.string.msg_qr_target_nao_suportado, Toast.LENGTH_SHORT).show();
                    return;
                }
                switch (qrTarget) {
                    case ENDERECO_WEB:
                    case GOOGLE_PLAY:
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(bean.getQrcodeData())));
                        } catch (Exception e) {
                            String mensagemErro = String.format(getString(R.string.msg_qr_target_erro), bean.getQrcodeData());
                            Toast.makeText(MainActivity.this, mensagemErro, Toast.LENGTH_LONG).show();
                        }
                        break;
                    case IMAGEM:
                        Toast.makeText(MainActivity.this, R.string.msg_qr_target_imagem_nao_suportado, Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!gpsFacade.isGpsEnabled()) {
            new GpsDialog(this).show();
        } else {
            gpsFacade.setGpsTrackingEnabled(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        gpsFacade.setGpsTrackingEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_qrcode:
                doQRCodeAction();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void doQRCodeAction() {
        if (gpsFacade.retriveLastLocation()==null) {
            Toast.makeText(this, R.string.msg_gps_inicializando, Toast.LENGTH_SHORT).show();
        } else {
            Intent qrScanIntent = new Intent(this, ScanActivity.class);
            startActivityForResult(qrScanIntent, SCAN_ACTIVITY);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case SCAN_ACTIVITY:
                if (RESULT_OK==resultCode) {
                    processScanResult(data);
                }
                break;
        }
    }

    private void processScanResult(Intent data) {
        String qrData = data.getStringExtra("qrdata");
        QRCodeBean bean = qrDAO.findByName(qrData);
        if (bean==null) {
            bean = new QRCodeBean();
            Location location = gpsFacade.retriveLastLocation();
            bean.setQrcodeData(qrData);
            bean.setLatitude(location.getLatitude());
            bean.setLongitude(location.getLongitude());
            bean.setDataCriacao(new Date());
            qrDAO.gravar(bean);
            listModel.add(bean);
        }
    }

}
