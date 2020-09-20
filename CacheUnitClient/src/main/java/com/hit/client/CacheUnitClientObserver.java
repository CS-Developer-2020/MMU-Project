package com.hit.client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.hit.view.CacheUnitView;

public class CacheUnitClientObserver implements PropertyChangeListener{

	private CacheUnitClient client;
	
	public CacheUnitClientObserver() {
		client = new CacheUnitClient();
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		String requestContent = (String)e.getNewValue();
		
		String response = client.send(requestContent);
		
		CacheUnitView view = (CacheUnitView)e.getSource();
		view.updateUIData(response);
	}

}
