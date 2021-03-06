package mnuster.dchant.lib;

public class ItemInfo {

	public static class TEMPLATE_BLANK {
		public static int ID;
		public static final int DEFAULT = 20000;
		public static final String KEY = "templateBlank";
		public static final String UNLOCALIZED_NAME = KEY;
		public static final String NAME = "Blank Template";
		public static final String ICON = KEY;
	}
	
	public static class TEMPLATE_ENCH {
		public static int ID;
		public static final int DEFAULT = 20001;
		public static final String KEY = "templateEnch";
		public static final String UNLOCALIZED_NAME = KEY;
		public static final String NAME = "Enchanted Template";
		public static final String ICON = KEY;
		
		public static final String TAG = "enchID";
	}

}
