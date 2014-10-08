import java.math.BigDecimal;

public class WorkersMarkup extends Markup {
	BigDecimal markupPercentPerWorker;
	int numberWorkers;
	
	public WorkersMarkup() {
		markupPercentPerWorker = new BigDecimal("0.012");
		setNumberWorkers(0);
	}
	
	void setNumberWorkers(int newNumberWorkers) {
		numberWorkers = newNumberWorkers;
		markupPercent = markupPercentPerWorker.multiply(new BigDecimal(numberWorkers));
	}
}
