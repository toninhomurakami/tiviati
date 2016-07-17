package br.com.tiviati.appteste.qrcode.facade;

import android.app.Activity;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Antonio Murakami on 17/07/2016.
 */
public class QRCodeScannerFacade {

    private ZXingScannerView scannerView;
    private Activity activity;

    public QRCodeScannerFacade(Activity activity) {
        this.activity = activity;
    }

    public void openScanner(ZXingScannerView.ResultHandler resultHandler) {
        scannerView = new ZXingScannerView(activity);
        activity.setContentView(scannerView);

        scannerView.setResultHandler(resultHandler);
        scannerView.startCamera();
    }

    public void stopCamera() {
        if (scannerView!=null) {
            scannerView.stopCamera();
        }
    }
}
