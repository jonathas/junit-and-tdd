package br.com.caelum.palindromo;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.palindromo.Palindromo;

public class PalindromoTest {

	@Test
    public void deveIdentificarPalindromoEFiltrarCaracteresInvalidos() {
        Palindromo p = new Palindromo();

        boolean resultado = p.ehPalindromo(
            "Socorram-me subi no onibus em Marrocos");
        Assert.assertTrue(resultado);
    }

    @Test
    public void deveIdentificarPalindromo() {
        Palindromo p = new Palindromo();

        boolean resultado = p.ehPalindromo(
            "Anotaram a data da maratona");
        Assert.assertTrue(resultado);
    }

    @Test
    public void deveIdentificarSeNaoEhPalindromo() {
        Palindromo p = new Palindromo();

        boolean resultado = p.ehPalindromo(
            "E preciso amar as pessoas como se nao houvesse amanha");
        Assert.assertFalse(resultado);
    }	
	
	
}
