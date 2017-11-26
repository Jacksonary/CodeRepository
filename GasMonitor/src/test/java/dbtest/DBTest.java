package dbtest;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hhu.dao.UserDao;
import com.hhu.entity.Station;
import com.hhu.entity.User;
import com.hhu.service.FaultDiagnosisService;
import com.hhu.service.StationParaService;
import com.hhu.service.StationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="../spring/*.xml")
public class DBTest {

	public ApplicationContext ctxt;

    public void setApplicationContext(ApplicationContext arg0)
            throws BeansException {
        this.ctxt = arg0;
    }

    @Resource
    private UserDao userDao;

    @Test
    public void testSql(){
        User user = userDao.getUser("zh", "123");
        System.out.println(user);
    }
    
    
    
    @Resource
    private StationService stationService;
    
    @Resource
    private StationParaService stationParaService;
    
    @Resource
    private FaultDiagnosisService faultDiagnosisService;
    
    @Test
    public void testFor() {
    	List<Station> list = stationService.getAllStation2();
    	faultDiagnosisService.datasAnalysis2(list);
    }
}
