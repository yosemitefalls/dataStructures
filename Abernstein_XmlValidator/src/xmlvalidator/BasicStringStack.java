package xmlvalidator;

import java.util.*;

public class BasicStringStack implements StringStack {

	private ArrayList<String> items;

	public BasicStringStack() {
		items = new ArrayList<String>();
	}

	@Override
	public void push(String item) {

		items.add(0, item);

	}

	public boolean isEmpty() {
		return this.getCount() == 0;
	}

	@Override
	public String pop() {
		if (!isEmpty()) {
			return items.remove(0);
		} else {

			return null;

		}
	}

	@Override
	public String peek(int position) {
		if (!isEmpty()) {
			return items.get(position);
		} else {

			return null;

		}
	}

	@Override
	public int getCount() {

		return items.size();
	}

}
