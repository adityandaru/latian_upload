package ndaru.nmtloker.pckg;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabLayout1Activity extends TabActivity{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tablayout);
		
		Intent intent = getIntent();
		String id_intent = intent.getStringExtra("id_intent");
		
		TabHost tabHost =  getTabHost();
		
		TabSpec detail = tabHost.newTabSpec("Detail");
		detail.setIndicator("Detail  Lowongan");
        Intent detailIntent = new Intent(this, DetailLowongan.class);
        detailIntent.putExtra("id_DetailLowongan", id_intent);
        detail.setContent(detailIntent);
       
        TabSpec profile = tabHost.newTabSpec("Profile");
        profile.setIndicator("Profile Perusahaan");
        Intent profileIntent = new Intent(this, ProfilePerusahaan.class);
        profileIntent.putExtra("id_ProfilePerusahaan", id_intent);
        profile.setContent(profileIntent);
		
		
		
        tabHost.addTab(detail);
		tabHost.addTab(profile);
		
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
