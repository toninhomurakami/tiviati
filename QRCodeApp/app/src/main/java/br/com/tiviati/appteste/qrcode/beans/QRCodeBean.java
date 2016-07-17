package br.com.tiviati.appteste.qrcode.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Antonio Murakami on 17/07/2016.
 */
public class QRCodeBean implements Serializable {

    private String qrcodeData;
    private double latitude;
    private double longitude;
    private Date dataCriacao;

    public String getQrcodeData() {
        return qrcodeData;
    }

    public void setQrcodeData(String qrcodeData) {
        this.qrcodeData = qrcodeData;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public String toString() {
        return getQrcodeData()+", "+getLatitude()+", "+getLongitude();
    }
}
