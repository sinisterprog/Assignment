package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.mikes.configuration.HibernateContextConfiguration;
import com.mikes.dao.interfaces.EngineerDao;
import com.mikes.model.Engineer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateContextConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class TestEngineer {

	private static final String FIRST_NAME = "Dave";
	private static final String FAMILY_NAME = "Test";
	private static final String EMAIL_ADDR = "somename@somewhere.com";
	private static final String TELEPHONE = "123456";
	private static final char GENDER = 'M';
	private static final String WEBSITE = "www.test.com";
	private static final String ADDRESS1 = "Some House";
	private static final String ADDRESS2 = "Some Street";
	private static final String ADDRESS3 = "Some Where";
	private static final String REMARKS = "some remarks";
	private static final String TECHNICAL_SKILLS = "Technical skills";
	private static final String PHOTO_ID = "test.jpg";
	@Autowired
	private EngineerDao engineerDao;

	private static Engineer getEngineer() {

		Date date = new Date();
		Engineer engineer = new Engineer();
		engineer.setEmail_addr(EMAIL_ADDR);
		engineer.setFirst_name(FIRST_NAME);
		engineer.setLast_name(FAMILY_NAME);
		engineer.setDob(date);
		engineer.setGender('M');
		engineer.setMobile_number(TELEPHONE);
		engineer.setAddress(ADDRESS1);

		engineer.setWebsite(WEBSITE);
		engineer.setRemarks(REMARKS);
		engineer.setTechnical_skills(TECHNICAL_SKILLS);
		engineer.setPhoto_id(PHOTO_ID);
		return engineer;
	}

	@Test
	public void testPersist() {
		Engineer engineer = getEngineer();
		assertNull(engineer.getEngineer_id());
		engineerDao.saveOrUpdate(engineer);
		assertNotNull(engineer.getEngineer_id());

	}
	@Test
	public void testGetAll() {
		int current = engineerDao.getEngineers().size();
		Engineer engineer = getEngineer();
		engineerDao.saveOrUpdate(engineer);
		assertEquals (current+1, engineerDao.getEngineers().size());
	}
}
