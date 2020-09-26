
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.mikes.configuration.HibernateContextConfiguration;
import com.mikes.dao.interfaces.QualificationsDao;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateContextConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class TestQualifications extends  AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private QualificationsDao qualificationsDao;

	@Test
	public void testGetAll() {
		int current = qualificationsDao.getQualifications().size();
		assertTrue(current > 0);
	}
}
