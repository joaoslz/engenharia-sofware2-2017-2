package solid.p03.ocp_dip.parte2;

public class CalculadoraDePrecos {

    public double calcula(Compra compra) {
        
    	Desconto tabela = new DescontoPadrao();
        Correios correios = new Correios();

        double desconto = tabela.descontoPara(compra );
        
        double frete = correios.para(compra.getCidade());

        return compra.getValor() * (1-desconto) + frete;
    }
}

