

public class Lookup {
	
	//the variable will be filled in when the variable is initialized
	
	static final double[] cos = cos();
	static final double[] sin = sin();
	
	public static double[] cos()
	{
		double[] cos = new double[360];
		
		//this loop will fill up all the possible cosine of all angle degree from 0 to 360
		for(int A = 0; A<360; A++ )
			
			cos[A] = Math.cos(A *Math.PI/180);
			
		return cos;
	}
	
	public static double[] sin()
	{

		double[] sin = new double[360];
		
		//this loop will fill up all the possible cosine of all angle degree from 0 to 360
		for(int A = 0; A<360; A++ )
			
			sin[A] = Math.sin(A *Math.PI/180);
			
		return sin;
	
	}
	
	

}
