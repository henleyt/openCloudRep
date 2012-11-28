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
	
	public String toString() {
		if(lang == LangSet.fr)
			return "fr";
		else //Default
			return "en";
	}
}
