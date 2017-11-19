package solid.p05.liskov.cenario1.parte2;

// Usado para interagir com o back-end da aplicação
public class UIController {
	
	private DataReadRepository dataReadRepository;
	private DataWriteRepository dataWriteRepository;
	
	
	
	public void persist(Object object) {
		
		if (dataWriteRepository != null ) {
			dataWriteRepository.persist(object);
		}
	}
	
	public void retrieve(long id) {
		if (dataReadRepository != null ) {
			dataReadRepository.read(id);
		}
	}

}
