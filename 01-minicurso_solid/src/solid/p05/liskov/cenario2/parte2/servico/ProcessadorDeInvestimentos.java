package solid.p05.liskov.cenario2.parte2.servico;

import java.util.Arrays;
import java.util.List;

import solid.p05.liskov.cenario2.parte1.ContaComum;

public class ProcessadorDeInvestimentos {
	
    public static void main(String[] args) {

        for (ContaComum conta : contasDoBanco()) {
            conta.somaInvestimento();

            System.out.println("Novo Saldo:");
            System.out.println(conta.getSaldo());
        }
    }

    private static List<ContaComum> contasDoBanco() {
        return Arrays.asList(umaContaCom(100), 
        		             umaContaCom(150),
                             umaContaCom(200) );
    }

    private static ContaComum umaContaCom(double valor) {
        ContaComum c = new ContaComum();
        c.deposita(valor);
        return c;
    }

}

