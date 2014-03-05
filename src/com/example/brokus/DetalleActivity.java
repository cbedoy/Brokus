package com.example.brokus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import AsyncTasks.DetallePublicacionAnsyTask;
import android.opengl.Visibility;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetalleActivity extends Activity {

	private TextView fecha, titulo, contenido;
	private ImageView imagen;
	public static DetalleActivity mthis;
	private BRPublicacion publicacion;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle);
		
		
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
		
		
		Bundle extra = getIntent().getExtras();
		int id = extra.getInt("idPublicacion");
		
		this.fecha = (TextView)findViewById(R.id.sFecha_detalle);
		this.titulo = (TextView)findViewById(R.id.sTitulo_detalle);
		this.contenido = (TextView)findViewById(R.id.sContenido_detalle);
		this.imagen = (ImageView)findViewById(R.id.imagen_detalle);
		mthis = this;
		
		DetallePublicacionAnsyTask tarea = new DetallePublicacionAnsyTask(id);
		tarea.execute();
		
		
		
		

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalle, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.action_recomendar:
	        	mensajeRecomandar();
	        	return true;
	        case R.id.action_correo:
	        	actionCorreo();
	        	return true;
	        	
	        	
	        
	            
	        
	    }

	    return super.onOptionsItemSelected(item);
	}

	private void actionCorreo() {
		// TODO Auto-generated method stub
		
	}

	private void mensajeRecomandar() {
		AlertDialog.Builder mensaje = new AlertDialog.Builder(getApplicationContext());
		
		
	}

	public void convertirJsonPublicaciones(JSONObject json) {
		// TODO Auto-generated method stub
		publicacion = new BRPublicacion();
		try {
			JSONArray jarray = json.getJSONArray("publicacion");
			
			for(int i=0; i<jarray.length(); i++){
				JSONObject jobj = jarray.getJSONObject(i);
				this.publicacion.setId(jobj.getInt("idpublicacion"));
				this.titulo.setText(jobj.getString("titulo"));
				this.contenido.setText(jobj.getString("contenido"));
				this.contenido.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
				//this.fecha.setText();
				if(jobj.getString("extension").toUpperCase().equals(".JPG")){
					this.imagen.setImageBitmap(stringToBitmap(jobj.getString("anexo")));				
				}else{
					this.imagen.setVisibility(View.GONE);
				}
				this.fecha.setText(jobj.get("fecha").toString());
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	
	private  Bitmap stringToBitmap(String imageString){
	    Bitmap bitmap = null;
	    byte[] byteArray = Base64.decode(imageString, Base64.DEFAULT);
	    bitmap = BitmapFactory.decodeByteArray(byteArray , 0, byteArray.length);
	    return bitmap;
	    
	}
	
	
	
	
	

}
