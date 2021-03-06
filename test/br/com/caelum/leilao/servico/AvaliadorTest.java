package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class AvaliadorTest {

	private Avaliador leiloeiro;
	private Usuario maria;
	private Usuario jose;
	private Usuario joao;

	@BeforeClass
	public static void testandoBeforeClass() {
		// System.out.println("before class");
	}

	@AfterClass
	public static void testandoAfterClass() {
		// System.out.println("after class");
	}

	@Before
	public void setUp() {
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("João");
		this.jose = new Usuario("José");
		this.maria = new Usuario("Maria");
	}

	/*
	 * Ao usar isso dentro do @Test, o JUnit sabe que só ficará verde se a
	 * Exception for lançada. Demais, não? =)
	 */
	@Test(expected = RuntimeException.class)
	public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.constroi();

		leiloeiro.avalia(leilao);
	}

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		// parte 1: cenário
		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(joao, 250.0));
		leilao.propoe(new Lance(jose, 300.0));
		leilao.propoe(new Lance(maria, 400.0));

		// parte 2: ação
		leiloeiro.avalia(leilao);

		// parte 3: validação
		double maiorEsperado = 400;
		double menorEsperado = 250;
		// double medioEsperado = 316.66666;

		/*
		 * assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		 * assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
		 * assertEquals(medioEsperado, leiloeiro.getValorMedioDosLances(),
		 * 0.00001);
		 */

		// Utilizando o hamcrest
		assertThat(leiloeiro.getMaiorLance(), equalTo(maiorEsperado));
		assertThat(leiloeiro.getMenorLance(), equalTo(menorEsperado));
	}

	@Test
	public void deveEntenderLeilaoComLancesEmOrdemAleatoria() {
		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(joao, 200));
		leilao.propoe(new Lance(maria, 450));
		leilao.propoe(new Lance(joao, 120));
		leilao.propoe(new Lance(maria, 700));
		leilao.propoe(new Lance(joao, 630));
		leilao.propoe(new Lance(maria, 230));

		leiloeiro.avalia(leilao);

		/*
		 * assertEquals(120, leiloeiro.getMenorLance(), 0.00001);
		 * assertEquals(700, leiloeiro.getMaiorLance(), 0.00001);
		 */

		// Utilizando o hamcrest
		assertThat(leiloeiro.getMenorLance(), equalTo(120.0));
		assertThat(leiloeiro.getMaiorLance(), equalTo(700.0));
	}

	@Test
	public void deveEntenderLeilaoComLancesEmOrdemDecrescente() {
		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(joao, 400));
		leilao.propoe(new Lance(maria, 300));
		leilao.propoe(new Lance(joao, 200));
		leilao.propoe(new Lance(maria, 100));

		leiloeiro.avalia(leilao);

		/*
		 * assertEquals(100, leiloeiro.getMenorLance(), 0.00001);
		 * assertEquals(400, leiloeiro.getMaiorLance(), 0.00001);
		 */

		// Utilizando o hamcrest
		assertThat(leiloeiro.getMenorLance(), equalTo(100.0));
		assertThat(leiloeiro.getMaiorLance(), equalTo(400.0));
	}

	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(joao, 1000.0));

		leiloeiro.avalia(leilao);

		/*
		 * assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
		 * assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);
		 */
		assertThat(leiloeiro.getMaiorLance(), equalTo(leiloeiro.getMaiorLance()));
	}

	@Test
	public void deveEncontrarOsTresMaioresLances() {

		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 100.0).lance(maria, 200.0).lance(joao, 300.0)
				.lance(maria, 400.0).constroi();

		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();

		// assertEquals(3, maiores.size());

		// Utilizando o hamcrest
		assertThat(3, equalTo(maiores.size()));

		/*
		 * assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
		 * assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
		 * assertEquals(200.0, maiores.get(2).getValor(), 0.00001);
		 */

		// Usando o hamcrest:
		// Esse matcher recebe uma lista de itens e garante que esses itens
		// existem na lista que passamos
		assertThat(
				maiores,
				hasItems(new Lance(maria, 400), new Lance(joao, 300),
						new Lance(maria, 200)));
	}

	@After
	public void finaliza() {
		// System.out.println("fim");
	}

}
