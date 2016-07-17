/**
 * 
 */
package br.com.tiviati.appteste.qrcode.persistance;

import android.content.Context;
import br.com.tiviati.appteste.qrcode.beans.QRCodeBean;

/**
 * Componente criado para auxiliar criação de instancias de DAO
 * Created by Antonio Murakami on 17/07/2016.
 */
public class FactoryDAO {
	
	private Context context;
	
	private DAO<QRCodeBean> qrcodeDAO;
	
	private static final FactoryDAO instance = new FactoryDAO();
	private FactoryDAO() {}
	public static FactoryDAO getInstance(Context context) {
		if (instance.context==null) {
			instance.context = context;
			DBUtils.getInstance().setContext(context);
		}
		return instance;
	}
	
	public DAO<QRCodeBean> getQRCodeDAO() {
		if (qrcodeDAO == null) {
			qrcodeDAO = new QRCodeDAO(context);
		}
		return qrcodeDAO;
	}
	
}
