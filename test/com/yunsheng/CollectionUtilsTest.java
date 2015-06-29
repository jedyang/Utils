package com.yunsheng;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CollectionUtilsTest {

	@Test
	public void test() {
		List<String> ls = new ArrayList<String>();
		ls.add("1");
		ls.add("2");
		Object[] result = CollectionUtils.parseList(ls);
		System.out.println(result);
	}

}
