package br.com.caelum.anobissexto;

public class AnoBissexto {
	
	public static boolean ehBissexto(int ano) {
		if(ano % 400 == 0 || (ano % 4 == 0 && ano % 100 != 0)) return true;
		return false;		
	}
	
}
