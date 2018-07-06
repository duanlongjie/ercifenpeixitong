package com.ercifenpeixitong.ercifenpeixitong.service;

import com.ercifenpeixitong.ercifenpeixitong.dao.DeclarationDao;
import com.ercifenpeixitong.ercifenpeixitong.domain.Declaration;
import com.ercifenpeixitong.ercifenpeixitong.domain.Standard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeServiceTest {
    @Resource
    private DeclarationDao declarationDao;
    @Test
    public void t(){
        Optional<Declaration> op = declarationDao.findById("1");

        Declaration declaration = op.get();
        System.out.println(declaration);
        Standard s=new Standard();
        s.setStandard("1000");
        declaration.getStandards().add(s);
        Declaration save = declarationDao.save(declaration);


    }
}
