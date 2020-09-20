package com.hit.services;

import com.hit.dao.DaoFileImpl;
import com.hit.dao.IDao;
import com.hit.dm.DataModel;
import com.hit.memory.CacheUnit;
import java.io.File;
import java.util.ArrayList;

import com.hit.algorithm.*;


public class CacheUnitService<T> extends java.lang.Object {
	private IDao<Long, DataModel<T>> dao;
	private CacheUnit<T> cache;
	private int totalRequestCount;
	private int totalModelsExtracted;
	private int capacity;
	private IAlgoCache<Long, DataModel<T>> algo;
	private String algorithmType = null;


	
	public CacheUnitService() {
		totalRequestCount = 0;
		totalModelsExtracted = 0;
		capacity = 100;
		algo = new LRUAlgoCacheImpl<Long, DataModel<T>>(capacity);
		algorithmType = algo.getClass().getSimpleName().replaceAll("AlgoCacheImpl", "");
		File pagesStorage = new File("C:\\JAVA\\DataSource.txt");
		dao = new DaoFileImpl<>(pagesStorage.getAbsolutePath());
		cache = new CacheUnit<T>(algo);
		loadDOAForVideo();
	}
	

	
	public DataModel<T>[] get(DataModel<T>[] data) {
		
		totalRequestCount++;
		
		int modelsExtracted = 0;
		DataModel<T>[] returnData;
		Long[] ids = new Long[data.length];
		
		for (int i = 0; i < data.length; i++) 
		{
			ids[i] = data[i].getDataModelId();
		}
		
		returnData = cache.getDataModels(ids);
	
		modelsExtracted = returnData.length;
		
		ArrayList<DataModel<T>> PagesFromDao = new ArrayList<DataModel<T>>();
		for (int i = 0; i < returnData.length; i++) 
		{
			if (returnData[i] == null)
			{
				returnData[i] = dao.find(ids[i]);
				
				if(returnData[i] == null)
					modelsExtracted--;
				else
				{
					PagesFromDao.add(returnData[i]);
				}
			}
			
		}
		@SuppressWarnings("unchecked")
		DataModel<T>[] arr = new DataModel[PagesFromDao.size()];
		DataModel<T>[] surplusFromCache = cache.putDataModels(PagesFromDao.toArray(arr));
		if(surplusFromCache.length > 0)
		{
			for (DataModel<T> item : surplusFromCache)
			{
				dao.save(item);
			}
		}
	
		setTotalModelsExtracted(modelsExtracted);

		return returnData;
	}
	
	public int getTotalModelsExtracted() 
	{
		return totalModelsExtracted;
	}
	
	public void setTotalModelsExtracted(int length) 
	{
		totalModelsExtracted += length;
	}
	
	public int geTtotalRequestCount()
	{
		return totalRequestCount;
	}
	
	public boolean update(DataModel<T>[] data) 
	{	
		DataModel<T>[] foundModels = get(data);
		boolean updatedSucceeded = true;
		
			
			try {
				for (DataModel<T> memoryModel : foundModels) 
				{
					for (DataModel<T> userModel : data) 
					{

						if (userModel.getDataModelId().equals(memoryModel.getDataModelId())) 
						{
							memoryModel.setContent(userModel.getContent());
							dao.save(userModel);
							break;
						}
					}
				
				
				}
			}
			catch (Exception ex) {
			updatedSucceeded = false;
			ex.printStackTrace();
		}

		return updatedSucceeded;
	}

	public boolean delete(DataModel<T>[] data) 
	{
	
		DataModel<T>[] foundModels = get(data);
		boolean removedAll = true;
		
		Long[] ids = new Long[foundModels.length];
		try {
			for (int i = 0; i < foundModels.length; i++) 
			{
				ids[i] = foundModels[i].getDataModelId();
				
			}
			cache.removeDataModels(ids);
			}catch (Exception ex) {
				removedAll = false;
				ex.printStackTrace();
			}

		
		return removedAll;
	}
	
	public String statistics() {
		totalRequestCount++;
		
		StringBuilder responseBuilder = new StringBuilder();
		responseBuilder.append("Capacity: " + capacity + "\n");
		responseBuilder.append("Algorithm: " + algorithmType + "\n");
		responseBuilder.append("Total Number of requests: " + geTtotalRequestCount() + "\n");
		responseBuilder.append("Total number of DataModels(GET/DELETE/UPDATE requests): " + getTotalModelsExtracted() + "\n");
		responseBuilder.append("Total number of DataModel swaps: " + cache.getSwapsCount() + "\n");
		return responseBuilder.toString();
		
		
		
	}
	
	@SuppressWarnings("unchecked")
	public void loadDOAForVideo()
	{
		DataModel<String> initItem0 = new DataModel<String> ((long) 1, "a");
		DataModel<String> initItem1 = new DataModel<String> ((long) 2, "b");
		DataModel<String> initItem2 = new DataModel<String> ((long) 3, "c");
		DataModel<String> initItem3 = new DataModel<String> ((long) 4, "d");		
		
		dao.save((DataModel<T>) initItem0);
		dao.save((DataModel<T>) initItem1);
		dao.save((DataModel<T>) initItem2);
		dao.save((DataModel<T>) initItem3);
	}
}