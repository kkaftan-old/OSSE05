package residents_test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;

public class ResidentMock {
	
	private Resident resident, resident2, resident3;
    private BaseResidentService baseResidentService;
    List<Resident> lis = new ArrayList <Resident>();
    
	@Before
	public void init() {
		baseResidentService = new BaseResidentService();
		resident = new Resident("Kevin", "Kaftan", "Strasse","FuWa", new Date(1998,6,11));
		resident2 = new Resident("Kowa", "Assw", "Rue","Magd", new Date(2002,4,23));
		resident3 = new Resident("Eree", "Awawha", "Street","Yoak", new Date(1977,2,4));
		lis.add(resident);
		lis.add(resident2);
		lis.add(resident3);
	}
	
	@Test
	public void getFilteredResidentListTest() {
		ResidentRepository residentRepositoryMock = createMock(ResidentRepository.class);
		baseResidentService = new BaseResidentService();
		baseResidentService.setResidentRepository(residentRepositoryMock);
		
		
		expect(residentRepositoryMock.getResidents()).andReturn(lis);
		replay(residentRepositoryMock);		
		
		Resident hlp = new Resident("Kevin", "","","",null);
		Resident erg = baseResidentService.getFilteredResidentsList(hlp).get(0);
		verify(residentRepositoryMock);
		assertThat(erg, equalTo(lis.get(0)));
	}
	
	@Test
	public void getUniqueResidentTest() throws ResidentServiceException {
		ResidentRepository residentRepositoryMock = createMock(ResidentRepository.class);
		baseResidentService = new BaseResidentService();
		baseResidentService.setResidentRepository(residentRepositoryMock);
		
		expect(residentRepositoryMock.getResidents()).andReturn(lis);
		replay(residentRepositoryMock);			
	
		Resident hlp = new Resident("Kevin", "","","",null);
		Resident erg = baseResidentService.getUniqueResident(hlp);
		
		verify(residentRepositoryMock);
		assertThat(erg, equalTo(lis.get(0)));
			
		
	}

}
