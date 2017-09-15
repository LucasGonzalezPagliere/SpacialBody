
public class Planet {

	double myXPos;            // current x position
	double myYPos;            // current y position
	double myXVel;            // current velocity in x direction 
	double myYVel;            // current velocity in y direction
	double myMass;            // mass of planet
	String myFileName;        // file name (in images folder) 
	double G = 6.67 * Math.pow(10, -11);
	
	public Planet(double xp, double yp, double xv,
            double yv, double mass, String filename) {
		
		myXPos=xp;
		myYPos=yp;
		myXVel=xv;
		myYVel=yv;
		myMass=mass;
		myFileName=filename;
		
	}
	
	public Planet(Planet p) {
		
		myXPos = p.myXPos;
		myYPos = p.myYPos;
		myXVel = p.myXVel;
		myYVel = p.myYVel;
		myMass = p.myMass;
		myFileName=p.myFileName;
		
		
	}
	
	public double calcDistance(Planet p) {
		
		double x1 = this.myXPos;
		double x2 = p.myXPos;
		double y1 = this.myYPos;
		double y2= p.myYPos;
		
		double insidex = Math.pow((x1-x2), 2);
		double insidey = Math.pow((y1-y2), 2);
				
		
		
		return Math.sqrt(insidex+insidey);
		
	}
	
	public double calcForceExertedBy(Planet p) {
		
		double r = this.calcDistance(p);
		
		double B = (G*this.myMass*p.myMass);
		
		double F = B/Math.pow(r, 2);
		
		return F;		
	}
	
	public double calcForceExertedByX(Planet p) {
		
		double Top = this.calcForceExertedBy(p) * (p.myXPos-this.myXPos);
		
		double r = this.calcDistance(p);
		
		return Top/r;	
	}
	
	
	
	public double calcForceExertedByY(Planet p) {
		
		double Top = this.calcForceExertedBy(p) * (p.myYPos-this.myYPos);
		
		double r = this.calcDistance(p);
		
		return Top/r;
	}
	
	
	public double calcNetForceExertedByX(Planet[] allPlanets) {
		
		double sum = 0;
		
		
		for(int i=0; i<allPlanets.length; i++ ) {
		
		Planet p = allPlanets[i];
			
		if (! p.equals(this)) {
		    sum += calcForceExertedByX(p);
		}

		}
		
		
		return sum;
	}
	
	public double calcNetForceExertedByY(Planet[] allPlanets) {
		
		double sum = 0;
		
		
		for(int i=0; i<allPlanets.length; i++ ) {
		
		Planet p = allPlanets[i];
			
		if (! p.equals(this)) {
		    sum += calcForceExertedByY(p);
		}

		}
		
		
		return sum;
	}
	
	public void update(double seconds, double xforce, double yforce) {
		
		double accelX = xforce/this.myMass;
		double accelY = yforce/this.myMass;
		
		this.myXVel = this.myXVel + seconds * accelX;
		this.myYVel = this.myYVel + seconds * accelY;
		
		this.myXPos = this.myXPos + seconds * this.myXVel;
		this.myYPos = this.myYPos + seconds * this.myYVel;
		
	}
	
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
	
}
