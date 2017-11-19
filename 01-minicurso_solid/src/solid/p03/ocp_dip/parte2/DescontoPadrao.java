package solid.p03.ocp_dip.parte2;

public class DescontoPadrao implements Desconto {
	
    @Override
	public double descontoPara(Compra compra) {
        
    	double valor = compra.getValor();
    	
		if(valor > 5000) 
        	return 0.03;
        
        if(compra.getValor() >1000) 
        	return 0.05;
        
        return 0;
    }
}

