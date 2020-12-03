package residents_test;

import java.util.List;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;

public class ResidentRepositoryStub implements ResidentRepository {

	List <Resident> residents;
	public ResidentRepositoryStub(List<Resident> lis) {
		residents = lis;
	}
	
	@Override
	public List<Resident> getResidents() {
		return residents;
	}

}
		