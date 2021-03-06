package br.com.k21;

import junit.framework.Assert;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.com.k21.infraestrutura.BaseAcceptanceTest;

public class LoginAcceptanceTest extends BaseAcceptanceTest {

	private static final String URL = "http://localhost:8080/AceitacaoComJava/login.seam";

	@Test
	public void teste_login_existente_mas_com_senha_errada_gera_entrada_na_tabela_FalhaLogin_e_exibe_mensagem() {
		Integer entradasEsperadasNaTabela = 1;
		String texto_mensagem_falha_login = "Login Failed!";
 
		// act
		driver.get(URL);

		driver.findElement(By.id("loginForm:username")).sendKeys("cfc");
		driver.findElement(By.id("loginForm:password")).sendKeys("cfc");
		driver.findElement(By.id("loginForm:submit")).click();

		WebElement element = driver.findElement(By
				.xpath("id('messages')/li[2]"));

		Assert.assertEquals(texto_mensagem_falha_login, element.getText());

		Integer resultadoEntradasFalhaLog = (Integer) emf.createEntityManager()
				.createNativeQuery("select count(*) from FalhaLogin")
				.getSingleResult();

		Assert.assertEquals(entradasEsperadasNaTabela,
				resultadoEntradasFalhaLog);

	}
	

	@Test
	public void teste_login_com_sucesso_gera_entrada_na_tabela_SucessoLogin() {
		Integer entradasEsperadasNaTabela = 1;
		String texto_mensagem = "Welcome, cfc";
 
		// act
		driver.get(URL);

		driver.findElement(By.id("loginForm:username")).sendKeys("cfc");
		driver.findElement(By.id("loginForm:password")).sendKeys("123456");
		driver.findElement(By.id("loginForm:submit")).click();

		WebElement element = driver.findElement(By
				.xpath("id('messages')/li[2]"));

		Assert.assertEquals(texto_mensagem, element.getText());

		Integer resultadoEntradasSucessoLog = (Integer) emf.createEntityManager()
				.createNativeQuery("select count(*) from SucessoLogin")
				.getSingleResult();

		Assert.assertEquals(entradasEsperadasNaTabela,
				resultadoEntradasSucessoLog);

	}
}
