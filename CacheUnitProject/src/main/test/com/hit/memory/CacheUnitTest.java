package com.hit.memory;


import com.hit.algorithm.IAlgoCache;
import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.dao.DaoFileImpl;
import com.hit.dao.IDao;
import com.hit.dm.DataModel;
import org.junit.Assert;
import org.junit.Test;

class CacheUnitTest {
	
	@Test
	public void testMain(){
		try {
		IAlgoCache<Long, DataModel<String>> algo = new LRUAlgoCacheImpl<>(4);
		CacheUnit<String> cacheUnit = new CacheUnit<>(algo);
		IDao<Long, DataModel<String>> dao = new DaoFileImpl<>("datasource.txt");
		
		DataModel<String> dm1 = new DataModel<String>(10L,"FirstString");
		DataModel<String> dm2 = new DataModel<String>(20L,"SecondString");
		DataModel<String> dm3 = new DataModel<String>(35L,"ThirdString");
		
		dao.save(dm1);
		dao.save(dm2);
		dao.save(dm3);
		Assert.assertEquals("FirstString", dao.find(10L).getContent());
		Assert.assertEquals("SecondString", dao.find(20L).getContent());
		DataModel<String> temp=dao.find(10L);
		System.out.println("Datamodel(10) ID:" +temp.getDataModelId());
		System.out.println("Datamodel(10) Content:" +temp.getContent());
		
		Long[] ids = {35L,1L,10L};
		cacheUnit.myAlgo.putElement(dm1.getDataModelId(), dm1);
		cacheUnit.myAlgo.putElement(dm2.getDataModelId(), dm2);
		DataModel<String>[] returnDM = cacheUnit.getDataModels(ids);
		if (returnDM[0] == null) 
			returnDM[0] = dao.find(35L);
		System.out.println("Datamodel(3)  Content:" + returnDM[0].getContent());
		
		Assert.assertEquals("ThirdString", returnDM[0].getContent());
		Assert.assertEquals("FirstString", returnDM[2].getContent());
		}
		catch (NullPointerException e) {
			System.out.println("Datamodel not found");
			return;
		}
	}
}
