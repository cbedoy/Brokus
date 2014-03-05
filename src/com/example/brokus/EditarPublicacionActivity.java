package com.example.brokus;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class EditarPublicacionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar_publicacion);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.editar_publicacion, menu);
		return true;
	}

}
