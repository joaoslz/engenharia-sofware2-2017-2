package dcomp.es2.locadora.utils;

import java.time.LocalDate;
import java.util.Date;

public class DataUtils {
	
	public static LocalDate adicionarDias(LocalDate data, int dias) {
		return data.plusDays(dias);
	}
	
	public static LocalDate obterDataComDiferencaDias(int dias) {
		return LocalDate.now().plusDays(dias );
	}
	
	public static LocalDate amanha() {
		return LocalDate.now().plusDays(1);
		 
	}
	
	public static LocalDate obterData(int dia, int mes, int ano){
		return LocalDate.of(ano, mes, dia);
	}
	
	/**
	 * Verifica se uma data é igual a outra
	 * 	Esta comparação considera apenas dia, mes e ano
	 * 
	 * @param data1
	 * @param data2
	 * @return
	 */
	public static boolean isMesmaData(LocalDate data1, LocalDate data2) {
		return data1.equals(data2);
	}
	
	/**
	 * Verifica se uma determinada data é o dia da semana desejado
	 * 
	 * @param data Data a ser avaliada
	 * @param diaSemana <code>true</code> caso seja o dia da semana desejado, 
	 * <code>false</code> em caso contrário 
	 * @return
	 */
	public static boolean verificarDiaSemana(LocalDate data, int diaSemana) {
		return data.getDayOfWeek().getValue() == diaSemana;
	}
}
