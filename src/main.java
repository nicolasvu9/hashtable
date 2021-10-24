import java.util.Arrays;


public class main {


	public static void main(String[] args) {
		Open_Addressing testLinearProbe = new Open_Addressing(5,0,-1);
		int[] keys= new int[]{5,7, 29,37,69};
		testLinearProbe.insertKeyArrayResize(keys);
		System.out.println(testLinearProbe.probe(37,0));

		arr_index(keys, testLinearProbe.w, testLinearProbe.A);
		System.out.println(Arrays.toString(testLinearProbe.Table));
		testLinearProbe.removeKey(7);
		System.out.println(Arrays.toString(testLinearProbe.Table));

		int[] x=testLinearProbe.searchKeyOptimized(69);
		System.out.println(testLinearProbe.size);
		System.out.println(Arrays.toString(testLinearProbe.Table));

		System.out.println(Arrays.toString(x));



	}
	private static void arr_index(int[] keys, int w, int A){
		Open_Addressing testLinearProbe = new Open_Addressing(w,0,A);

		int[] index_keys = new int[keys.length];
		for(int i=0; i<keys.length; i++){
			int index = testLinearProbe.probe(keys[i],0);
			System.out.println("index 0 of " +keys[i]+"......" + index);

		}

	}
}