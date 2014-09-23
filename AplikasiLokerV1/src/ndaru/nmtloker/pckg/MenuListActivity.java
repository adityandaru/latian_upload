package ndaru.nmtloker.pckg;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;


import android.annotation.SuppressLint;
//import android.R.array;
//import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.graphics.Color;
import android.os.Bundle;
//import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
//import android.widget.TableLayout;
//import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MenuListActivity extends ListActivity{
	
	ArrayList<HashMap<String, String>>daftar_data = new ArrayList<HashMap<String,String>>();
	private Button button_refresh;
	private Intent inte = null;
	private OperasiDatabase od = null;	
	private SQLiteDatabase db = null;
	private Cursor cursor = null;
	public static int jml_barisMenulist = 0;
	public static String [][] data_Menulist;
	
	private String MenuListActivity_TABLE_NAME = OperasiDatabase.TABLE_NAME;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main1);
		
		button_refresh = (Button)findViewById(R.id.button1);
		button_refresh.setText("Pembaruan Lowongan");
		
		try {
			//messageDialog("MenuListActivity jml_barisMenulist "+Integer.toString(DataOffline.jml_barisMenulist));
			MenuListActivity_GetSqlite_Menulist();
			MenuListActivity_SetData_Menulist();
			MenuListActivity_adapter_listview();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		
		
		button_refresh.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					
				//	messageDialog("MenuListActivity jml_barisMenulist button "+Integer.toString(DataOffline.jml_barisMenulist));
					MenuListActivity_GetSqlite_Menulist();
					MenuListActivity_SetData_Menulist();
					MenuListActivity_adapter_listview();
					
					Intent DownloadData_myIntent = null;
					DownloadData_myIntent = new Intent(MenuListActivity.this, DownloadData.class);			
					startActivity(DownloadData_myIntent);
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
		
	}
	public void saas(String l){
		messageDialog("Tidak adaux respon dari server! "+l);
	}
	
	public void MenuListActivity_GetSqlite_Menulist(){
		
		//sqllite
		od = new OperasiDatabase(this);
		db = od.getWritableDatabase();
		od.CreateTable(db);
		
		cursor = od.SelectData(db, "SELECT nama, id, jenis_perusahaan, rincian_pekerjaan, awal_ditayangkan, akhir_ditayangkan "+
							"FROM  "+ MenuListActivity_TABLE_NAME);
		cursor.moveToFirst();
		
		jml_barisMenulist = cursor.getCount();
		//messageDialog("jumlah baris offline "+Integer.toString(jml_barisMenulist));
		
		
		if (jml_barisMenulist == 0 ) return;
		
		int kol_nama = cursor.getColumnIndex("nama");
		int kol_id = cursor.getColumnIndex("id");
		int kol_jenis_perusahaan = cursor.getColumnIndex("jenis_perusahaan");
		int kol_awal_ditayangkan = cursor.getColumnIndex("awal_ditayangkan");
		int kol_akhir_ditayangkan = cursor.getColumnIndex("akhir_ditayangkan");
		int kol_rincian_pekerjaan = cursor.getColumnIndex("rincian_pekerjaan");

		int indeks = 1;
		
		data_Menulist = new String[jml_barisMenulist][7];
		data_Menulist[0][0] = cursor.getString(kol_id);
		data_Menulist[0][1] = cursor.getString(kol_nama);	
		data_Menulist[0][2] = cursor.getString(kol_jenis_perusahaan);	
		data_Menulist[0][3] = cursor.getString(kol_awal_ditayangkan);
		data_Menulist[0][4] = cursor.getString(kol_akhir_ditayangkan);
		data_Menulist[0][5] = cursor.getString(kol_rincian_pekerjaan).substring(0,100)+"...(baca selengkapnya)";
		
		if (cursor != null) {
			while (cursor.moveToNext()) {
				data_Menulist[indeks][0] = cursor.getString(kol_id);
				data_Menulist[indeks][1] = cursor.getString(kol_nama);				
				data_Menulist[indeks][2] = cursor.getString(kol_jenis_perusahaan);				
				data_Menulist[indeks][3] = cursor.getString(kol_awal_ditayangkan);		
				data_Menulist[indeks][4] = cursor.getString(kol_akhir_ditayangkan);	
				data_Menulist[indeks][5] = cursor.getString(kol_rincian_pekerjaan).substring(0,100)+"...(baca selengkapnya)";
								
				indeks++;
				
			}
		}
		
	}
	
	
	public void MenuListActivity_SetData_Menulist(){
		
	//	int jml_Menulist = DataOffline.jml_barisMenulist;		
		//String [][] dataMenulist = DataOffline.data_Menulist;
	//	messageDialog("jml_Menulist : "+jml_Menulist);
		//messageDialog("dataMenulist : "+dataMenulist.length);
		
		daftar_data.removeAll(daftar_data);
		
		for (int i = 0; i < jml_barisMenulist; i++) {
			HashMap<String, String>map = new HashMap<String, String>();
			map.put("id", data_Menulist[i][0]);
			map.put("nama", "Nama Instansi : "+data_Menulist[i][1]);			
			map.put("jenis_perusahaan", "Katagori : "+data_Menulist[i][2]);	
			map.put("rincian_pekerjaan", data_Menulist[i][5]);
			map.put("awal_ditayangkan", "Lowongan Mulai Tanggal : "+data_Menulist[i][3] +" S/D "+data_Menulist[i][4]);
						
			daftar_data.add(map);
		}
		
		
	}
	
	public void MenuListActivity_adapter_listview(){
		
		
		ListAdapter adapter = new SimpleAdapter(this, daftar_data,
				R.layout.list_item1,
				new String[] { "id", "nama", "jenis_perusahaan","rincian_pekerjaan","awal_ditayangkan"}, 
				new int[] {R.id.textView_id, R.id.textView_nama, R.id.textView_jenis_perusahaan, R.id.textView_rincian_pekerjaan,R.id.textView_awal_ditayangkan});
		setListAdapter(adapter);
		
		ListView lv = getListView();
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String id_l = ((TextView)view.findViewById(R.id.textView_id)).getText().toString();
				//messageDialog("id : "+id_l);
				//String id_l2 = ((TextView)view.findViewById(R.id.textView2_nama)).getText().toString();
				//messageDialog("nama : "+id_l2);
				
				inte = new Intent(MenuListActivity.this, TabLayout1Activity.class);
				inte.putExtra("id_intent", id_l);
				startActivity(inte);
			}
			
		});
		
	}
	
	@SuppressWarnings({ "deprecation", "unused" })
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
