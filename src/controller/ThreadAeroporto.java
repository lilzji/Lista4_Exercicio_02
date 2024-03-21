package controller;

import java.util.concurrent.Semaphore;

public class ThreadAeroporto extends Thread {

	private int id;
	private int pista = (int) ((Math.random() * 2) + 1);
	private Semaphore mutex;

	public ThreadAeroporto(int id, Semaphore mutex) {
		this.id = id;
		this.mutex = mutex;
	}

	public void run() {
		if (pista == 1) {
			manobra();
			taxiar();
			// SECAO CRITICA
			System.out.println("Aviao " + id + " entrando em secao critica");
			try {
				mutex.acquire();
				decolagem();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				mutex.release();
				// FIM DA SECAO CRITICA
			}
		} else {
			System.out.println("Aviao " + id + " entrando em secao critica");
			try {
				mutex.acquire();
				decolagem();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				mutex.release();
				// FIM DA SECAO CRITICA
			}
		}
		afastamento();
	}

	private void manobra() {
		try {
			sleep((int) ((Math.random() * 401) + 300));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void taxiar() {
		try {
			sleep((int) ((Math.random() * 501) + 500));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void decolagem() {
		try {
			sleep((int) ((Math.random() * 201) + 600));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Aviao " + id + " decolou!");
	}

	private void afastamento() {
		try {
			sleep((int) ((Math.random() * 501) + 300));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
