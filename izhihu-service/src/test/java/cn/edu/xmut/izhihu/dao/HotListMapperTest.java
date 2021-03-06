package cn.edu.xmut.izhihu.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author: qiuguanlin
 * @Date: 2019-06-29 23:40
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HotListMapperTest {


    @Autowired
    HotListMapper hotListMapper;

    @Test
    public void list() {
        hotListMapper.listByCategoryType(0);
    }
}