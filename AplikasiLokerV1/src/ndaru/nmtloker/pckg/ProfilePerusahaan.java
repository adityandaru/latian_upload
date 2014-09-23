package ndaru.nmtloker.pckg;

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

public class ProfilePerusahaan extends Activity{
	
	private OperasiDatabase od = null;	
	private SQLiteDatabase db = null;
	private Cursor cursor = null;
	private TextView text_deskripsi;
	private TextView text_nama;
	private TextView text_jenis_perusahaan;
	private TextView text_nomer_telepon;
	private TextView text_nomer_hp;
	private TextView text_email;
	private TextView text_alamat;
	private Button button_kembali;
	
	public static int jml_barisProfilePerusahaan = 0;
	public static String [][] data_ProfilePerusahaan;
	private String ProfilePerusahaan_TABLE_NAME = OperasiDatabase.TABLE_NAME;
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_profilelowongan);
			//messageDialog("test message dialaog activity_profilelowongan  ");
			
			od = new OperasiDatabase(this);
			db = od.getWritableDatabase();
			od.CreateTable(db);
			ProfilePerusahaan_setData();
			button_kembali = (Button)findViewById(R.id.button_kembali);
			button_kembali.setText("<< Kembali");
			button_kembali.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent MenuListActivity_myIntent = null;
					MenuListActivity_myIntent = new Intent(ProfilePerusahaan.this, MenuListActivity.class);			
					startActivity(MenuListActivity_myIntent);
				}
			});
		}
		
		public void ProfilePerusahaan_setData(){
			
			Intent intent = getIntent();
			String id_Perusahaan = intent.getStringExtra("id_ProfilePerusahaan");
			
			cursor = od.SelectData(db, "SELECT deskripsi, nama, jenis_perusahaan, nomer_telepon, nomer_hp, email, alamat "+
								" FROM  "+ ProfilePerusahaan_TABLE_NAME +" WHERE id = "+id_Perusahaan);
			cursor.moveToFirst();
			
			jml_barisProfilePerusahaan = cursor.getCount();
			//messageDialog("jumlah baris offline "+Integer.toString(jml_barisMenulist));
			
			
			if (jml_barisProfilePerusahaan == 0 ) return;
			
			int kol_deskripsi = cursor.getColumnIndex("deskripsi");
			int kol_nama = cursor.getColumnIndex("nama");
			int kol_jenis_perusahaan = cursor.getColumnIndex("jenis_perusahaan");
			int kol_nomer_telepon = cursor.getColumnIndex("nomer_telepon");
			int kol_nomer_hp = cursor.getColumnIndex("nomer_hp");
			int kol_email = cursor.getColumnIndex("email");
			int kol_alamat = cursor.getColumnIndex("alamat");
			int indeks = 1;
			
			data_ProfilePerusahaan = new String[jml_barisProfilePerusahaan][7];
			data_ProfilePerusahaan[0][0] = cursor.getString(kol_deskripsi);
			data_ProfilePerusahaan[0][1] = cursor.getString(kol_nama);	
			data_ProfilePerusahaan[0][2] = cursor.getString(kol_jenis_perusahaan);	
			data_ProfilePerusahaan[0][3] = cursor.getString(kol_nomer_telepon);
			data_ProfilePerusahaan[0][4] = cursor.getString(kol_nomer_hp);
			data_ProfilePerusahaan[0][5] = cursor.getString(kol_email);
			data_ProfilePerusahaan[0][6] = cursor.getString(kol_alamat);
			
			
			text_deskripsi = (TextView)findViewById(R.id.textView_deskripsi);
			text_deskripsi.setText(data_ProfilePerusahaan[0][0]);
			text_nama = (TextView)findViewById(R.id.textView_nama);
			text_nama.setText("Perusahaan :"+data_ProfilePerusahaan[0][1]);
			text_jenis_perusahaan = (TextView)findViewById(R.id.textView_jenis_perusahaan);
			text_jenis_perusahaan.setText("Jenis Usaha : " +data_ProfilePerusahaan[0][2]);
			text_nomer_telepon = (TextView)findViewById(R.id.textView_nomer_telepon);
			text_nomer_telepon.setText("Telepon :" +data_ProfilePerusahaan[0][3]);
			text_nomer_hp = (TextView)findViewById(R.id.textView_nomer_hp);
			text_nomer_hp.setText("Hp :" +data_ProfilePerusahaan[0][4]);
			text_email = (TextView)findViewById(R.id.textView_email);
			text_email.setText("Email :" +data_ProfilePerusahaan[0][5]);
			text_alamat = (TextView)findViewById(R.id.textView_alamat);
			text_alamat.setText("Alamat :" +data_ProfilePerusahaan[0][6]);
			
			
			
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
