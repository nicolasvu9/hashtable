

public class Universal_Hashing extends Open_Addressing{
	int a;
	int b;
	int p;

	protected Universal_Hashing(int w, int seed) {
		super(w, seed, -1);
		int temp = this.m+1; // m is even, so temp is odd here
		while(!isPrime(temp)) {
			temp += 2;
		}
		this.p = temp;
		a = generateRandom(0, p, seed);
		b = generateRandom(-1, p, seed);
	}
	
	/**
	 * Checks if the input int is prime
	 */
	public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i*i <= n; i++) {
        	if (n % i == 0) return false;
        }
        return true;
    }
	
	/**
     * Implements universal hashing
     */
	@Override
    public int probe(int key, int i) {
		int ind= ((((a*key+b)%p)%m)+i) % (power2(r));
		return ind;
    }

    /**
     * Inserts key k into hash table. Returns the number of collisions encountered,
     * and resizes the hash table if needed
     */
	@Override
    public int insertKeyResize(int key) {
		float load_factor = (float)(this.size+1)/m;

		if(load_factor>MAX_LOAD_FACTOR){
			int old_m = this.m;
			this.r = this.r +1;
			this.w= this.r*2;
			this.m= power2(this.r);
			this.A = generateRandom((int) power2(this.w - 1), (int) power2(this.w), seed);
			int temp = this.m+1;
			while(!isPrime(temp)) {
				temp += 2;
			}
			this.p = temp;
			a = generateRandom(0, p, seed);
			b = generateRandom(-1, p, seed);

			// Make copy of old table
			int[] old_table= new int[old_m];
			for (int j=0;j<old_m;j++){
				old_table[j]= this.Table[j];
			}
			// Reset the table of this instance
			this.Table = new int[this.m];
			for (int i = 0; i < this.m; i++) {
				this.Table[i] = -1;
			}
			this.size=0;
			// Insert all the old keys to the new resized array
			for(int k=0; k< old_m; k++){
				if(old_table[k]>=0) {
					this.insertKey(old_table[k]);
				}
			}
		}
		int collisions=insertKey(key);

		return collisions;
    }
}
