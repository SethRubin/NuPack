import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class MarkupCalculator {
	private BasePrice basePrice;
	private ConstMarkup constMarkup;

	private WorkersMarkup workersMarkup;
	private PharmaceuticalMarkup pharmaceuticalMarkup;
	private FoodMarkup foodMarkup;
	private ElectronicMarkup electronicMarkup;

	private boolean gotBasePrice;
	private boolean hasPharmaceuticalMarkup;
	private boolean hasFoodMarkup;
	private boolean hasElectronicMarkup;

	public MarkupCalculator() {
		basePrice = new BasePrice();
		constMarkup = new ConstMarkup();
		workersMarkup = new WorkersMarkup();
		pharmaceuticalMarkup = new PharmaceuticalMarkup();
		foodMarkup = new FoodMarkup();
		electronicMarkup = new ElectronicMarkup();
	}

	private boolean checkBasePrice(String basePriceString) {
		if (basePriceString.startsWith("$")) {
			try {
				String numString = basePriceString.substring(1);
				basePrice.setBasePrice(new BigDecimal(numString));
				return true;
			} catch (NumberFormatException e) {
				System.err
						.println("The first entry does not have a properly formatted number after the $");
			}
		} else {
			System.err
					.println("The first entry is not the base price or does not start with a $");
		}
		return false;
	}

	private boolean checkNumberWorkers(String numberWorkersString) {
		List<String> numberWorkersList = Arrays.asList(numberWorkersString
				.split(" "));
		if (numberWorkersList.size() != 2) {
			System.err
					.println("The second entry is not the number of workers or is not a number followed by 'person' or 'people'");
			return false;
		}
		try {
			String numString = numberWorkersList.get(0);
			int numWorkers = Integer.parseInt(numString);
			if (!((numWorkers == 1 && numberWorkersList.get(1).equals("person")) || numberWorkersList
					.get(1).equals("people"))) {
				System.err
						.println("The second entry is not followed by 'person' or 'people', or is using the wrong word");
				return false;
			}
			workersMarkup.setNumberWorkers(numWorkers);
			return true;
		} catch (NumberFormatException e) {
			System.err
					.println("The second entry does not start with a properly formatted number");
			return false;
		}
	}

	private BigDecimal calculateMarkup() {
		BigDecimal basePriceAmount = basePrice.getBasePrice();
		BigDecimal constMarkupAmount = constMarkup.getMarkup();
		BigDecimal workersMarkupAmount = workersMarkup.getMarkup();
		BigDecimal pharmaceuticalMarkupAmount = hasPharmaceuticalMarkup ? pharmaceuticalMarkup
				.getMarkup() : BigDecimal.ZERO;
		BigDecimal foodMarkupAmount = hasFoodMarkup ? foodMarkup.getMarkup()
				: BigDecimal.ZERO;
		BigDecimal electronicMarkupAmount = hasElectronicMarkup ? electronicMarkup
				.getMarkup() : BigDecimal.ZERO;
		return basePriceAmount.multiply(constMarkupAmount).multiply(
				new BigDecimal("1").add(workersMarkupAmount)
						.add(pharmaceuticalMarkupAmount).add(foodMarkupAmount)
						.add(electronicMarkupAmount));
	}

	public String getMarkup(String markupString) {
		return getMarkup(Arrays.asList(markupString.split("\r\n|\r|\n")));
	}

	public String getMarkup(List<String> markupList) {
		for (int i = 0; i < markupList.size(); i++) {
			String stringMarkup = markupList.get(i);
			if (i == 0) {
				if (!checkBasePrice(stringMarkup)) {
					return null;
				}
				gotBasePrice = true;
			} else if (i == 1) {
				if (!checkNumberWorkers(stringMarkup)) {
					return null;
				}
			} else {
				if (!hasPharmaceuticalMarkup) {
					List<String> pharmaceuticalMarkupList = pharmaceuticalMarkup
							.getStringList();
					for (int j = 0; j < pharmaceuticalMarkupList.size(); j++) {
						if (stringMarkup
								.equals(pharmaceuticalMarkupList.get(j))) {
							hasPharmaceuticalMarkup = true;
						}
					}
				}
				if (!hasFoodMarkup) {
					List<String> foodMarkupList = foodMarkup.getStringList();
					for (int j = 0; j < foodMarkupList.size(); j++) {
						if (stringMarkup.equals(foodMarkupList.get(j))) {
							hasFoodMarkup = true;
						}
					}
				}
				if (!hasElectronicMarkup) {
					List<String> electronicMarkupList = electronicMarkup
							.getStringList();
					for (int j = 0; j < electronicMarkupList.size(); j++) {
						if (stringMarkup.equals(electronicMarkupList.get(j))) {
							hasElectronicMarkup = true;
						}
					}
				}
			}
		}
		if (!gotBasePrice) {
			System.err.println("The input is empty");
			return null;
		}
		return "$".concat(calculateMarkup().setScale(2,
				BigDecimal.ROUND_HALF_UP).toString());
	}

	public void addPharmaceuticalString(String stringToAdd) {
		pharmaceuticalMarkup.addStringToList(stringToAdd);
	}

	public void addFoodString(String stringToAdd) {
		foodMarkup.addStringToList(stringToAdd);
	}

	public void addElectronicString(String stringToAdd) {
		electronicMarkup.addStringToList(stringToAdd);
	}

//	public static void main(String[] args) {
//		MarkupCalculator markupCalculator = new MarkupCalculator();
//		System.out.println(markupCalculator
//				.getMarkup("$12456.95\n4 people\nbooks"));
//	}
}
