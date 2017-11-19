
package solid.p02.acoplamento.parte2;

public class EnviadorDeEmail implements AcaoAposGerarNotaFiscal {

	@Override
	public void executa(NotaFiscal notaFiscal) {
		System.out.println("Enviando por email ...");
		
	}

}

