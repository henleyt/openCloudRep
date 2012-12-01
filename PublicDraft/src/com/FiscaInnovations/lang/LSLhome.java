package com.FiscaInnovations.lang;

import java.util.ArrayList;

public class LSLhome extends LSLCore {
	private ArrayList<String> arrLang;
	
	public LSLhome() {
		super();
		arrLang = new ArrayList<String>();		
		populateArray(false);
	}	
	
	public LSLhome(String langParam, boolean refTag)  {
		super(langParam);
		arrLang = new ArrayList<String>();		
		populateArray(refTag);
	}
	
	private void populateArray(boolean refTag) {
		if(super.lang == LangSet.en) {
			arrLang.add(0, "Home - Fisca Innovations");
			arrLang.add(1, "This is home.");
		}
		
		else if(super.lang == LangSet.fr) {
			arrLang.add(0, "Maison - Fisca Innovations");
			arrLang.add(1, "Ici la maison.");
		}
			
		if(refTag)
			for(int i=0;i<arrLang.size();i++)
				arrLang.set(i, "<"+i+">"+arrLang.get(i));
	}
	
	public String getLangParam() {
		return super.toString();
	}
	
	public String getLangParamNext(int step) {
		return super.getLangParamNext(step);
	}
			
	public String toString(int index) {
		if(arrLang != null && (index+1) <= arrLang.size())		
			return arrLang.get(index);
		return "(Undefined)";
	}
}