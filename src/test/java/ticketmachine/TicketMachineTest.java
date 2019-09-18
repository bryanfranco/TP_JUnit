package ticketmachine;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@Before
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation
	public void priceIsCorrectlyInitialized() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
		assertEquals("Initialisation incorrecte du prix", PRICE, machine.getPrice());
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	public void insertMoneyChangesBalance() throws Exception {
		machine.insertMoney(10);
		machine.insertMoney(20);
		assertEquals("La balance n'est pas correctement mise à jour", 10 + 20, machine.getBalance()); // Les montants ont été correctement additionnés               
	}
        
        @Test
        public void ticketNotPrinted() throws Exception{
            this.machine.insertMoney(20);
            assertFalse(machine.printTicket());
        }
        
        @Test
        public void tickedIsPrinted() throws Exception{
            this.machine.insertMoney(PRICE);
            assertTrue(machine.printTicket());
        }
        
        @Test
        public void balanceIsReduced() throws Exception{
            this.machine.insertMoney(PRICE);
            this.machine.printTicket();
            assertEquals(0,this.machine.getBalance());
        }
        
        @Test
        public void totalIsUpdated() throws Exception{
            this.machine.insertMoney(PRICE);
            this.machine.printTicket();
            assertEquals(PRICE,this.machine.getTotal());
        }
        
        @Test
        public void refundGiveRightAmount() throws Exception{
            this.machine.insertMoney(40);
            assertEquals(machine.refund(), 40);
        }
        
        @Test
        public void balanceAtZeroAfterRefund() throws Exception{
            this.machine.insertMoney(20);
            this.machine.refund();
            assertEquals(this.machine.getBalance(),0);
        }
        
        @Test(expected = Exception.class)
        public void cannotInsereNegativeAmount() throws Exception{
            this.machine.insertMoney(-20);
        }
        
        @Test(expected = IllegalArgumentException.class)
        public void priceNotNegative(){
            TicketMachine tick = new TicketMachine(-PRICE);
        }

}
