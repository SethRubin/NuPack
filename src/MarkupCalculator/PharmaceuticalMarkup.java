package MarkupCalculator;
import java.math.BigDecimal;

public class PharmaceuticalMarkup extends MarkupWithStringList {
	public PharmaceuticalMarkup() {
		markupPercent = new BigDecimal("0.075");
		stringsInCategoryList.add("drugs");
	}
}
