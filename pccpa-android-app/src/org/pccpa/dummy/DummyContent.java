package org.pccpa.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import android.support.v4.app.Fragment;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

	/**
	 * An array of sample (dummy) items.
	 */
	public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

	static {
		// Add 3 sample items.
		//addItem(new DummyItem("remind", "待办提醒",org.pccpa.remind.IndexFragment.class));
		addItem(new DummyItem("contact","通讯录",org.pccpa.contact.IndexFragment.class));
		//addItem(new DummyItem("news", "新闻"));
	}

	private static void addItem(DummyItem item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.id, item);
	}

	/**
	 * A dummy item representing a piece of content.
	 */
	public static class DummyItem {
		public String id;
		public String content;

		public Class<? extends Fragment> fragmentCls;
		
		public DummyItem(String id, String content,Class<? extends Fragment> fragmentCls) {
			this.id = id;
			this.content = content;
			this.fragmentCls=fragmentCls;
		}

		@Override
		public String toString() {
			return content;
		}
	}
}
