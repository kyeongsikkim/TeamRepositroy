package com.mycompany.myapp;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)			//testing을  제공하는 실행 클래스, junutrunner 쓰면 어노테이션 인식 불가, 따라서 스프링이 제공하는 것 씀
@ContextConfiguration({							//스프링 설정파일을 알려준다
	"file:WebContent/WEB-INF/spring/applicationContext.xml",
	"file:WebContent/WEB-INF/spring/dispatcher-servlet.xml"
})
//@WebAppConfiguration() -> 웹 컨텐츠가 src/main/webapp/WEB-INF 밑에 존재해야 함
@WebAppConfiguration("WebContent")
public class ApplicationContextLoader {

}
