import java.util.ArrayList;
import java.util.List;

public class MarkupWithStringList extends Markup {
	List<String> stringsInCategoryList = new ArrayList<String>();
	
	protected void addStringToList(String stringToAdd) {
		stringsInCategoryList.add(stringToAdd);
	}
	
	protected List<String> getStringList() {
		return stringsInCategoryList;
	}
}
