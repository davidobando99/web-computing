package david.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	
	public StringCalculator calculator;
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    
 
    @Test 
    public void testAdd0NoExceptionIsThrown() {
    	//StringCalculator.add("");
    	assertEquals(StringCalculator.add(""),0);
    	
    }
    
    @Test 
    public void testAdd1NoExceptionIsThrown() {
    	StringCalculator.add("4");
    	assertTrue(true);
    	
    }
    @Test 
    public void testAdd2NoExceptionIsThrown() {
    	StringCalculator.add("4,3");
    	assertTrue(true);
    	
    }
    
    @Test 
    @Disabled
    public final void testAdd3ExceptionIsThrown() {
    	 
    	assertThrows(RuntimeException.class,() -> {
    		StringCalculator.add("1,2,3");
    	});
    	
    	
    	
    }
    @Test
    public final void testAddUnknownNoExceptionIsThrown() {
//    	String numbers="";
//    	for(int i=0;i<Integer.parseInt(Math.random()+"");i++) {
//    		numbers+=i+",";
//    	}
    	assertEquals(StringCalculator.add("3,4,6,7"),20);
    	
    
    	
    }
   
    @Test 
    public final void testAddNoNumberExceptionIsThrown() {
    	
    	assertThrows(RuntimeException.class,() -> {
    		StringCalculator.add("1,A");
    	});
    	
    	
    	
    }
    
    @Test 
    public void testAddSum1NoExceptionIsThrown() {
    	
    	assertEquals(StringCalculator.add("4"),4);
    	
    }
    
    @Test 
    public void testAddSum2NoExceptionIsThrown() {
    	;
    	assertEquals(StringCalculator.add("4,3"),7);
    	
    }
    
    @Test 
    public void testAddR2NoExceptionIsThrown() {
    	
    	assertEquals(StringCalculator.add("4,3"),7);
    	assertEquals(StringCalculator.add("4 3"),7);
    }
    
    
    @Test 
    public void testAddR2ExceptionIsThrown() {
    	
    	assertThrows(RuntimeException.class,() -> {
    		StringCalculator.add("1\2");
    	});
    }
    
    
    
    
}
