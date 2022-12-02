package innitialDayToCloneFrom;


import java.io.File;
import java.net.URL;

import org.junit.jupiter.api.Test;


public class DayXPart1Test {

	DayX dayX = new DayX();
	
	@Test
	void pojo_test() throws Exception {
		POJO pojo = new POJO();
	}
	
	
	@Test
	void use_different_input_test() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		dayX.setFileToUse(new File(fileName.getPath()));
	}
	
}
