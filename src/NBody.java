import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {
	
	public static void main(String[] args){
		double totalTime = 10000000.0;
		double dt = 25000.0;
		String pfile = "data/planets.txt";
		
		if (args.length > 2) {
			totalTime = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			pfile = args[2];
		}
		
		String fname= "./data/planets.txt";
		Planet[] planets = readPlanets(fname);
		double radius = readRadius(fname);
		
	
		StdDraw.picture(0,0,"images/starfield.jpg");
		
		
		
	    
		//System.out.printf("%d\n", planets.length);
		//System.out.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    //System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		       //       planets[i].myXPos, planets[i].myYPos, 
		                 //     planets[i].myXVel, planets[i].myYVel, 
		                   //   planets[i].myMass, planets[i].myFileName);	
		}
		
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0,0,"images/starfield.jpg");
		
		for(double t = 0.0; t < totalTime; t += dt) {
			
		double[] xForces = new double[planets.length];	
		double[] yForces = new double[planets.length];
		
		for(int i=0;i<planets.length;i++) {
			xForces[i]=planets[i].calcNetForceExertedByX(planets);
			yForces[i]=planets[i].calcNetForceExertedByY(planets);
		}
		
		for(int i=0;i<planets.length;i++) {
			planets[i].update (dt, xForces[i], yForces[i]);
			
		}
		
		StdDraw.picture(0,0,"images/starfield.jpg");
		
		for(int i=0;i<planets.length;i++) {
			planets[i].draw();
		}
		
		
		StdDraw.show(10);
	
		}
		
		System.out.printf("%d\n", planets.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                     planets[i].myXPos, planets[i].myYPos, 
		                      planets[i].myXVel, planets[i].myYVel, 
		                      planets[i].myMass, planets[i].myFileName);	
		}
		
	
		/*for(int i=0;i<planets.length;i++) {
			planets[i].draw();
		}*/
		
	}
	
	public static double readRadius(String fname) {
		try {
	        Scanner scan = new Scanner(new File(fname));
	        scan.nextInt();
	        double value = scan.nextDouble();
	        

	        scan.close();
	        return value;   // must return a double here
	    } catch (FileNotFoundException e) {
	        System.out.println("Error Reading FileName");
	        System.exit(0);
	    }
		return 0;
		
		
	}
	
	public static Planet[] readPlanets(String fname) {
		
		int totalPlanets =0;
		
		try {
	        Scanner scan = new Scanner(new File(fname));
	        totalPlanets =scan.nextInt();
	        scan.close();
	          
	    } catch (FileNotFoundException e) {
	        System.out.println("Error Reading FileName");
	        System.exit(0);
	    }
		
		Planet[] allPlanets = new Planet[totalPlanets];
		//System.out.println(totalPlanets);
		
		
			
			
			try {
				Scanner scan = new Scanner(new File(fname));

		        scan.nextInt();
		        scan.nextDouble();
		       
				for(int runner =0;runner<totalPlanets;runner++) {
		        
		        
		        allPlanets[runner] = new Planet(scan.nextDouble(),scan.nextDouble(),scan.nextDouble(),scan.nextDouble(),scan.nextDouble(),scan.next());
		        
		        if(runner==totalPlanets-1) {
		        	  scan.close();
		        }
				}
		       // scan.close();
		          
		    } catch (FileNotFoundException e) {
		        System.out.println("Error Reading FileName");
		        System.exit(0);
		    }
			
			
			
			
		
		
		return allPlanets;
		
		
		
		
		
	}
}
