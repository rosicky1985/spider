package com.nbb.spider.entity;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractItem implements Item {
	protected Integer rank;
	protected String title;

	protected String makeString(List<? extends Object> list, String delimer) {
		StringBuffer sb = new StringBuffer();
		boolean first = true;
		if (list != null && list.size() != 0) {
			Iterator<? extends Object> itr = list.iterator();
			while (itr.hasNext()) {
				String key = itr.next().toString();
				if (first) {
					sb.append(key);
					first = false;
				} else {
					sb.append(delimer).append(key);
				}
			}
		}
		return sb.toString();
	}

	protected String parseNumeric(String text) {
		text = text.replaceAll(",", "");
		return text;
	}

	public static void sort(List<Item> allLine) {
		Collections.sort(allLine, new Comparator<Item>() {
			public int compare(Item arg0, Item arg1) {
				return arg0.compareRankto(arg1);
			}
		});
	}

	@Override
	public int compareRankto(Item arg1) {
		return new Integer(this.getRank())
				.compareTo(new Integer(arg1.getRank()));
	}

	@Override
	public Integer getRank() {
		return rank;
	}

	public void setRank(String rank2) {
		rank2 = parseNumeric(rank2);
		this.rank = Integer.parseInt(rank2);
	}

	@Override
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}