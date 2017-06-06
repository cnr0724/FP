package inter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class ArrayHash<T>{
	HashMap<String, ArrayList<T>> ah;
	
	public ArrayHash(){
		ah=new HashMap<String, ArrayList<T>>();
	}
	
	public boolean newKey (String key){
		if(ah.containsKey(key)==true){
			return false;
		}
		else{
			ah.put(key, new ArrayList<T>());
			return true;
		}
	}

	public boolean newValue(String key, T value) {
		if(ah.containsKey(key)==true&value!=null){
			ah.get(key).add(value);
			return true;
		}
		return false;
	}
	
	public int getSizeOf(String key) {
		return ah.get(key).size();
	}

	public boolean hasKey(String key) {
		return ah.containsKey(key);
	}

	public ArrayList<T> get(String key) {
		if(ah.containsKey(key)){
			return ah.get(key);
		}
		else return null;
	}

	public T get(String key, int idx) {
		if(ah.containsKey(key)&ah.get(key).size()>idx){
			return ah.get(key).get(idx);
		}
		else return null;
	}

	public int cntTotalElements() {
		int total=0;
		Set<String> set=ah.keySet();
		for(Iterator i=set.iterator();i.hasNext();){
			total+=ah.get(i).size();
		}
		return total;
	}

/*	public void newValue(String key, int i) {
		T a = null;
		if(ah.containsKey(key)){
			ah.get(key).add(i, a);
		}
		
	}*/
	
}
