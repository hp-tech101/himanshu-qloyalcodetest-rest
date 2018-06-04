package dataproviders;

import org.testng.annotations.DataProvider;

public class TestDataFile {
	
	@DataProvider(name="poweroff400")
	public static Object[][] testDataPowerOff() {
		Object[][] data= {
				{"000000000000"},
				{""}
		};
		return data;
	}
	
	@DataProvider(name="poweron200")
	public static Object[][] testDataPowerOnValid() {
		Object[][] data= {
				{"1"},{"1.0"},{"+1.0"},{"1.01"},{"1.5"},{"2"},{"30"},{"+30.5"},{"59"},{"59.5"},{"59.99"},{"60"},{"60.0"},{"+60.0"}
				//{"20"}
				
		};
		return data;
	}

	@DataProvider(name="poweron200invalid")
	public static Object[][] testDataPowerOnInvalid() {
		Object[][] data= {
				{"0"},{"61"},{"0.9"},{"60.01"},{"-1"}
				
		};
		return data;
	}
}
