package ndaru.nmtloker.pckg;

import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class DataOffline extends Activity{
	private OperasiDatabase od = null;	
	private SQLiteDatabase db = null;
	private Cursor cursor = null;
	
	private String DataOffline_TABLE_NAME = OperasiDatabase.TABLE_NAME;
	
	//private String DataOffline_TABLE_NAME1 = OperasiDatabase.TABLE_NAME1;
	//private String DataOffline_TABLE_NAME2 = OperasiDatabase.TABLE_NAME2;
	//private String DataOffline_TABLE_NAME3 = OperasiDatabase.TABLE_NAME3;
	
	public static int jml_barisMenulist = 0;
	public static String [][] data_Menulist;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		//sqllite
				od = new OperasiDatabase(this);
				db = od.getWritableDatabase();
				od.CreateTable(db);
				
				//DataOffline_Tampil_Menulist();
				
				DataOffline_Tampil_Menulist();
				//asassdataoffline();
				Intent MenuListActivity_myIntent = null;
				MenuListActivity_myIntent = new Intent(this, MenuListActivity.class);			
				startActivity(MenuListActivity_myIntent);
	}
	
	public void asassdataoffline(){
		messageDialog("asassdataoffline ");
	}
	public void DataOffline_Tampil_Menulist(){
				
		cursor = od.SelectData(db, "SELECT nama, id, jenis_perusahaan,  awal_ditayangkan, akhir_ditayangkan "+
							"FROM  "+ DataOffline_TABLE_NAME);
		cursor.moveToFirst();
		
		jml_barisMenulist = cursor.getCount();
		//messageDialog("jumlah baris offline "+Integer.toString(jml_barisMenulist));
		
		
		if (jml_barisMenulist == 0 ) return;
		
		int kol_nama = cursor.getColumnIndex("nama");
		int kol_id = cursor.getColumnIndex("id");
		int kol_jenis_perusahaan = cursor.getColumnIndex("jenis_perusahaan");
		int kol_awal_ditayangkan = cursor.getColumnIndex("awal_ditayangkan");
		int kol_akhir_ditayangkan = cursor.getColumnIndex("akhir_ditayangkan");

		int indeks = 1;
		
		data_Menulist = new String[jml_barisMenulist][7];
		data_Menulist[0][0] = cursor.getString(kol_id);
		data_Menulist[0][1] = cursor.getString(kol_nama);	
		data_Menulist[0][2] = cursor.getString(kol_jenis_perusahaan);	
		data_Menulist[0][3] = cursor.getString(kol_awal_ditayangkan);
		data_Menulist[0][4] = cursor.getString(kol_akhir_ditayangkan);
		
		if (cursor != null) {
			while (cursor.moveToNext()) {
				data_Menulist[indeks][0] = cursor.getString(kol_id);
				data_Menulist[indeks][1] = cursor.getString(kol_nama);				
				data_Menulist[indeks][2] = cursor.getString(kol_jenis_perusahaan);				
				data_Menulist[indeks][3] = cursor.getString(kol_awal_ditayangkan);		
				data_Menulist[indeks][4] = cursor.getString(kol_akhir_ditayangkan);	
								
				indeks++;
				
			}
		}
		
	}
	
	public void onDestroy() {  
       
        cursor.close();  
        db.close();  
    }
	private void messageDialog(String pesan){
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Alert Dialog");
		alertDialog.setMessage(pesan);
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(final DialogInterface dialog, final int which) {
				// TODO Auto-generated method stub
				
			}
		});
		alertDialog.show();
	}
}
