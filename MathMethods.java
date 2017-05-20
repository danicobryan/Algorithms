import java.math.BigInteger;
import java.util.Arrays;

public class MathMethods extends java.lang.Object { 
	public static void main(String [] args){

		String method = args[0];

		switch(method){
			case("factorial"):
				System.out.println(factorial(Integer.parseInt(args[1])));
				break;
			case("fibonacci"):
				System.out.println(fibonacci(Integer.parseInt(args[1])));
				break;
			case("gcd"):
				System.out.println(gcd(Long.parseLong(args[1]), Long.parseLong(args[2])));
				break;
			case("lcm"):
				System.out.println(lcm(Long.parseLong(args[1]), Long.parseLong(args[2])));
				break;
			case("poly"):
				double[] coeffs = new double[args.length - 1];
				for(int i = 0; i < coeffs.length; i++){
					coeffs[i] = Double.parseDouble(args[i + 1]);
				}
				System.out.println(poly(Double.parseDouble(args[1]), coeffs));
				break;
			case("sqrt"):
				System.out.println(sqrt(Double.parseDouble(args[1]), Double.parseDouble(args[2])));
				break;
			case("root"):
				System.out.println(root(Integer.parseInt(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3])));
				break;
			case("power"):
				System.out.println(power(Double.parseDouble(args[1]), Integer.parseInt(args[2])));
				break;
		}

	}

	public static BigInteger factorial(int n) {
		BigInteger result = new BigInteger(Integer.toString(n));

		if(n <= 1){
			BigInteger one = new BigInteger("1");
			return one;
		} else {
			while(n > 1){
				String next = Integer.toString(n - 1);
				BigInteger nextBigInt = new BigInteger(next);
				result = result.multiply(nextBigInt);
				n--;
			}
			return result;
		}
	}

	public static BigInteger fibonacci(int n){
		BigInteger first = new BigInteger("0");
		BigInteger second = new BigInteger("1");
		BigInteger next = new BigInteger("0");

		if(n == 0){
			return first;
		} else if(n == 1){
			return second;
		} else {
			for(int i = 0; i < n - 1; i++){
				next = first.add(second);
				first = second;
				second = next;
			}
			return second;
		}

	}

	public static long gcd(long m, long n){
		if(n == 0){
			return m;
		}

		return gcd(n, m % n);
	}

	public static long lcm(long m, long n) {
		if(m == 0 && n == 0){
			return 0;
		} else if(m == 0 && n != 0){
			return n;
		} else if(m != 0 && n== 0){
			return m;
		}
		return (m * n) / gcd(m,n);
	}

	public static double poly(double x, double[] coeff){
		double total = 0;
		for(int i = coeff.length - 1; i >= 0; i--){
			total = (total * x) + coeff[i];
		}
		return total;
	}

	public static double sqrt(double x, double epsilon){
		if(x < 0){
			throw new IllegalArgumentException("Input cannot be negative.");
		}
		double low = 0;
		double high = x;

		if(x < 1){
			high = 1;
		}
		if(x == 1){
			return 1;
		}

		while(high - low > epsilon){
			double mid = (low + high) / 2;
			if(mid * mid <= x){
				low = mid;
			} else {
				high = mid;
			}
		}
		return low;
	}

	public static double root(int n, double x, double epsilon){
		double low = 0;
		double high = x;

		if(x == 1){
			return 1;
		}
		if(n == 1){
			return x;
		}

		while(high - low > epsilon){
			double mid = (low + high) / 2;
			if(power(mid, n) <= x){
				low = mid;
			} else {
				high = mid;
			}
		}
		return low;
	}

	public static double power(double x, int n){ 
		if(n == 0){
			return 1;
		}
		if(n == 1){
			return x;
		}
		if(n % 2 == 0){
			double y = power(x, n/2);
			return y * y;
		} else {
			double w = power(x, n/2);
			return w * w * x;
		}
	}	
}