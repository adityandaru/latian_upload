package ndaru.nmtloker.pckg;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class DownloadData extends Activity {	
	
	private static final String AR_id		      			= "id";
	//profil TABLE_NAME1 
	//private static final String AR_id_profil      			= "id";
	private static final String AR_nama_profil   			= "nama";
	private static final String AR_deskripsi_profil   		= "deskripsi";
	private static final String AR_alamat_profil 			= "alamat";
	private static final String AR_nomer_telepon_profil    	= "nomer_telepon";
	private static final String AR_nomer_reg_bisnis_profil 	= "nomer_reg_bisnis";	
	private static final String AR_jenis_perusahaan_profil 	= "jenis_perusahaan";	
	
	
	//lowongan TABLE_NAME2
	//private static final String AR_id_lowongan      			= "id";
	private static final String AR_kode_perusahaan_lowongan   	= "kode_perusahaan";
	private static final String AR_jabatan_lowongan  			= "jabatan";
	private static final String AR_fungsi_kerja_lowongan 		= "fungsi_kerja";
	private static final String AR_rincian_pekerjaan_lowongan   = "rincian_pekerjaan";
	private static final String AR_pendidikan_minimal_lowongan 	= "pendidikan_minimal";	
	private static final String AR_awal_ditayangkan_lowongan 	= "awal_ditayangkan";	
	private static final String AR_akhir_ditayangkan_lowongan 	= "akhir_ditayangkan";	
	
	//kontak TABLE_NAME3
//	private static final String AR_id_kontak      			= "id";
	private static final String AR_email_kontak 			= "email";
	private static final String AR_nomer_hp_kontak   		= "nomer_hp";
	
	private OperasiDatabase od = null;	
	private SQLiteDatabase db = null;	
	private JSONObject json = null;
	private OperasiInternet oi;		
	
	@SuppressLint("ShowToast") @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//sqllite
				od = new OperasiDatabase(this);
				db = od.getWritableDatabase();
				od.CreateTable(db);
		
		OperasiInternet oi = new OperasiInternet(this);
        if(oi.CheckInternetConnection())
        	Toast.makeText(this, "Koneksi Internet OK", 500).show();        	
        else
        	Toast.makeText(this, "Koneksi internet tidak tersedia\n" +
        			"Silahkan aktifkan koneksi internet anda", 500).show();
        
        
        try {
			DownloadData_GetData_TABLE_NAME();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
       
		
	}
	
	
	
	private JSONObject  DownloadData_GetData_TABLE_NAME()throws InterruptedException{
		JSONObject jsonToServer = new JSONObject();
		
		try {
		
			jsonToServer.put("status", "ambildata_tabel_jc");			
		} catch (JSONException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		oi = new OperasiInternet(this);
		if (oi.CheckInternetConnection()) {
			oi.SetJSON(jsonToServer);
			oi.execute();
			oi.setOnPostExecuteListener(
					new OperasiInternet.OnPostExecuteListener() {
						
						@SuppressLint("NewApi")
						@Override
						public void OnPostExecute() {
							// TODO Auto-generated method stub
							try {
								if (!oi.GetText().isEmpty()) {										
									json = oi.GetJSON();	
									DownloadData_SetDataSqlite_TABLE_NAME(json);
								} else {
									messageDialog("Tidak ada respon dari server!");
								}
								
							} catch (InterruptedException e) {
								// TODO: handle exception
								e.printStackTrace();
							}
						}
					}
			);
					
		} 
		return json;
	}


	public void DownloadData_SetDataSqlite_TABLE_NAME(JSONObject json){
		
		
		try {
			
			
			JSONArray jsonarray = json.getJSONArray("tables");
					
			int jml_baris = jsonarray.length();
			//messageDialog("jumlah baris tabel_jc_lowongan = "+Integer.toString(jml_baris));
					
			//sqllite		
			od.DeleteTABLE_NAME(db);
			for (int i = 0; i < jml_baris; i++) {
				JSONObject jsonobj = jsonarray.getJSONObject(i);								
				//sqllite					       
				String [] setDataTABLE_NAME = new String [] {
						jsonobj.getString(AR_id),
						
						jsonobj.getString(AR_nama_profil),
						jsonobj.getString(AR_deskripsi_profil),
						jsonobj.getString(AR_alamat_profil),
						jsonobj.getString(AR_nomer_telepon_profil),
						jsonobj.getString(AR_nomer_reg_bisnis_profil),
						jsonobj.getString(AR_jenis_perusahaan_profil),
						
						jsonobj.getString(AR_kode_perusahaan_lowongan),
						jsonobj.getString(AR_jabatan_lowongan),
						jsonobj.getString(AR_fungsi_kerja_lowongan),
						jsonobj.getString(AR_rincian_pekerjaan_lowongan),
						jsonobj.getString(AR_pendidikan_minimal_lowongan),
						jsonobj.getString(AR_awal_ditayangkan_lowongan),
						jsonobj.getString(AR_akhir_ditayangkan_lowongan),
						
						jsonobj.getString(AR_email_kontak),
						jsonobj.getString(AR_nomer_hp_kontak) };
				//messageDialog("InsertDataTABLE_NAME3 "+i);
				od.InsertDataTABLE_NAME(db, setDataTABLE_NAME);
			}
			
			Intent MenuListActivity_myIntent = null;
			MenuListActivity_myIntent = new Intent(this, MenuListActivity.class);			
			startActivity(MenuListActivity_myIntent);
			
		} catch (JSONException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
	}
	
	
	
	public void onDestroy() {  
        super.onDestroy();  
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
