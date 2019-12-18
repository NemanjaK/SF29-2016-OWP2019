package servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import dao.ConnectionManager;

public class InitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ConnectionManager.close();
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("inicijalizacija...");
		
		ConnectionManager.open();
				
		System.out.println("zavrseno!");
	}

}
