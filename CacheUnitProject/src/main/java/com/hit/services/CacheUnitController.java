

package com.hit.services;

import com.hit.dm.DataModel;

public class CacheUnitController<T extends Object> {

	private CacheUnitService<T> service;
	
	public CacheUnitController() {
		service = new CacheUnitService<T>();
	}
	
	public DataModel<T>[] get(DataModel<T>[] data) {
		synchronized (service) {
			return service.get(data);
		}
	}
	
	public boolean update(DataModel<T>[] data) {
		synchronized (service) {
			return service.update(data);
		}
	}

	public boolean delete(DataModel<T>[] data) {
		synchronized (service) {
			return service.delete(data);
		}
	}
	
	public String statistics() {
		synchronized (service) {
			return service.statistics();
		}
	}
}

