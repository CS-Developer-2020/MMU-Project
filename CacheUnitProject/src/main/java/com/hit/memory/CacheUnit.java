package com.hit.memory;

import java.util.ArrayList;

import com.hit.algorithm.IAlgoCache;
import com.hit.dm.DataModel;

public class CacheUnit<T> {
	IAlgoCache<Long,DataModel<T>> myAlgo;
	int swapsCount;
	public CacheUnit(com.hit.algorithm.IAlgoCache<Long,DataModel<T>> algo)
	{
		myAlgo = algo;
		swapsCount = 0;
	}
	
	public DataModel<T>[] getDataModels(java.lang.Long[] ids) {
		int size = ids.length;
		@SuppressWarnings("unchecked")
		DataModel<T>[] temp = new DataModel[size];
		for(int i=0;i<size;i++) {
			temp[i] = myAlgo.getElement(ids[i]);
		}
		return temp;
	}
	
	public DataModel<T>[] putDataModels(DataModel<T>[] datamodels)
	{
		ArrayList<DataModel<T>> resultPages = new ArrayList<DataModel<T>>();
		for (DataModel<T> DM : datamodels) 
		{
			Long tmpDataModelId = DM.getDataModelId();
			DataModel<T> returnedPage = myAlgo.putElement(tmpDataModelId, DM);

			if(returnedPage != null) 
			{
				swapsCount++;
				resultPages.add(returnedPage);
			}
		}
		@SuppressWarnings("unchecked")
		DataModel<T>[] arr = new DataModel[resultPages.size()];
		return resultPages.toArray(arr);

	}
	
	public void removeDataModels(java.lang.Long[] ids) {
		for(int i=0;i<ids.length;i++) 
			myAlgo.removeElement(ids[i]);
	}
	public int getSwapsCount()
	{
		return swapsCount;
	}
}
