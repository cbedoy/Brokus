package com.example.brokus;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import AsyncTasks.CirculoConfianzaAnsyTask;
import AsyncTasks.PublicacionAnsyTask;
import AsyncTasks.UsuarioPerfilAnsyTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.widget.SlidingPaneLayout;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CirculoConfianzaActivity extends Activity {

	private SlidingPaneLayout mPanes;
	private ListView listaUsuarios;
	private ListView listaPublicaciones;
	private ListView listaPublicacionesCaducadas;
	private TextView sNombre;
	private TextView sPuesto;
	private TextView sSector;
	public ArrayList<BRPublicacion> listPublicacion;
	public ArrayList<BRUsuario> listUsuarios;
	public static CirculoConfianzaActivity mthis;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_circulo_confianza);
		this.setTitle("HOME");
		
		
		
		
		mPanes = (SlidingPaneLayout) findViewById(R.id.slidingPane);
		Toast.makeText(this,"Seleccione la publicacion a recomendar",Toast.LENGTH_SHORT).show();
        mthis = this;
        
        PublicacionAnsyTask tarea = new PublicacionAnsyTask();
       tarea.execute();
        
        UsuarioPerfilAnsyTask tarea_user = new UsuarioPerfilAnsyTask();
        
        CirculoConfianzaAnsyTask cirucloTarea = new CirculoConfianzaAnsyTask();
        cirucloTarea.execute();
        
        this.listaUsuarios = (ListView)findViewById(R.id.left_drawer);
		this.listaUsuarios.setAdapter(new MyAdapterCirculo());

		
		


		this.sNombre = (TextView)findViewById(R.id.sNombre_perfil);
		this.sPuesto = (TextView)findViewById(R.id.sPuesto_perfil);
		this.sSector = (TextView)findViewById(R.id.sSector_perfil);
		
		this.sNombre.setText("Bety Diaz de Leon");
		this.sPuesto.setText("Analista de sistemas");
		this.sSector.setText("Tecnologias de informacion");
		
		
	}
	
	@Override
	protected void onRestart(){
		PublicacionAnsyTask tarea = new PublicacionAnsyTask();
        tarea.execute();
		super.onRestart();
	}
	
	
	private class PublicacionesAdapter extends BaseAdapter{

		private TextView usuario_publicacion;
		private TextView contenido_publicacion;
		private TextView fecha_publicacion;
		
		public PublicacionesAdapter(){
			
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listPublicacion.size();
		}

		@Override
		public Object getItem(int index) {
			// TODO Auto-generated method stub
			return index;
		}

		@Override
		public long getItemId(int index) {
			// TODO Auto-generated method stub
			return index;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup viewGroup) {
			// TODO Auto-generated method stub
			
			if(convertView == null){
				LayoutInflater inflate = (LayoutInflater)mthis.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View vistaView= inflate.inflate(R.layout.item_publicacion, null);
				convertView = vistaView;
			}
			
			if(convertView != null){
				int n = new Random().nextInt();
				LinearLayout layout = (LinearLayout)convertView;
				switch(position%5){
					case 0:
						layout.setBackgroundResource(R.color.publicacion_color_1);
						break;
					case 1:
						layout.setBackgroundResource(R.color.publicacion_color_2);
						break;
					case 2:
						layout.setBackgroundResource(R.color.publicacion_color_3);
						break;
					case 3:
						layout.setBackgroundResource(R.color.publicacion_color_4);
						break;
					case 4:
						layout.setBackgroundResource(R.color.publicacion_color_5);
						break;
					case 5:
						layout.setBackgroundResource(R.color.publicacion_color_6);
						break;
				}
				
				this.usuario_publicacion = (TextView)layout.findViewById(R.id.sNombre_publicacion);
				this.contenido_publicacion = (TextView)layout.findViewById(R.id.sContenido_publicacion);
				this.fecha_publicacion = (TextView)layout.findViewById(R.id.sFecha_publicacion);
				this.usuario_publicacion.setText(listPublicacion.get(position).getNombreUsuario());
				this.contenido_publicacion.setText(listPublicacion.get(position).getContenido());
				this.fecha_publicacion.setText(listPublicacion.get(position).getFechaString());
				
				contenido_publicacion.setOnLongClickListener(new View.OnLongClickListener() {

			        public boolean onLongClick(View v) {
			        	
			        	AlertDialog.Builder mensaje = new AlertDialog.Builder(mthis);
			        	mensaje.setMessage("������Que desea hacer con esta publicacion?");
			        	mensaje.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								
							}
						});
			        	
			        	mensaje.setNegativeButton("Editar", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								
							}
						});
			        	
			        	mensaje.setNeutralButton("Detalle", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent i = new Intent(mthis, DetalleActivity.class);
								i.putExtra("idPublicacion", listPublicacion.get(position).getId());
								startActivity(i);
							}
						});
			        	mensaje.show();
			        	
			        	//mthis.startActivity(new Intent(mthis, DetalleActivity.class));;
			        	return false;
			        }
			    });
				
			}
			
			return convertView;
		}
		
	}

	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.perfil, menu);
		return true;
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            if (mPanes.isOpen()) {
	                closePane();
	            } else {
	                openPane();
	            }
	        case R.id.action_new_post:
		        startActivity(new Intent(CirculoConfianzaActivity.this, NuevaPublicacionActivity.class));
		        return true;
	        case R.id.action_logout:
	        	 logout();
	        	 return true;
	        case R.id.action_circle:
	        	openPane();
	            return true;
	        
	    }

	    return super.onOptionsItemSelected(item);
	}
	
	private void openPane() {
        mPanes.openPane();
        
    }

    private void closePane() {
        mPanes.closePane();
        
    }
    @Override
	   public void onBackPressed(){ 
		   logout();
		   
	   }
    public void logout(){
    	 AlertDialog.Builder alert = new AlertDialog.Builder(mthis);
         alert.setMessage("Esta seguro que desea Cerrar Sesion ? ");
         alert.setCancelable(false);
         alert.setPositiveButton("Si", new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Intent i = new Intent(mthis, LoginActivity.class);
				startActivity(i);
				mthis.finish();
			}
       	  
         });
         alert.setNegativeButton("No", new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				
			}
       	  
       	  
         });
         alert.show();
    }

    @Override
	public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
   
	}
    
    
    private class MyAdapterCirculo extends BaseAdapter{

		private ImageView logo;
		private TextView usuario_nombre;
		private TextView usuario_perfil;
		private TextView usuario_sector;
		
		
		public MyAdapterCirculo(){
			
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 10;
		}

		@Override
		public Object getItem(int index) {
			// TODO Auto-generated method stub
			return index;
		}

		@Override
		public long getItemId(int index) {
			// TODO Auto-generated method stub
			return index;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup viewGroup) {
			// TODO Auto-generated method stub
			
			if(convertView == null){
				LayoutInflater inflate = (LayoutInflater)mthis.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View vistaView= inflate.inflate(R.layout.item_usuario, null);
				convertView = vistaView;
			}
			
			if(convertView != null){
				
				LinearLayout layout = (LinearLayout)convertView;
				this.usuario_nombre = (TextView)layout.findViewById(R.id.sNombre_perfil);
				this.usuario_perfil = (TextView)layout.findViewById(R.id.sPuesto_perfil);
				this.usuario_sector = (TextView)layout.findViewById(R.id.sSector_perfil);
				this.logo = (ImageView)layout.findViewById(R.id.imagen_detalle);
				usuario_nombre.setText(" USUARIO #"+(position+1));
				usuario_perfil.setText(" PERFIL #"+(position+1));
				usuario_sector.setText(" Sector: #"+(position+1));
				
				switch(position%3){
				case 0:
					this.logo.setImageResource(R.drawable.logo1);
					break;
				case 1:
					this.logo.setImageResource(R.drawable.logo2);
					break;
				case 2:
					this.logo.setImageResource(R.drawable.logo3);
					break;
				case 3:
					this.logo.setImageResource(R.drawable.logo4);
					break;
				}
				
				logo.setOnLongClickListener(new View.OnLongClickListener() {
					
					@Override
					public boolean onLongClick(View v) {
						mthis.startActivity(new Intent(mthis, PerfilEmpresaActivity.class));
						return false;
					}
				});
				
			}
			
			return convertView;
		}
		
	}






	public void convertirJsonPublicaciones(JSONObject json) {
		// TODO Auto-generated method stub
		try {
			JSONArray jarray = json.getJSONArray("publicacion");
			listPublicacion = new ArrayList<BRPublicacion>();
			listPublicacion.clear();

			
			for(int i=0; i<jarray.length(); i++){
				JSONObject jobj = jarray.getJSONObject(i);
				BRPublicacion publicacion = new BRPublicacion();
				publicacion.setId(jobj.getInt("idpublicacion"));
				publicacion.setTitulo(jobj.getString("titulo"));
				publicacion.setContenido(jobj.getString("contenido"));
				
				String contenidoAux = jobj.getString("contenido");
				if(contenidoAux.length() >50){
					contenidoAux = contenidoAux.substring(0, 50)+"...";
				}
				publicacion.setContenido(contenidoAux);
				//publicacion.setExtension(jobj.getString("extension"));
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
				try {
					String fecha = formatoDelTexto.parse(jobj.getString("fecha")).toString();
					publicacion.setFechaString(fecha);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				publicacion.setIdUsuario(jobj.getInt("idusuario"));
				//publicacion.setAnexo(jobj.getString("anexo"));
				publicacion.setNombreUsuario(jobj.getString("nombre usuario"));
				listPublicacion.add(publicacion);
			}
			
			
		} catch (JSONException e) {
			e.printStackTrace();
			
		}
		this.listaPublicaciones = (ListView)findViewById(R.id.listaPublicacion);
		this.listaPublicaciones.setAdapter(new PublicacionesAdapter());
		
		Log.i("response", "algo paso   "+listPublicacion.size()+"");
	}

	public void convertirJsonCirculoConfianza(JSONObject json) {
		this.listUsuarios = new ArrayList<BRUsuario>();
		BRUsuario usuario = new BRUsuario();
		try {
			JSONArray array = json.getJSONArray("usuario");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
