package br.com.tiviati.appteste.qrcode.persistance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import br.com.tiviati.appteste.qrcode.util.LogUtil;

/**
 * Componente criado para interfacear Aplicação com BD
 * Created by Antonio Murakami on 17/07/2016.
 */
public class DBUtils {
	
	private SQLiteHelper sqLiteHelper;
	private SQLiteDatabase database;
	
	/**
	 * Padrão Singleton
	 */
	private static final DBUtils instance = new DBUtils();
	private DBUtils() { }
	public static DBUtils getInstance() {
		return instance;
	}
	
	/**
	 * Método utilizado para recuperar conexão com o BD.
	 * Tem visibilidade menor para ser utilizado por componentes DAO
	 * @return
	 */
	SQLiteDatabase getDatabase() {
		if (database == null) {
			database = sqLiteHelper.getWritableDatabase();
		}
		if (!database.isOpen()) {
			LogUtil.warn("Abrindo BD com estrategia alternativa...");
			database = sqLiteHelper.getSQLiteDatabaseBySystem();
			LogUtil.warn("...BD aberto com sucesso.");
		}
		return database;
	}
	
	/**
	 * Este método serve para preparação do componente de persistencia.
	 * Deve ser invocado uma vez, na inicialização do aplicativo
	 * @param context
	 */
	public void setContext(Context context) {
		if (sqLiteHelper == null) {
			LogUtil.info("Carregando BD...");
			sqLiteHelper = new SQLiteHelper(context);
			LogUtil.info("...BD carregado com sucesso.");
		}
	}
	
	/**
	 * Método utilizado para encerrar a comunicaçãDAO.javao com o BD
	 */
	public void fechar() {
		if (database != null && database.isOpen()) {
			LogUtil.info("Encerrando BD...");
			database.close();
			LogUtil.info("...BD encerrado com sucesso.");
		}
	}
}
