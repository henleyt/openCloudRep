package com.FiscaInnovations.lang;

public class LSLCore {

	protected enum LangSet { en, fr };
	
	protected LangSet lang;
	
	protected LSLCore() {
		lang = LangSet.en;
	}
	
	protected LSLCore(String langParam) {
		if(langParam != null && langParam.equals("fr"))
			lang = LangSet.fr;
		else //Default
			lang = LangSet.en; 
	}
	
	protected String getLangParamNext(int step)  {
		LangSet[] langList = LangSet.values();
		int listCursor = 0;
		int newListCursor = 0;
				
		for(int i=0;i<langList.length;i++)
			if(langList[i] == lang)
				listCursor = i;
						
		if((listCursor+step)>(langList.length-1)) 
			newListCursor = (listCursor+step) % langList.length;
		else
			newListCursor=listCursor+step;
		
		return langList[newListCursor].toString();		
	}
	
	
	public String toString() {
		if(lang == LangSet.fr)
			return "fr";
		else //Default
			return "en";
	}
}
