import java.util.Random;

public class Open_Addressing {
	public static final double MAX_LOAD_FACTOR = 0.75;
	
	public int m; // number of slots
	public int A; // the default random number
	int w;
	int r;
	int seed;
	public int[] Table;
	int size; // number of elements stored in the hash table

	protected Open_Addressing(int w, int seed, int A) {
		this.seed = seed;
		this.w = w;
		this.r = (int) (w - 1) / 2 + 1;
		this.m = power2(r);
		if (A == -1) {
			this.A = generateRandom((int) power2(w - 1), (int) power2(w), seed);
		} else {
			this.A = A;
		}
		this.Table = new int[m];
		for (int i = 0; i < m; i++) {
			Table[i] = -1;
		}
		this.size = 0;
	}

	/**
	 * Calculate 2^w
	 */
	public static int power2(int w) {
		return (int) Math.pow(2, w);
	}

	public static int generateRandom(int min, int max, int seed) {
		Random generator = new Random();
		if (seed >= 0) {
			generator.setSeed(seed);
		}
		int i = generator.nextInt(max - min - 1);
		return i + min + 1;
	}

	/**
	 * Implements the hash function g(k)
	 */
	public int probe(int key, int i) {
		int ind=((((this.A*key)%power2(this.w))>>(this.w-this.r)) + i) % power2(r);
		return ind;

	}

	/**
	 * Inserts key k into hash table. Returns the number of collisions encountered
	 */
	public int insertKey(int key) {
		int i=0;
		// While loop until it finds a slot
		while(true){
			// Find its slot
			int ind = this.probe(key, i);
			// If the slot is empty, fill it using the key and increase the size
			if(this.Table[ind] < 0){
				this.Table[ind] = key;
				this.size++;
				return i;
			}
			// Go to the next slot if they're full
			i++;
		}
	}


	/**
	 * Sequentially inserts a list of keys into the HashTable. Outputs total number of collisions
	 */
	public int insertKeyArray(int[] keyArray) {
		int collision = 0;
		for (int key : keyArray) {
			collision += insertKey(key);
		}
		return collision;
	}


	public int[] searchKey(int k) {
		//TODO: implement this function and change the return statement
		int i=0;
		int[] output;
		// While the slots gone through is less than the number of total slots
		while(i<m){
			// Go to the possible slot
			int ind = this.probe(k, i);
			// Check if the key in the slot is the searched key
			if(this.Table[ind] == k){
				output= new int[]{ind, i};
				return output;

			} else if(this.Table[ind] == -1){ // if it's an empty slot, that means the key is not there
				output= new int[]{-1, i+1};
				return output;
			}
			i++;
		}
		output= new int[]{-1, i};
		return output;
	}
	
	/**
	 * Removes key k from hash table. Returns the number of collisions encountered
	 */
	public int removeKey(int k){
		//TODO: implement this function and change the return statement.

		// Use search key to find the key
		int[] arr = this.searchKey(k);
		// Search key didn't find the key
		if(arr[0]==-1){
			int slots_visited=arr[1];
			return slots_visited;
		}else{

			int ind = arr[0];
			int col = arr[1];
			//Remove the key
			this.Table[ind]=-2;
			this.size--;
			return col;
		}

	}

	/**
	 * Inserts key k into hash table. Returns the number of collisions encountered,
	 * and resizes the hash table if needed
	 */
	public int insertKeyResize(int key) {
		//TODO: implement this function and change the return statement

		float load_factor = (float)(this.size+1)/this.m;

		if(load_factor>MAX_LOAD_FACTOR){
			int old_m = m;
			this.r = r +1;
			this.w= r*2;
			this.m= power2(this.r);
			this.A = generateRandom((int) power2(this.w - 1), (int) power2(this.w), seed);

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
			// Insert all the old keys to the new resized array
			this.size=0;
			for(int k=0; k< old_m; k++){
				if(old_table[k]>=0) {
					this.insertKey(old_table[k]);
				}
			}
		}
		int collisions=insertKey(key);

		return collisions;
	}

	/**
	 * Sequentially inserts a list of keys into the HashTable, and resize the hash table
	 * if needed. Outputs total number of collisions
	 */
	public int insertKeyArrayResize(int[] keyArray) {
		int collision = 0;
		for (int key : keyArray) {
			collision += insertKeyResize(key);
		}
		return collision;
	}

	public int[] searchKeyOptimized(int k) {
		//TODO: implement this function and change the return statement
		int[] search = searchKey(k);
		if (search[0]==-1){
			return search;
		}
		int[] output;

		int i=0;
		int ind0= this.probe(k,i);
		// Check if it's not already at the best possible spot
		if(search[0]!=ind0){
			// While the new index search is not equal to its current position
			while(this.probe(k,i)!=search[0]){
				int  ind = this.probe(k, i);
				//If it's an empty spot or removed spot
				if(this.Table[ind]<0){
					//Place the new key at its new position
					this.Table[ind]=k;
					//Remove the key at its old position
					this.Table[search[0]]=-2;
					output = new int[]{ind,i};
					return output;
				}
				i++;
			}
			return search;
		}

		output= new int[]{ind0,0};
		return output;
	}

	/**
	 * @return an int array of n keys that would collide with key k
	 */
	public int[] collidingKeys(int k, int n, int w) {
		//TODO: implement this function and change the return statement
		int[] keys = new int[n];
		for(int i=0; i<n; i++){
			keys[i]= k+i*power2(w);
		}
		return keys;
	}
}
