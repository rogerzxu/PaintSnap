package colorLibrary;

public class Lab {
	
	public double L;
	public double a;
	public double b;
    private double e = 216 / 24389;
    private double k = 24389 / 27;
    
	public Lab(double L, double a, double b) {
        this.L = (L > 0) ? L : 0;
		this.a = a;
		this.b = b;
	}
	
    public void clear()
    {
        this.L = Double.MAX_VALUE;
    }

    public Lab complement() {
    	double atemp = this.a;
    	double btemp = this.b;
    	if(atemp > 127.0) {
    		atemp = 127.0;
    	}
    	if(btemp > 127.0) {
    		btemp = 127.0;
    	}
    	
    	return new Lab(this.L, -atemp, -btemp);
    }

    public XYZ LabToXYZ()
    {
        double x = reversefX();
        double y = reversefY();
        double z = reversefZ();
        double X = 95.047;
        double Y = 100.00;
        double Z = 108.883;

        return new XYZ(x * X, y * Y, z * Z);
    }
    
    public RGB LabToRGB()
    {
    	return LabToXYZ().XYZToRGB();
    }
    
    public HEX LabToHEX()
    {
    	return LabToRGB().RGBToHEX();
    }
    
    public double compareTo(Lab that)
    {
        double LBarPrime = (this.L + that.L) / 2;
        double C1 = Math.sqrt(this.a * this.a + this.b * this.b);
        double C2 = Math.sqrt(that.a * that.a + that.b * that.b);
        double CBar = (C1 + C2) / 2;
        double G = (1 - getC7(CBar)) / 2;
        double a1Prime = this.a * (1 + G);
        double a2Prime = that.a * (1 + G);
        double C1Prime = Math.sqrt(a1Prime * a1Prime + this.b * this.b);
        double C2Prime = Math.sqrt(a2Prime * a2Prime + that.b * that.b);
        double CBarPrime = (C1Prime + C2Prime) / 2;
        double h1Prime = gethPrime(a1Prime, this.b);
        double h2Prime = gethPrime(a2Prime, that.b);
        double HBarPrime = getHBarPrime(h1Prime, h2Prime);
        double T = 1 - (0.17 * Math.cos(Math.toRadians(HBarPrime - 30))) + (0.24 * Math.cos(Math.toRadians(2 * HBarPrime))) + (0.32 * Math.cos(Math.toRadians((3 * HBarPrime) + 6))) - (0.2 * Math.cos(Math.toRadians((4 * HBarPrime) - 63)));
        double DeltahPrime = getDeltahPrime(h1Prime, h2Prime);
        double DeltaLPrime = that.L - this.L;
        double DeltaCPrime = C2Prime - C1Prime;
        double DeltaHPrime = 2 * Math.sqrt(C1Prime * C2Prime) * Math.sin(Math.toRadians(DeltahPrime / 2));
        double SL = 1 + ((0.015 * (LBarPrime - 50) * (LBarPrime - 50)) / (Math.sqrt(20 + (LBarPrime - 50) * (LBarPrime - 50))));
        double SC = 1 + (0.045 * CBarPrime);
        double SH = 1 + (0.015 * CBarPrime * T);
        double DeltaOmega = 30 * Math.exp(0 - Math.pow(((HBarPrime - 275) / 25), 2.0));
        double RC = 2 * getC7(CBarPrime);
        double RT = -RC * Math.sin(Math.toRadians(2 * DeltaOmega));
        double KL = 1.0;
        double KC = 1.0;
        double KH = 1.0;

        double triL = triMath(DeltaLPrime, KL, SL);
        double triC = triMath(DeltaCPrime, KC, SC);
        double triH = triMath(DeltaHPrime, KH, SH);
        double DeltaE = Math.sqrt(triL * triL + triC * triC + triH * triH + (RT * triC * triH));
        return DeltaE;
    }

    private double getC7(double CBar)
    {
        return Math.sqrt((CBar * CBar * CBar * CBar * CBar * CBar * CBar) / ((CBar * CBar * CBar * CBar * CBar * CBar * CBar) + Math.pow(25, 7)));
    }

    private double gethPrime(double aPrime, double b)
    {
        return (Math.toDegrees(Math.atan2(b, aPrime)) < 0) ? (Math.toDegrees(Math.atan2(b, aPrime)) + 360) : Math.toDegrees(Math.atan2(b, aPrime));
    }

    private double getHBarPrime(double h1, double h2)
    {
        return (Math.abs(h1 - h2) > 180) ? ((h1 + h2 + 360) / 2) : ((h1 + h2) / 2);
    }

    private double getDeltahPrime(double h1, double h2)
    {
        if (Math.abs(h2 - h1) <= 180)
        {
            return (h2 - h1);
        }
        else if ((Math.abs(h2 - h1) > 180) && (h2 <= h1))
        {
            return (h2 - h1 + 360);
        }
        else
        {
            return (h2 - h1 - 360);
        }
    }

    private double triMath(double one, double two, double three)
    {
        return (one / (two * three));
    }

    private double reversefX()
    {
        double fx = (this.a / 500) + findfY();
        return ((fx * fx * fx) > e) ? (fx * fx * fx) : (((116 * fx) - 16) / k);
    }

    private double reversefY()
    {
        return (this.L > (k * e)) ? (((this.L + 16) / 116) * ((this.L + 16) / 116) * ((this.L + 16) / 116)) : (this.L / k);
    }

    private double reversefZ()
    {
        double fz = findfY() - (this.b / 200);
        return ((fz * fz * fz) > e) ? (fz * fz * fz) : (((116 * fz) - 16) / k);
    }

    private double findfY()
    {
        return (this.L + 16) / 116;
    }
    
    public String toString()
    {
    	return Math.floor(L) + " " + Math.floor(a) + " " + Math.floor(b);
    }
}
