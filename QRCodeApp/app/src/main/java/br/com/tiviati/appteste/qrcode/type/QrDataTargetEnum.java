package br.com.tiviati.appteste.qrcode.type;

import static org.apache.commons.lang3.StringUtils.trimToEmpty;

/**
 * Created by Antonio Murakami on 17/07/2016.
 */
public enum  QrDataTargetEnum {
    ENDERECO_WEB,
    IMAGEM,
    GOOGLE_PLAY;

    public static QrDataTargetEnum getQrDataTargetEnum(String data) {
        if (data==null) {
            return null;
        }
        data = trimToEmpty(data.toLowerCase());
        if (data.startsWith("market://"))
            return GOOGLE_PLAY;
        else if (data.endsWith(".png") ||
                 data.endsWith(".jpg") ||
                 data.endsWith(".jpeg"))
            return IMAGEM;
        else if (data.startsWith("http")) {
            return ENDERECO_WEB;
        }
        return null;
    }
}
