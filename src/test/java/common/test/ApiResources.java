package common.test;



public class ApiResources {
	private static String allMethods = "/api/allmethods";
	private static String allMethodsPowerOff = "/api/allmethods/off";
	private static String allMethodsPowerOn = "/api/allmethods/on";
	private static String userId = "8ecb9f88-e781-6e0a-e12d-f1b056026cd9";
	
	public static String getResourceAllMethods() {
		return allMethods;
	}
	
	public static String getResourcePowerOFF() {
		return allMethodsPowerOff;
	}
	
	public static String getResourcePowerOn() {
		return allMethodsPowerOn;
	}
	
	public static String getUserId() {
		return userId;
	}
	
	public static String getPostRequestBody(String power) {
		//JSONObject requestParams = new JSONObject();
		//requestParams.put("power", power);
		String msgBody="{ \"power\" : \""+power+"\" }";
		return msgBody;
			
	}
}
