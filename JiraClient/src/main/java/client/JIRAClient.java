package client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.json.JSONException;
import org.json.JSONObject;

import client.service.ServiceInterface;
import client.service.ServiceInterface.Resource;
import ssl.SSLConnection;
import ssl.SSLConnectionManager;
import ssl.SSLConnectionManagerFactory;

public class JIRAClient {
	
	private JIRAClient(){}
	private static File JIRA_CERTIFICATE_FILE = new File(System.getProperty("user.home") + "/.testmanager/.credentials/JIRA.crt");

	private static File CLIENT_SECRET_FILE;
	private static FileInputStream CLIENT_SECRET_INPUT;
	private static BufferedReader CLIENT_SECRET_READER;
	private static FileOutputStream CLIENT_SECRET_OUTPUT;
	
	//private static BufferedReader CLIENT_SECRET = new BufferedReader(new InputStreamReader(JIRAClient.class.getResourceAsStream("/client_secret.json")));
	public static String JIRA_APP_NAME = "jira";
	public static final String JIRA_REST_BASE_URL = "https://" + JIRA_APP_NAME + "/jira/api/latest/";
	
	private static String JIRA_REST_ISSUE_BASE_URL = JIRA_REST_BASE_URL + "issue/{issueID}?expand=editmeta";
	private SSLConnectionManager sslConnectionManager;
	
	static {
		
		try {
			CLIENT_SECRET_FILE = new File(JIRAClient.class.getResource("/client_secret.json").toURI());
			CLIENT_SECRET_INPUT = new FileInputStream(CLIENT_SECRET_FILE);
			CLIENT_SECRET_OUTPUT = new FileOutputStream(CLIENT_SECRET_FILE);
			CLIENT_SECRET_READER = new BufferedReader(new InputStreamReader(CLIENT_SECRET_INPUT));
		} catch (URISyntaxException | FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if (!SSLConnectionManagerFactory.certificateExists("JIRA")){
			System.out.println("Certificate doesn't exist in keystore, attempting to add from app directory.");
		}
		
		setClientCertificate();
		
		if(!JIRA_CERTIFICATE_FILE.exists()){
			System.out.println("Certificate for JIRA is missing, request from user.");

		} else {
			
		}
		
		if(SSLConnectionManagerFactory.getCertificate("JIRA")==null){	
			SSLConnectionManagerFactory.setCertificate(JIRA_CERTIFICATE_FILE, "JIRA");
		}
	}
	
	private static File getCertificateFile(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		java.awt.FileDialog fd = new java.awt.FileDialog((java.awt.Frame) null);
		fd.setAlwaysOnTop(true);
		fd.setTitle("Please select the appropriate JIRA access certificate.");
		fd.setDirectory("C:\\");
		fd.setFile("*.crt");
		fd.setVisible(true);
		File file = new File(fd.getDirectory(),fd.getFile());
		
		return file;
	}
	
	private static void setClientCertificate(){
		
		File file = getCertificateFile();
		String fileType = new StringBuilder(new StringBuilder(file.getAbsolutePath()).reverse().substring(0, 3)).reverse().toString();
		
		if (file.exists() && file.isFile() && fileType.equals("crt")){
			String line = null;
			String contents = "";
			try {
				while((line = CLIENT_SECRET_READER.readLine()) != null){
					contents += line;
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			JSONObject json = null;
			try {
				json = new JSONObject(contents);
			} catch (JSONException e) {
				json = new JSONObject();
			}
			json.put("defaultDirectory", file.getAbsolutePath());
			 
			try {
				CLIENT_SECRET_OUTPUT.write(json.toString().getBytes());
				CLIENT_SECRET_OUTPUT.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			System.out.println("A valid *.crt file was not selected.");
			setClientCertificate();
		}
	}
	
	public JIRAClient(String password){
		this(System.getProperty("user.name"),password);
	}
	
	public JIRAClient(String username, String password){
		this.sslConnectionManager = SSLConnectionManagerFactory.getSSLConnectionManager(password);
	}
	
	public static void setJIRAName(String jiraName){
		JIRAClient.JIRA_APP_NAME = jiraName;
	}
	
	private String getJIRAService(Resource service){
		ServiceInterface.getServiceReference(service);
		return "";
	}
	
	public SSLConnection getJIRAResource(String url){
		return this.sslConnectionManager.getDefaultSSLConnection(url);
	}
	
}
