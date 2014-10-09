package MarkupCalculator;
import java.math.BigDecimal;

public class Markup {
	BigDecimal markupPercent;
	
	protected BigDecimal getMarkup() {
		return markupPercent;
	}
	
	protected void setMarkupPercent(BigDecimal newMarkupPercent) {
		markupPercent = newMarkupPercent;
	}
}
