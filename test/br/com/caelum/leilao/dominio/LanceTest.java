package br.com.caelum.leilao.dominio;

import org.junit.Test;

public class LanceTest {

	@Test(expected = IllegalArgumentException.class)
	public void naoDeveAceitarLancesDeValorNegativo() {
		new Lance(new Usuario("João"), -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoDeveAceitarLancesDeValorZero() {
		new Lance(new Usuario("Maria"), 0);
	}

}
