package MarkupCalculator;
import java.math.BigDecimal;

public class FoodMarkup extends MarkupWithStringList {
	public FoodMarkup() {
		markupPercent = new BigDecimal("0.13");
		stringsInCategoryList.add("food");
	}
}
