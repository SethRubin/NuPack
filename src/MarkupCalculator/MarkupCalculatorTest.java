package MarkupCalculator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MarkupCalculatorTest {
	MarkupCalculator markupCalculator = new MarkupCalculator();

	@Test
	public void testStandardStringMarkups() {
		String input1 = "$1299.99\n3 people\nfood";
		String input2 = "$5432.00\n1 person\ndrugs";
		String input3 = "$12456.95\n4 people\nbooks";

		String output1 = markupCalculator.getMarkup(input1);
		String output2 = markupCalculator.getMarkup(input2);
		String output3 = markupCalculator.getMarkup(input3);

		assertEquals("$1591.58", output1);
		assertEquals("$6199.81", output2);
		assertEquals("$13707.63", output3);
	}

	@Test
	public void testStandardListMarkups() {
		MarkupCalculator markupCalculator = new MarkupCalculator();
		List<String> input1 = new ArrayList<String>() {{
			add("$1299.99");
			add("3 people");
			add("food");
		}};
		List<String> input2 = new ArrayList<String>() {{
			add("$5432.00");
			add("1 person");
			add("drugs");
		}};
		List<String> input3 = new ArrayList<String>() {{
			add("$12456.95");
			add("4 people");
			add("books");
		}};

		String output1 = markupCalculator.getMarkup(input1);
		String output2 = markupCalculator.getMarkup(input2);
		String output3 = markupCalculator.getMarkup(input3);

		assertEquals("$1591.58", output1);
		assertEquals("$6199.81", output2);
		assertEquals("$13707.63", output3);
	}
	
	@Test
	public void testBasePrice() {
		String input1 = "$0\n0 people";
		String input2 = "$100.00\n0 people";
		String input3 = "$200.00\n0 people";
		String input4 = "$300.00\n0 people";

		String output1 = markupCalculator.getMarkup(input1);
		String output2 = markupCalculator.getMarkup(input2);
		String output3 = markupCalculator.getMarkup(input3);
		String output4 = markupCalculator.getMarkup(input4);

		assertEquals("$0.00", output1);
		assertEquals("$105.00", output2);
		assertEquals("$210.00", output3);
		assertEquals("$315.00", output4);
	}
	
	@Test
	public void testWorkerMarkups() {
		String input1 = "$100.00\n1 person";
		String input2 = "$100.00\n2 people";
		String input3 = "$100.00\n100 people";

		String output1 = markupCalculator.getMarkup(input1);
		String output2 = markupCalculator.getMarkup(input2);
		String output3 = markupCalculator.getMarkup(input3);

		assertEquals("$106.26", output1);
		assertEquals("$107.52", output2);
		assertEquals("$231.00", output3);
	}
	
	@Test
	public void testAddingStrings() {
		markupCalculator.addPharmaceuticalString("pills");
		markupCalculator.addFoodString("pizza");
		markupCalculator.addElectronicString("television");

		String input1 = "$100.00\n0 people\npills";
		String input2 = "$100.00\n0 people\npizza";
		String input3 = "$100.00\n0 people\ntelevision";

		String output1 = markupCalculator.getMarkup(input1);
		String output2 = markupCalculator.getMarkup(input2);
		String output3 = markupCalculator.getMarkup(input3);

		assertEquals("$112.88", output1);
		assertEquals("$118.65", output2);
		assertEquals("$107.10", output3);
	}
	
	@Test
	public void testAddingMultipleStrings() {
		markupCalculator.addFoodString("pizza");
		markupCalculator.addFoodString("cake");
		markupCalculator.addFoodString("pie");

		String input1 = "$100.00\n0 people\npizza";
		String input2 = "$100.00\n0 people\ncake";
		String input3 = "$100.00\n0 people\npie";

		String output1 = markupCalculator.getMarkup(input1);
		String output2 = markupCalculator.getMarkup(input2);
		String output3 = markupCalculator.getMarkup(input3);

		assertEquals("$118.65", output1);
		assertEquals("$118.65", output2);
		assertEquals("$118.65", output3);
	}

	@Test
	public void testMultipleSameStrings() {
		String input1 = "$100.00\n0 people\ndrugs\ndrugs\ndrugs";
		String input2 = "$100.00\n0 people\nfood\nfood\nfood";
		String input3 = "$100.00\n0 people\nelectronics\nelectronics\nelectronics";

		String output1 = markupCalculator.getMarkup(input1);
		String output2 = markupCalculator.getMarkup(input2);
		String output3 = markupCalculator.getMarkup(input3);

		assertEquals("$112.88", output1);
		assertEquals("$118.65", output2);
		assertEquals("$107.10", output3);
	}
	
	@Test
	public void testLargeNumbers() {
		String input1 = "$1000000.00\n0 people\ndrugs";
		String input2 = "$1000000000.00\n0 people\nfood";
		String input3 = "$1000000000000.00\n0 people\nelectronics";

		String output1 = markupCalculator.getMarkup(input1);
		String output2 = markupCalculator.getMarkup(input2);
		String output3 = markupCalculator.getMarkup(input3);

		assertEquals("$1128750.00", output1);
		assertEquals("$1186500000.00", output2);
		assertEquals("$1071000000000.00", output3);
	}
	
	@Test
	public void testSmallNumbers() {
		String input1 = "$1.00\n0 people\ndrugs";
		String input2 = "$0.10\n0 people\nfood";
		String input3 = "$0.01\n0 people\nelectronics";

		String output1 = markupCalculator.getMarkup(input1);
		String output2 = markupCalculator.getMarkup(input2);
		String output3 = markupCalculator.getMarkup(input3);

		assertEquals("$1.13", output1);
		assertEquals("$0.12", output2);
		assertEquals("$0.01", output3);
	}
	
	@Test
	public void testExtraMarkups() {
		String input1 = "$100.00\n0 people\nballs";
		String input2 = "$100.00\n0 people\ncanoes";
		String input3 = "$100.00\n0 people\nguitars";

		String output1 = markupCalculator.getMarkup(input1);
		String output2 = markupCalculator.getMarkup(input2);
		String output3 = markupCalculator.getMarkup(input3);

		assertEquals("$105.00", output1);
		assertEquals("$105.00", output2);
		assertEquals("$105.00", output3);
	}
	
	@Test
	public void testAllMarkups() {
		String input1 = "$100.00\n3 people\ndrugs\nfood\nelectronics";
		String input2 = "$200.00\n3 people\nfood\nelectronics\ndrugs";
		String input3 = "$300.00\n3 people\nelectronics\ndrugs\nfood";

		String output1 = markupCalculator.getMarkup(input1);
		String output2 = markupCalculator.getMarkup(input2);
		String output3 = markupCalculator.getMarkup(input3);

		assertEquals("$132.41", output1);
		assertEquals("$264.81", output2);
		assertEquals("$397.22", output3);
	}
	
	@Test
	public void testInvalidBasePrice() {
		String input1 = "100.00\n0 people";
		String input2 = "$ 100.00\n0 people";
		String input3 = "-$100.00\n0 people";
		String input4 = "$-100.00\n0 people";
		String input5 = "one dollar\n0 people";

		String output1 = markupCalculator.getMarkup(input1);
		String output2 = markupCalculator.getMarkup(input2);
		String output3 = markupCalculator.getMarkup(input3);
		String output4 = markupCalculator.getMarkup(input4);
		String output5 = markupCalculator.getMarkup(input5);

		assertNull(output1);
		assertNull(output2);
		assertNull(output3);
		assertNull(output4);
		assertNull(output5);
	}
	
	@Test
	public void testInvalidNumberWorkers() {
		String input1 = "$100.00\n0 person";
		String input2 = "$100.00\n1 people";
		String input3 = "$100.00\n2";
		String input4 = "$100.00\n3 ppl";
		String input5 = "$100.00\none person";

		String output1 = markupCalculator.getMarkup(input1);
		String output2 = markupCalculator.getMarkup(input2);
		String output3 = markupCalculator.getMarkup(input3);
		String output4 = markupCalculator.getMarkup(input4);
		String output5 = markupCalculator.getMarkup(input5);

		assertNull(output1);
		assertNull(output2);
		assertNull(output3);
		assertNull(output4);
		assertNull(output5);
	}
	
	@Test
	public void testNoLinebreak() {
		String input1 = "$100.00 1 person";
		String input2 = "$100.00 2 people";

		String output1 = markupCalculator.getMarkup(input1);
		String output2 = markupCalculator.getMarkup(input2);

		assertNull(output1);
		assertNull(output2);
	}
	
	@Test
	public void testOnlyBasePrice() {
		String input1 = "$100.00";
		String input2 = "$200.00";

		String output1 = markupCalculator.getMarkup(input1);
		String output2 = markupCalculator.getMarkup(input2);

		assertNull(output1);
		assertNull(output2);
	}
	
	@Test
	public void testMisspelledCategories() {
		String input1 = "$100.00\n0 people\n food";
		String input2 = "$100.00\n0 people\nfoood";
		String input3 = "$100.00\n0 people\nfood ";

		String output1 = markupCalculator.getMarkup(input1);
		String output2 = markupCalculator.getMarkup(input2);
		String output3 = markupCalculator.getMarkup(input3);

		assertEquals("$105.00", output1);
		assertEquals("$105.00", output2);
		assertEquals("$105.00", output3);
	}
}
