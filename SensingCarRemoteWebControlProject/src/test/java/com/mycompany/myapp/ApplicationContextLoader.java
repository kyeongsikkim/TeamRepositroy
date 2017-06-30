package com.mycompany.myapp;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)			//testing��  �����ϴ� ���� Ŭ����, junutrunner ���� ������̼� �ν� �Ұ�, ���� �������� �����ϴ� �� ��
@ContextConfiguration({							//������ ���������� �˷��ش�
	"file:WebContent/WEB-INF/spring/applicationContext.xml",
	"file:WebContent/WEB-INF/spring/dispatcher-servlet.xml"
})
//@WebAppConfiguration() -> �� �������� src/main/webapp/WEB-INF �ؿ� �����ؾ� ��
@WebAppConfiguration("WebContent")
public class ApplicationContextLoader {

}
