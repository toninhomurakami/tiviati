package br.com.tiviati.appteste.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.zxing.Result;

import br.com.tiviati.appteste.qrcode.facade.QRCodeScannerFacade;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Antonio Murakami on 17/07/2016.
 */
public class ScanActivity extends Activity implements ZXingScannerView.ResultHandler {

    private QRCodeScannerFacade qrFacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        qrFacade = new QRCodeScannerFacade(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        qrFacade.openScanner(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        qrFacade.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        Intent intent = new Intent();
        intent.putExtra("qrdata", result.getText());
        setResult(RESULT_OK, intent);
        //
        finish();
    }
}
