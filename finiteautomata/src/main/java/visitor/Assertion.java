package visitor;

import java.util.List;

public abstract class Assertion {
	public abstract boolean verify();
	public abstract String getResult();
	
	protected String getLabelWord(List<String> labels){
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < labels.size(); i++){
			result.append(labels.get(i));
			if(i < labels.size() - 1){
				result.append("->");
			}
		}
		
		return result.toString();
	}
}
