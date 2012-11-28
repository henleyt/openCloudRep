package com.FiscaInnovations.lang;

import java.util.ArrayList;

public class LSLsearch extends LSLCore {
	private ArrayList<String> arrLang;
	
	public LSLsearch() {
		super();
		arrLang = new ArrayList<String>();		
		populateArray(false);
	}	
	
	public LSLsearch(String langParam, boolean refTag)  {
		super(langParam);
		arrLang = new ArrayList<String>();		
		populateArray(refTag);
	}
	
	private void populateArray(boolean refTag) {
		if(super.lang == LangSet.en) {
			arrLang.add(0, "Search - Fisca Innovations");
			arrLang.add(1, "Search field: ");
			arrLang.add(2, "Submit");
		}
		
		else if(super.lang == LangSet.fr) {
			arrLang.add(0, "Recherche - Fisca Innovations");
			arrLang.add(1, "Champ de recherche: ");
			arrLang.add(2, "Soumettre");
		}
			
		if(refTag)
			for(int i=0;i<arrLang.size();i++)
				arrLang.set(i, "<"+i+">"+arrLang.get(i));
	}
	
	public String getLangParam() {
		return super.toString();
	}
			
	public String toString(int index) {
		if(arrLang != null && (index+1) <= arrLang.size())		
			return arrLang.get(index);
		return "(Undefined)";
	}
}
