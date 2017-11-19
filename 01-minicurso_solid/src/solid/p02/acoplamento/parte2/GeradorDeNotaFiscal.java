package solid.p02.acoplamento.parte2;

import java.util.ArrayList;
import java.util.List;

public class GeradorDeNotaFiscal {

    private List<AcaoAposGerarNotaFiscal> acoes = new ArrayList<>();
    
    public GeradorDeNotaFiscal() {
	}

	public GeradorDeNotaFiscal(List<AcaoAposGerarNotaFiscal> acoes) {
		this.acoes = acoes;
    }

    public NotaFiscal gera(Fatura fatura) {

        double valor = fatura.getValorMensal();

        NotaFiscal nf = new NotaFiscal(valor, fatura.impostoSimplesSobreOValor() );
        
        
        // assíncrona
        for (AcaoAposGerarNotaFiscal acao : acoes) {
			acao.executa(nf);
		}
        return nf;
    }


}
