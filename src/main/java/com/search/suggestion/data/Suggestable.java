package com.search.suggestion.data;

import java.util.Map;

public interface Suggestable extends Indexable {

	/**
	 * get filter values
	 * 
	 * @return
	 */
	Map<String, Integer> getFilter();

	/**
	 * suggetable items can decide if a filter should be ignored
	 * 
	 * @param filter
	 * @return
	 */
	boolean ignoreFilter(String filter);

	String getSearch();

	String getRealText();

	Suggestable copy();

	int getCount();

	void setCount(int i);

}
