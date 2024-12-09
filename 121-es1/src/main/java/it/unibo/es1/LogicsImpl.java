package it.unibo.es1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LogicsImpl implements Logics {

	private final List<Integer> values;

	public LogicsImpl(int size) {
		values = new ArrayList<>(Collections.nCopies(size, 0));
	}

	@Override
	public int size() {
		return values.size();
	}

	@Override
	public List<Integer> values() {
		return Collections.unmodifiableList(values);
	}

	@Override
	public List<Boolean> enablings() {
		final List<Boolean> list = new ArrayList<>();
		for(int i = 0; i < values.size(); i++){
			if(values.get(i) < values.size()){
				list.add(true);
			}
			else if(values.get(i) == values.size()){
				list.add(false);
			}
		}
		return list;
	}

	@Override
	public int hit(int elem) {
		final int newVal;
		newVal = values.get(elem) + 1;
		values.set(elem, newVal);
		return newVal;
	}

	@Override
	public String result() {
		/*String res = "<<";
		for(int i = 0; i < values.size(); i++){
			res = res + values.get(i) + "|";
		}
		StringBuilder str = new StringBuilder(res);
		str.deleteCharAt(res.length()-1);
		
		res = res + ">>";
		return res;*/
		return this.values.stream().map(String::valueOf).collect(Collectors.joining("|", "<<", ">>"));
	}

	@Override
	public boolean toQuit() {
		return this.values.stream().allMatch(i -> i == this.values.get(0));
	}
}
