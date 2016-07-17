package br.com.tiviati.appteste.qrcode.persistance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.tiviati.appteste.qrcode.R;
import br.com.tiviati.appteste.qrcode.util.LogUtil;

/**
 * Componente criado para facilitar criação/atualização de BD com SqLite
 * Created by Antonio Murakami on 17/07/2016.
 */
class SQLiteHelper extends SQLiteOpenHelper {

	private Context context;

	/**
	 * Cria uma instancia de SQLiteHelper
	 * 
	 * @param context
	 */
	SQLiteHelper(Context context) {
		super(context, context.getString(R.string.db_name), null, Integer.parseInt(context.getString(R.string.db_md_version)));
		//
		this.context = context;
	}
	
	SQLiteDatabase getSQLiteDatabaseBySystem() {
		return context.openOrCreateDatabase(context.getString(R.string.db_name), SQLiteDatabase.OPEN_READWRITE, null);
	}
	
	/**
	 * Criação de um novo BD
	 */
	public void onCreate(SQLiteDatabase db) {
		LogUtil.info(context.getResources().getString(R.string.log_create_start));
		String[] scriptSQLCreate = context.getResources().getStringArray(R.array.db_scripts_create);
		for (String sql: scriptSQLCreate) {
			LogUtil.info(sql);
			db.execSQL(sql);
		}
		LogUtil.info(context.getResources().getString(R.string.log_create_end));
	}

	/**
	 * Atualização de versção
	 */
	public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
		LogUtil.warn(context.getResources().getString(R.string.log_update_warn)+" old: "+versaoAntiga+" new: "+novaVersao);
		String [] scriptSQLDelete = context.getResources().getStringArray(R.array.db_scripts_drop);
		for (String sql: scriptSQLDelete) {
			LogUtil.info(sql);
			db.execSQL(sql);
		}
		onCreate(db);
	}
	
}