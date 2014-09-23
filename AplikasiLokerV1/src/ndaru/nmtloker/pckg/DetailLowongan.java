package ndaru.nmtloker.pckg;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailLowongan extends Activity{
	
	private OperasiInternet oi;	
	private JSONObject json = null;
	private TextView text_rincian_pekerjaan;
	private TextView text_fungsi_kerja;
	private TextView text_pendidikan_minimal;
	private TextView text_awal_ditayangkan;
	private TextView text_akhir_ditayangkan;
	private Button button_kembali;
	
	
	private OperasiDatabase od = null;	
	private SQLiteDatabase db = null;
	private Cursor cursor = null;
	public static int jml_barisDetailLowongan = 0;
	public static String [][] data_DetailLowongan;
	
	private String DetailLowongan_TABLE_NAME = OperasiDatabase.TABLE_NAME;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_detailslowongan2);	
			
			//messageDialog("test message dialaog DetailLowongan  ");
			
			od = new OperasiDatabase(this);
			db = od.getWritableDatabase();
			od.CreateTable(db);
			DetailLowongan_setData();
			
			button_kembali = (Button)findViewById(R.id.button_kembali);
			button_kembali.setText("<< Kembali");
			button_kembali.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent MenuListActivity_myIntent = null;
					MenuListActivity_myIntent = new Intent(DetailLowongan.this, MenuListActivity.class);			
					startActivity(MenuListActivity_myIntent);
				}
			});
			
		}
		
		public void DetailLowongan_setData(){
			
			Intent intent = getIntent();
			String id_lowongan = intent.getStringExtra("id_DetailLowongan");
			
			cursor = od.SelectData(db, "SELECT rincian_pekerjaan, fungsi_kerja, pendidikan_minimal, awal_ditayangkan, akhir_ditayangkan "+
								" FROM  "+ DetailLowongan_TABLE_NAME +" WHERE id = "+id_lowongan);
			cursor.moveToFirst();
			
			jml_barisDetailLowongan = cursor.getCount();
			//messageDialog("jumlah baris offline "+Integer.toString(jml_barisMenulist));
			
			
			if (jml_barisDetailLowongan == 0 ) return;
			
			int kol_rincian_pekerjaan = cursor.getColumnIndex("rincian_pekerjaan");
			int kol_fungsi_kerja = cursor.getColumnIndex("fungsi_kerja");
			int kol_pendidikan_minimal = cursor.getColumnIndex("pendidikan_minimal");
			int kol_awal_ditayangkan = cursor.getColumnIndex("awal_ditayangkan");
			int kol_akhir_ditayangkan = cursor.getColumnIndex("akhir_ditayangkan");

			int indeks = 1;
			
			data_DetailLowongan = new String[jml_barisDetailLowongan][5];
			data_DetailLowongan[0][0] = cursor.getString(kol_rincian_pekerjaan);
			data_DetailLowongan[0][1] = cursor.getString(kol_fungsi_kerja);	
			data_DetailLowongan[0][2] = cursor.getString(kol_pendidikan_minimal);	
			data_DetailLowongan[0][3] = cursor.getString(kol_awal_ditayangkan);
			data_DetailLowongan[0][4] = cursor.getString(kol_akhir_ditayangkan);
			
			
			
			
			text_fungsi_kerja = (TextView)findViewById(R.id.textView_fungsi_kerja);
			text_fungsi_kerja.setText("fungsi kerja :"+data_DetailLowongan[0][1]);
			
			text_pendidikan_minimal = (TextView)findViewById(R.id.textView_pendidikan_minimal);
			text_pendidikan_minimal.setText("minimal pendidikan : " +data_DetailLowongan[0][2]);
			
			text_awal_ditayangkan = (TextView)findViewById(R.id.textView_awal_ditayangkan);
			text_awal_ditayangkan.setText("mulai tanggal :" +data_DetailLowongan[0][3]);
			
			text_akhir_ditayangkan = (TextView)findViewById(R.id.textView_akhir_ditayangkan);
			text_akhir_ditayangkan.setText("sampai tanggal :" +data_DetailLowongan[0][4]);
			
			text_rincian_pekerjaan = (TextView)findViewById(R.id.textView_rincian_pekerjaan);
			text_rincian_pekerjaan.setText(data_DetailLowongan[0][0]);
		}
		
		private void messageDialog(String pesan){
			AlertDialog alertDialog = new AlertDialog.Builder(this).create();
			alertDialog.setTitle("Alert Dialog");
			alertDialog.setMessage(pesan);
			alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			alertDialog.show();
		}
}
