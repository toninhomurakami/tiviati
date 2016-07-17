package br.com.tiviati.appteste.qrcode.persistance;

import android.content.Context;

/**
 * Componente criado para oferecer pre-implementação para classes de persistencia
 * Created by Antonio Murakami on 17/07/2016.
 *
 */
public abstract class AbstractDAO<T> implements DAO<T> {
	protected DBUtils dbUtils;
	protected Context context;
	
	public AbstractDAO(Context context) {
		this.context = context;
		dbUtils = DBUtils.getInstance();
	}
	
	public void fechar() {
		dbUtils.fechar();
	}
}
