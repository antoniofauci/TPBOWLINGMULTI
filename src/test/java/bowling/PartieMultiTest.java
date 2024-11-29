package bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PartieMultiTest {
	PartieMulti parties;
	String[] joueurs = {"j1", "j2", "j3"};

	@BeforeEach
	public void setUp() {
		parties = new PartieMulti();
		parties.demarreNouvellePartie(joueurs);
	}

	@Test
	void testPartieNondemarre() {
		PartieMulti partiesTest = new PartieMulti();
		try {
			partiesTest.enregistreLancer(7);
			fail("ne catch pas quand la partie n'est pas démarrée");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testDemarrerTableauVide() {
		PartieMulti partiesTest = new PartieMulti();
		String[] j = {};
		try {
			partiesTest.demarreNouvellePartie(j);
			fail("ne catch pas quand le tableau est vide");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testinitiationPartie() {
		PartieMulti partiesTest = new PartieMulti();
		assertEquals("Prochain tir : joueur j1, tour n° 1, boule n° 1", parties.demarreNouvellePartie(joueurs), "partie non correctement initialisé");
	}

	@Test
	void testApresUnStrike() {
		assertEquals("Prochain tir : joueur j2, tour n° 1, boule n° 1", parties.enregistreLancer(10));
	}

	@Test
	void testScoreUnknownPlayer() {
		try {
			parties.scorePour("j4");
			fail("Do not catch when unknown player");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testScorePartieLambda() {
		parties.enregistreLancer(10);//j1
		parties.enregistreLancer(3);//j2
		parties.enregistreLancer(5);//j2
		parties.enregistreLancer(10);//j3
		parties.enregistreLancer(5);//j1
		parties.enregistreLancer(4);//j1
		parties.enregistreLancer(4);//j2
		parties.enregistreLancer(6);//j2
		parties.enregistreLancer(8);//j3
		parties.enregistreLancer(1);//j3
		parties.enregistreLancer(10);//j1
		parties.enregistreLancer(2);//j2
		parties.enregistreLancer(4);//j2
		parties.enregistreLancer(7);//j3


		assertEquals(38, parties.scorePour("j1"));
		assertEquals(26, parties.scorePour("j2"));
		assertEquals(35, parties.scorePour("j3"));
	}

	@Test
	void testPartieTermine() {
		int cpt = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 6; j++) {
				parties.enregistreLancer(1);
			}
		}
		assertEquals("Partie terminée", parties.enregistreLancer(1));

	}
}