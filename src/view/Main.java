package view;

import java.util.concurrent.Semaphore;

import controller.ThreadAeroporto;

public class Main {

	public static void main(String[] args) {
		int permissao = 1;
		Semaphore mutex = new Semaphore(permissao);
		for (int id = 0; id < 12; id++) {
			Thread threadAeroporto = new ThreadAeroporto(id + 1, mutex);
			threadAeroporto.start();
		}
	}

}
