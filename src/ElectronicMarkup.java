import java.math.BigDecimal;

public class ElectronicMarkup extends MarkupWithStringList {
	public ElectronicMarkup() {
		markupPercent = new BigDecimal("0.02");
		stringsInCategoryList.add("electronics");
	}
}
