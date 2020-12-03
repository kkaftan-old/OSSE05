package residents_test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.*;

import de.hfu.residents.domain.*;
import de.hfu.residents.service.*;

public class ResidentServiceTest {
	private Resident resident, resident2, resident3;
    private BaseResidentService baseResidentService;

	
	@Before
	public void init() {
		baseResidentService = new BaseResidentService();
		resident = new Resident("Kevin", "Kaftan", "Strasse","FuWa", new Date(1998,6,11));
		resident2 = new Resident("Kowa", "Assw", "Rue","Magd", new Date(2002,4,23));
		resident3 = new Resident("Eree", "Awawha", "Street","Yoak", new Date(1977,2,4));
	}
	
	
	
	//++getFilteredResidentsList() TEST++
	@Test
	public void getFilteredResidentsListTest() throws ResidentServiceException {
		List <Resident> lis = new ArrayList<Resident>();
		lis.add(resident);
		lis.add(resident2);
		lis.add(resident3);
		
		ResidentRepositoryStub residentRepositoryStub = new ResidentRepositoryStub(lis);
		BaseResidentService baseResidentService = new BaseResidentService();
		baseResidentService.setResidentRepository(residentRepositoryStub);
		
		//no-result
		Resident no = new Resident("P*","","","",null);
		Assert.assertEquals(0, baseResidentService.getFilteredResidentsList(no).size());
		
		
		//one-result
		Resident eins = new Resident("K*","","","",null);
		Resident erg = residentRepositoryStub.residents.get(0);
		Assert.assertEquals(erg,(baseResidentService.getFilteredResidentsList(eins).get(0)));
		
		//multiple-result
		List <Resident> zwei = new ArrayList<>();
		zwei.add(residentRepositoryStub.residents.get(0));
		zwei.add(residentRepositoryStub.residents.get(1));
		Resident erg2 = new Resident ("","","S*","",null);
		Assert.assertEquals(zwei,(baseResidentService.getFilteredResidentsList(erg2)));
		
		Resident test1 = new Resident("","A*","","",null);
		Assert.assertEquals(erg,(baseResidentService.getFilteredResidentsList(test1)));
		
		Resident test2 = new Resident("","","St*","",null);
		Assert.assertEquals(erg,(baseResidentService.getFilteredResidentsList(test2)));
		
		Resident test3 = new Resident("","","","Y*",null);
		Assert.assertEquals(erg,(baseResidentService.getFilteredResidentsList(test3)));
		
		Resident test4 = new Resident("","","","",new Date(1885,26,12));
		Assert.assertEquals(erg,(baseResidentService.getFilteredResidentsList(test4)));
		
		
	}
		
	//++getUniqueResident() TEST++
	@Test
	public void UniqueResidentTest1() throws ResidentServiceException{
		List<Resident> lis = new ArrayList<>();
		
		lis.add(resident);
	    lis.add(resident2);
	    lis.add(resident3);
	    
	    ResidentRepositoryStub residentRepositoryStub = new ResidentRepositoryStub(lis);
		BaseResidentService baseResidentService = new BaseResidentService();
		baseResidentService.setResidentRepository(residentRepositoryStub);
		
		resident = baseResidentService.getUniqueResident(resident2);
		
		try {
			resident = baseResidentService.getUniqueResident(new Resident("Max", "Mustermann", "Mastrasse", "Macity", null));
		} catch (ResidentServiceException e){
			
		}
	    	
	}
	
	@Test
	public void UniqueResidentTest2() throws ResidentServiceException{
		List<Resident> lis = new ArrayList<>();
		
		lis.add(resident);
	    lis.add(resident2);
	    lis.add(resident3);
	    
	    ResidentRepositoryStub residentRepositoryStub = new ResidentRepositoryStub(lis);
		BaseResidentService baseResidentService = new BaseResidentService();
		baseResidentService.setResidentRepository(residentRepositoryStub);
		
		try {
			resident = baseResidentService.getUniqueResident(new Resident("*", "*", "*", "*", null));
		} catch (ResidentServiceException e) {
			assertEquals("Wildcards * sind nicht erlaubt", e.getMessage());
		}
	}
	
	
	@Test
	public void UniqueResidentTest3() throws ResidentServiceException{
		List<Resident> lis = new ArrayList<>();
		
		lis.add(resident);
	    lis.add(resident2);
	    lis.add(resident3);
	    
	    ResidentRepositoryStub residentRepositoryStub = new ResidentRepositoryStub(lis);
		BaseResidentService baseResidentService = new BaseResidentService();
		baseResidentService.setResidentRepository(residentRepositoryStub);
		
		try {
			Resident testResident = baseResidentService.getUniqueResident(new Resident("Kevin", "Kaftan", "Strasse", "FuWa", new Date(1998,6,11)));
			assertEquals("Kevin", testResident.getGivenName());
			assertEquals("Kaftan", testResident.getFamilyName());
			assertEquals("Strasse", testResident.getStreet());
			assertEquals("FuWa", testResident.getCity());
		} catch(ResidentServiceException e) {
			assertEquals("Nicht eindeutig identifizierbar", e.getMessage());
		}
		
		
	}
	
	
}//end 

