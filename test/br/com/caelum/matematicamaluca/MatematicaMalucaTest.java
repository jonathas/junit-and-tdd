package br.com.caelum.matematicamaluca;



import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MatematicaMalucaTest {
	
	@Test
	public void testaValoresMatematicaMaluca() {
		
		MatematicaMaluca matmaluca = new MatematicaMaluca();
		
		int numMaiorQue30 = matmaluca.contaMaluca(31);
		int numMaiorQue10 = matmaluca.contaMaluca(11);
		int numMenorQue10 = matmaluca.contaMaluca(9);
		
		assertEquals(31 * 4, numMaiorQue30, 0.00001);
		assertEquals(11 * 3, numMaiorQue10, 0.00001);
		assertEquals(9 * 2, numMenorQue10, 0.00001);
	}
	
	
}
