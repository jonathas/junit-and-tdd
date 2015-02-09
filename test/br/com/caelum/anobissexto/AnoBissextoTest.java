package br.com.caelum.anobissexto;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AnoBissextoTest {

	@Test
	public void deveSerBissexto() {
		
		int ano = 2016;
		
		assertEquals(true, AnoBissexto.ehBissexto(ano));
		
	}
	
}
