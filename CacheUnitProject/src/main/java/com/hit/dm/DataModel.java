package com.hit.dm;

import java.util.Arrays;

public class DataModel<T extends Object> implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	private T content;
	private Long dataModelId;


	public DataModel(Long id, T content) {
		this.dataModelId = id;
		this.content = content;
	}
	
	public DataModel(DataModel<T> DataModelToCopy)
	{
		this.content = DataModelToCopy.content;
		this.dataModelId  = DataModelToCopy.dataModelId;
	}


	@Override
	public boolean equals(Object obj) {
		boolean result = false;

		if (obj == this) {
			result = true;
		} else if ((obj != null) && (obj instanceof DataModel)) {
			DataModel<T> cloneObj = ((DataModel<T>)obj).clone();
			if (cloneObj.getContent() == this.content && cloneObj.dataModelId == this.dataModelId) {
				result = true;
			}
		}

		return result;
	}

	@Override
	public int hashCode() {
		return content.hashCode();
	}

	@Override
	public String toString() {
		return Arrays.toString((byte[]) content) + " " + dataModelId.toString();
	}

	public T getContent() {
		return content;
	}

	public Long getDataModelId() {
		return dataModelId;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public void setDataModelId(Long DataModelId) {
		this.dataModelId = DataModelId;
	}

	@Override
	public DataModel<T> clone() {
		return new DataModel<T>(dataModelId, content);
	}

}
