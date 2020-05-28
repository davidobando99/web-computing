package david.tdd;



public class StringCalculator {
	//R1
//	public static int add(String numbers) {
//		
//    	int sum=0;
//    	String[] nums=numbers.split(",");
//    	
//    	if(nums.length>2) {
//    		throw new RuntimeException();
//    	}
//    	else if(numbers.equals("")){
//    		return 0;
//    	}
//    	else {
//    		for(int i=0; i<nums.length;i++) {
//    			
//    			sum +=Integer.parseInt(nums[i]);
//    		}
//    	}
//    		return sum;
//    	}
	
	
	//R2
//	public static int add(String numbers) {
//		
//    	int sum=0;
//    	String[] nums=numbers.split(",");
//    	
//    	
//    	if(numbers.equals("")){
//    		return 0;
//    	}
//    	else {
//    		for(int i=0; i<nums.length;i++) {
//    			
//    			sum +=Integer.parseInt(nums[i]);
//    		}
//    	}
//    		return sum;
//    	}
	
public static int add(String numbers) {
		String separator="";
    	int sum=0;
    	String[] nums;
    	
    	if(numbers.contains(",")) {
    		separator=",";
    		nums=numbers.split(separator);
    	}
    	else if(numbers.contains(" ")) {
    		separator=" ";
    		nums=numbers.split(separator);
    	}
    	else {
    	
			throw new RuntimeException();
		}
    	
    	 if(numbers.equals("")){
     		return 0;
     	}
     	else {
     		for(int i=0; i<nums.length;i++) {
     			
     			sum +=Integer.parseInt(nums[i]);
     		}
     	}
     		return sum;
     	}
    
    
		
}


