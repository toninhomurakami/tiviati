package br.com.tiviati.appteste.qrcode.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;

import br.com.tiviati.appteste.qrcode.R;

/**
 * Created by Antonio Murakami on 17/07/2016.
 */
public class GpsDialog {

    private Context context;

    public GpsDialog(Context context) {
        this.context = context;
    }

    public void show() {
        AlertDialog.Builder confirmacao = new AlertDialog.Builder(context);
        confirmacao.setTitle(context.getString(R.string.msg_dialog_titulo));
        confirmacao.setMessage(context.getString(R.string.msg_gps_inativo));
        //
        confirmacao.setPositiveButton(context.getResources().getString(R.string.msg_dialog_sim), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS );
                context.startActivity(intent);
            }
        });
        //
        confirmacao.setNegativeButton(context.getString(R.string.msg_dialog_nao), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ((Activity)context).finish();
            }
        });
        confirmacao.show();
    }
}
