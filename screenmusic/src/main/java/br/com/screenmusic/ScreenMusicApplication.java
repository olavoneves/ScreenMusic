package br.com.screenmusic;

import br.com.screenmusic.application.ConsoleApplication;
import br.com.screenmusic.repository.ISingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenMusicApplication implements CommandLineRunner {

	@Autowired
	private ISingerRepository iSingerRepository;

	public static void main(String[] args) {
		SpringApplication.run(ScreenMusicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConsoleApplication console;
		try {
			console = new ConsoleApplication(iSingerRepository);
			console.exibirMenu();
		} catch (Exception e) {
			System.out.println("Erro de comunicação com o usuário." + e.getMessage());
		}
	}
}
