package br.com.cvinicius.core.helper;

/**
 * Class utilitaria para conversão de unidades de medida
 * 
 * @author cvinicius
 * @since 11/09/2020
 * @version 1.0
 */
public class ManipulationBytes {

	public static String convertBytesToMB(Long bytes) {
		
		return String.format("%dMB", ((bytes / 1024) / 1024));
	}
}