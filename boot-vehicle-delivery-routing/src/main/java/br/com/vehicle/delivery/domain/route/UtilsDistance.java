package br.com.vehicle.delivery.domain.route;

/**
 * Classe utilitario para efetuar calculo entre pontos.
 * 
 * @author cvinicius
 * @since 11/07/2018
 * @version 1.0
 */
public final class UtilsDistance {

	/**
	 * Calculo da Distância
	 * 
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param lon2
	 * @return double
	 */
	public static double distance(double lat1, double lon1, double lat2, double lon2) {

	    final int R = 6371; 

	    Double latDistance = deg2rad(lat2 - lat1);
	    Double lonDistance = deg2rad(lon2 - lon1);
	    
	    Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    
	    double distance = R * c * 1000; 
	    
	    distance = Math.pow(distance, 2) + Math.pow(0.0, 2);
	    
	    return Math.sqrt(distance);
	}

	/**
	 * 
	 * @param deg
	 * @return double
	 */
	private static double deg2rad(double deg) {
	    return (deg * Math.PI / 180.0);
	}
}