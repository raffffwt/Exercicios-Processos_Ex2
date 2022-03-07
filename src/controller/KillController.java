package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KillController {
	private String os() {
		String os = System.getProperty("os.name");
		return "os-name " + os;
	}
	
	public void listaProcessos() {
		String osName = this.os();
		String process = "";
		
		if (osName.contains("Windows")) {
			process = "TASKLIST /FO TABLE";
		} else if (osName.contains("Linux")) {
			process = "ps -ef";
		}
		
		try {
			Process p = Runtime.getRuntime().exec(process);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void mataPid(int PId) {
		String osName = this.os();
		String process = "";
		
		if (osName.contains("Windows")) {
			process = "TASKKILL /PID ";
		} else if (osName.contains("Linux")) {
			process = "kill -9 ";
		}
		
		try {
			Runtime.getRuntime().exec(process + PId);
			System.out.println("Processo finalizado!");
		} catch (IOException e) {
			System.out.println("Erro na finalizacao do processo: ");
			e.getMessage();
		}
	}
	
	public void mataNome(String processName) {
		String osName = this.os();
		String process = "";
		
		if (osName.contains("Windows")) {
			process = "TASKKILL /IM ";
		} else if (osName.contains("Linux")) {
			process = "pkill -f ";
		}
		
		try {
			Runtime.getRuntime().exec(process + processName);
			System.out.println("Processo finalizado!");
		} catch (IOException e) {
			System.out.println("Erro na finalizacao do processo: ");
			e.getMessage();
		}
	}
}
