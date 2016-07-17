package br.com.tiviati.appteste.qrcode.facade;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Componente utilizado para interfacear comunicação com GPS
 * Created by Antonio Murakami on 17/07/2016.
 */
public class GpsFacade implements LocationListener {

	private Context context;
	private LocationManager locationManager;

	public GpsFacade(Context ctx) {
		this.context = ctx;
		locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
	}

	public void setGpsTrackingEnabled(boolean activated) {
		if (isGpsPresent()) {
			locationManager.removeUpdates(this);
			if (isGpsEnabled() && activated) {
				locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, GpsFacade.this);
			}
		}
	}

    /*
     * Utilizando esta estratégia para inicializar GPS por Satelite previamente e
     * recuperar localização com melhor precisão
     */
    public Location retriveLastLocation() {
        return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }

	public void onLocationChanged(Location location) { }
	public void onProviderDisabled(String provider) { }
	public void onProviderEnabled(String provider) { }
	public void onStatusChanged(String provider, int status, Bundle extras) { }
	
	public boolean isGpsEnabled() {
		return isGpsPresent() && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}

	/**
	 * Metodo utilizado para saber se há GPS integrado ao device.
	 *
	 */
	public boolean isGpsPresent() {
		return locationManager != null;
	}

}
