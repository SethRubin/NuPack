package MarkupCalculator;
import java.math.BigDecimal;

public class ConstMarkup extends Markup {
	public ConstMarkup() {
		markupPercent = new BigDecimal("1.05");
	}
}
