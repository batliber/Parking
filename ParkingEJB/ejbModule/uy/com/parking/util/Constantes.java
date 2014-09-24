package uy.com.parking.util;

public class Constantes {

	public static final long __MINUTOS_HORA = 60;
	public static final long __SEGUNDOS_MINUTO = 60;
	public static final long __MILISEGUNDOS_SEGUNDO = 1000;
	public static final long __MILISEGUNDOS_MINUTO = __MILISEGUNDOS_SEGUNDO * __SEGUNDOS_MINUTO;
	public static final long __MILISEGUNDOS_HORA = __MILISEGUNDOS_SEGUNDO * __SEGUNDOS_MINUTO * __MINUTOS_HORA;
	
	public static final double __MINIMO_UNIDADES_PARKING = 0.5;
	
	public static final double __IMPUESTO_IVA = 0.22;
	
	public static final String __SEPARADOR_CAMPO = ";";
	
	public static final String __PREFIJO_ARCHIVO_CLIENTES = "Clientes";
}