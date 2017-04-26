package com.bpedroso.ranking;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class RankingApplication {

	private static final String BASE_PACKAGE = "com.socialpoint.bpedroso.controller";
	private static final String BASE_PATH = "/*";
	private static final int SERVER_PORT = 8080;

	public static void main(String[] args) throws Exception {
		ResourceConfig resourceConfig = new ResourceConfig();
		resourceConfig.packages(BASE_PACKAGE);
		ServletHolder jerseyServlet = new ServletHolder(new ServletContainer(resourceConfig));

		Server jettyServer = new Server(SERVER_PORT);
		ServletContextHandler context = new ServletContextHandler(jettyServer, BASE_PATH);
		context.addServlet(jerseyServlet, BASE_PATH);

		try {
			jettyServer.start();
			jettyServer.join();
		} finally {
			jettyServer.destroy();
		}

	}

}
