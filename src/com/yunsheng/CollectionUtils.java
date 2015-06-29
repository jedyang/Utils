package com.yunsheng;

import java.util.List;

/**
 * 
 * @author yunsheng
 *
 */
public class CollectionUtils {

	public static <T> T[] parseList(List<T> ll)
	{
		if(null == ll || ll.isEmpty())
		{
			return null;
		}
		@SuppressWarnings("unchecked")
		T[] arrayT = (T[]) new Object[ll.size()];
		for(int i = 0; i < ll.size(); i++)
		{
			arrayT[i] = ll.get(i);
		}
		
		return arrayT;
	}
}
