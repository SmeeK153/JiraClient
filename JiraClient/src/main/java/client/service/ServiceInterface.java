package client.service;

public interface ServiceInterface {
	
	static Resource RESOURCE = null;
	
	public static enum Resource {
		SEARCH
	}
	
	public static String getServiceReference(Resource service) {
		return "";
	}
	
//	private static SSLConnection getServiceConnection(){
//		
//	}


}
