package clases;
import java.util.Random;

public class numeros 
{
	private double _numero;
	
	public numeros() 
	{
		double rndNum = new Random().nextDouble();
		
		_numero=Math.round(rndNum * (100)*100)/100;;
	}

	public double get_numero() 
	{
		return _numero;
	}

	
	
}
