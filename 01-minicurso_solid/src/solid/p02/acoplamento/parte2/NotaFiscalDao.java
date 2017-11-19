package solid.p02.acoplamento.parte2;

public class NotaFiscalDao implements AcaoAposGerarNotaFiscal {

	@Override
	public void executa(NotaFiscal notaFiscal) {
		System.out.println("Salvando no banco dados" );
		
	}
}
