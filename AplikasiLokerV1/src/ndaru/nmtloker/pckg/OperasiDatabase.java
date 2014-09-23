package ndaru.nmtloker.pckg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OperasiDatabase extends SQLiteOpenHelper{

	private static final int DATABASE_VERSION =1;
	private static final String DATABASE_NAME = "job_bwi";
	public static final String TABLE_NAME = "tabel_jc";
//	public static final String TABLE_NAME1 = "tabel_jc_profil";
//	public static final String TABLE_NAME2 = "tabel_jc_lowongan";
//	public static final String TABLE_NAME3 = "tabel_jc_kontak";
	
	public OperasiDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//db.execSQL("");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		//db.execSQL("");
		
	}
	
	//method createTable untuk membuat table biodata
		public void CreateTable(SQLiteDatabase db){
			//db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
			
			db.execSQL("CREATE TABLE IF NOT EXISTS `" +TABLE_NAME+ "` ("+
	  "`id` int(10) NOT NULL," +
	  "`nama` varchar(128) NOT NULL," +
	  "`deskripsi` text NOT NULL," +
	  "`alamat` varchar(255) NOT NULL," +
	  "`nomer_telepon` varchar(16) NOT NULL," +
	  "`nomer_reg_bisnis` varchar(255) NOT NULL," +
	  "`jenis_perusahaan` varchar(255) NOT NULL," +
	  "`kode_perusahaan` int(10) NOT NULL," +
	  "`jabatan` varchar(64) NOT NULL," +
	  "`fungsi_kerja` text NOT NULL," +
	  "`rincian_pekerjaan` text NOT NULL," +
	  "`pendidikan_minimal` text NOT NULL," +
	  "`awal_ditayangkan` date NOT NULL," +
	  "`akhir_ditayangkan` date NOT NULL," +
	  "`email` varchar(64) NOT NULL," +
	  "`nomer_hp` varchar(16) NOT NULL," +
	  
	  "PRIMARY KEY (`id`));");
				
			
			
		}
		/*
	//method createTable untuk membuat table biodata
	public void CreateTable(SQLiteDatabase db){
		//db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
		
		db.execSQL("CREATE TABLE IF NOT EXISTS `" +TABLE_NAME1+ "` ("+
  "`id` int(10) NOT NULL," +
  "`nama` varchar(128) NOT NULL," +
  "`deskripsi` text NOT NULL," +
  "`alamat` varchar(255) NOT NULL," +
  "`nomer_telepon` varchar(16) NOT NULL," +
  "`nomer_reg_bisnis` varchar(255) NOT NULL," +
  "`jenis_perusahaan` varchar(255) NOT NULL," +
  "PRIMARY KEY (`id`));");
			
		db.execSQL("CREATE TABLE IF NOT EXISTS `" +TABLE_NAME2+ "` ("+
				  "`id` int(10) NOT NULL," +
				  "`kode_perusahaan` int(10) NOT NULL," +
				  "`jabatan` varchar(64) NOT NULL," +
				  "`fungsi_kerja` text NOT NULL," +
				  "`rincian_pekerjaan` text NOT NULL," +
				  "`pendidikan_minimal` text NOT NULL," +
				  "`awal_ditayangkan` date NOT NULL," +
				  "`akhir_ditayangkan` date NOT NULL," +
				  "PRIMARY KEY (`id`));");
		
		db.execSQL("CREATE TABLE IF NOT EXISTS `" +TABLE_NAME3+ "` ("+
				  "`id` int(10) NOT NULL," +
				  "`email` varchar(64) NOT NULL," +
				  "`nomer_hp` varchar(16) NOT NULL," +				
				  "PRIMARY KEY (`id`));");
		
	}
	*/
	 //method SelectData untuk menampilkan data.
	public Cursor SelectData(SQLiteDatabase db, String sql){
		Cursor cursor = db.rawQuery(sql,null); 
		return cursor;
		
	}
	
	 //method InsertData untuk mengisikan data ke TABLE_NAME1.
		public void InsertDataTABLE_NAME (SQLiteDatabase db, String [] data){
				
			
			ContentValues cv = new ContentValues();
			cv.put("id", data[0]);
			cv.put("nama", data[1]);
			cv.put("deskripsi", data[2]);
			cv.put("alamat", data[3]);
			cv.put("nomer_telepon", data[4]);
			cv.put("nomer_reg_bisnis", data[5]);
			cv.put("jenis_perusahaan", data[6]);
			
			cv.put("kode_perusahaan", data[7]);
			cv.put("jabatan", data[8]);
			cv.put("fungsi_kerja", data[9]);
			cv.put("rincian_pekerjaan", data[10]);
			cv.put("pendidikan_minimal", data[11]);
			cv.put("awal_ditayangkan", data[12]);
			cv.put("akhir_ditayangkan", data[13]);
			
			cv.put("email", data[14]);
			cv.put("nomer_hp", data[15]);
			
			db.insert(TABLE_NAME, null, cv);
					
		}
		
	/*
	 //method InsertData untuk mengisikan data ke TABLE_NAME1.
	public void InsertDataTABLE_NAME1 (SQLiteDatabase db, String [] data){
			
		
		ContentValues cv = new ContentValues();
		cv.put("id", data[0]);
		cv.put("nama", data[1]);
		cv.put("deskripsi", data[2]);
		cv.put("alamat", data[3]);
		cv.put("nomer_telepon", data[4]);
		cv.put("nomer_reg_bisnis", data[5]);
		cv.put("jenis_perusahaan", data[6]);
		
		
		db.insert(TABLE_NAME1, null, cv);
				
	}
	
	
	 //method InsertData untuk mengisikan data ke TABLE_NAME1.
	public void InsertDataTABLE_NAME2 (SQLiteDatabase db, String [] data){
				
			
		ContentValues cv = new ContentValues();
		cv.put("id", data[0]);
		cv.put("kode_perusahaan", data[1]);
		cv.put("jabatan", data[2]);
		cv.put("fungsi_kerja", data[3]);
		cv.put("rincian_pekerjaan", data[4]);
		cv.put("pendidikan_minimal", data[5]);
		cv.put("awal_ditayangkan", data[6]);
		cv.put("akhir_ditayangkan", data[7]);
		
		
		db.insert(TABLE_NAME2, null, cv);
					
	}
	//method InsertData untuk mengisikan data ke TABLE_NAME1.
	public void InsertDataTABLE_NAME3 (SQLiteDatabase db, String [] data){
					
				
		ContentValues cv = new ContentValues();
		cv.put("id", data[0]);
		cv.put("email", data[1]);
		cv.put("nomer_hp", data[2]);
		
		db.insert(TABLE_NAME3, null, cv);
						
	}	
	
	public void DeleteTABLE_NAME1(SQLiteDatabase db){
		db.delete(TABLE_NAME1, null, null);		
	}
	public void DeleteTABLE_NAME2(SQLiteDatabase db){
		db.delete(TABLE_NAME2, null, null);
		
	}
	public void DeleteTABLE_NAME3(SQLiteDatabase db){
		db.delete(TABLE_NAME3, null, null);
		
	}
	*/
		public void DeleteTABLE_NAME(SQLiteDatabase db){
			db.delete(TABLE_NAME, null, null);		
		}
}
