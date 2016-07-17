package br.com.tiviati.appteste.qrcode.util;

import android.util.Log;

import static org.apache.commons.lang3.StringUtils.*;

/**
 * Componente criado para controlar log de App.
 * Created by Antonio Murakami on 17/07/2016.
 */
public class LogUtil {
    /* Este atributo deve ser alterado para False, afim de evitar que análise do aplicativo
     * por pessoas não autorizadas
     */
    private static final boolean ACTIVE = true;

    private static final String LOG_APPLICATION_NAME = "logQrcodeApp";

    public static void debug(String message) {
        if (ACTIVE) {
            Log.d(LOG_APPLICATION_NAME, trimToEmpty(message));
        }
    }

    public static void info(String message) {
        if (ACTIVE) {
            Log.i(LOG_APPLICATION_NAME, trimToEmpty(message));
        }
    }

    public static void warn(String message) {
        if (ACTIVE) {
            Log.w(LOG_APPLICATION_NAME, trimToEmpty(message));
        }
    }

    public static void error(String message) {
        if (ACTIVE) {
            Log.e(LOG_APPLICATION_NAME, trimToEmpty(message));
        }
    }

    public static void error(String message, Throwable t) {
        if (ACTIVE) {
            Log.e(LOG_APPLICATION_NAME, trimToEmpty(message), t);
        }
    }
}
