package com.example.brokus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import AsyncTasks.MiPerfilAnsyTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MiPerfilActivity extends Activity {

	public static MiPerfilActivity mthis;
	TextView nombre, puesto, sector;
	ImageView imagen;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mi_perfil);
		
		nombre = (TextView)findViewById(R.id.sNombre_perfil);
		puesto = (TextView)findViewById(R.id.sPuesto_perfil);
		sector = (TextView)findViewById(R.id.sSector_perfil);
		imagen = (ImageView)findViewById(R.id.imagen_detalle);
		
		this.nombre.setText("");
		this.puesto.setText("");
		this.sector.setText("");
		
		MiPerfilAnsyTask miPerfil = new MiPerfilAnsyTask();
		miPerfil.execute();
		mthis = this;
		setTitle("Mis publicaciones");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mi_perfil, menu);
		return true;
	}

	public void convertirJsonUsuario(JSONObject json) {
		JSONArray array=null;
		BRUsuario user = new BRUsuario();
		
		try {
			array = json.getJSONArray("usuario");
			System.out.println(array.length());
			
				JSONObject jobj = array.getJSONObject(0);
				user.setNombre(jobj.getString("nombre"));
				
				
				//Toast.makeText(mthis, jobj.getString("puesto"), Toast.LENGTH_LONG).show();
				//this.nombre.setText("  "+jobj.getString("nombre"));
				//this.puesto.setText("  "+jobj.getString("puesto"));
				//this.sector.setText("  "+jobj.getString("sector"));
				//this.imagen.setImageBitmap(this.stringToBitmap(jobj.getString("imagen")));
				
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.nombre.setText(user.getNombre());
	}
	
	private  Bitmap stringToBitmap(String imageString){
	    Bitmap bitmap = null;
	    byte[] byteArray = Base64.decode(imageString, Base64.DEFAULT);
	    bitmap = BitmapFactory.decodeByteArray(byteArray , 0, byteArray.length);
	    return bitmap;
	    
	}

}
