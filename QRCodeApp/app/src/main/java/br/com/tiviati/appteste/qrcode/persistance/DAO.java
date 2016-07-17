package br.com.tiviati.appteste.qrcode.persistance;

import java.util.List;

/**
 * Componente criado para estabelecer contrato com BD 
 * Created by Antonio Murakami on 17/07/2016.
 */
public interface DAO<T> {
	
	T gravar(T bean);
	
	boolean remove(T bean);
	
	int removeAll(T bean);

	T findByName(String name);
	
	List<T> listAll();
	
	void fechar();
}
