import org.junit.Assert;
import java.util.Arrays;


import static org.junit.Assert.*;

public class Open_AddressingTest {

    @org.junit.Test
    public void probe() {
        Open_Addressing testLinearProbe = new Open_Addressing(10,0,-1);
        Assert.assertEquals(testLinearProbe.probe(1,0),30);

    }

    @org.junit.Test
    public void insertKey() {
        System.out.println("insertKey() test\n");
        Open_Addressing testLinearProbe = new Open_Addressing(5,0,-1);

        int[] keys= new int[]{5,7,8,10, 29};
        testLinearProbe.insertKeyArray(keys);
        int[] actual= new int[]{-1,-1,8,10,29,5,7,-1};
        if(Arrays.equals(actual, testLinearProbe.Table)){
            System.out.println("test passed!\n");
        }
        else{
            System.out.println("Inserting keys: " + Arrays.toString(keys));
            System.out.println("Test failed\n actual: "+ Arrays.toString(actual));
            System.out.println("\n output: "+ Arrays.toString(testLinearProbe.Table));
        }

    }
    @org.junit.Test
    public void searchKey1() {
        System.out.println("searchKey1() test\n");

        Open_Addressing testLinearProbe = new Open_Addressing(5,0,-1);
        int[] keys= new int[]{5,7,8,10, 29};
        testLinearProbe.insertKeyArray(keys);


        System.out.println(Arrays.toString(testLinearProbe.Table));
        int[] actual= new int[]{4,1};
        int[] x=testLinearProbe.searchKey(29);


        if(Arrays.equals(actual, x)){
            System.out.println("test passed!\n");
        }
        else{
            System.out.println("Inserting keys: " + Arrays.toString(keys));
            System.out.println("Test failed\n actual: "+ Arrays.toString(actual));
            System.out.println("\n output: "+ Arrays.toString(x));
        }

    }
    @org.junit.Test
    public void searchKey2() {
        System.out.println("searchKey2() test\n");

        Open_Addressing testLinearProbe = new Open_Addressing(5,0,-1);
        int[] keys= new int[]{5,7,8, 29};
        testLinearProbe.insertKeyArray(keys);


        int[] actual= new int[]{-1,2};
        int[] x=testLinearProbe.searchKey(44);

        int y= testLinearProbe.probe(44,0);
        System.out.println("Index 0 of 44: " + y);
        System.out.println(Arrays.toString(testLinearProbe.Table));
        System.out.println(Arrays.toString(x));

        if(Arrays.equals(actual, x)){
            System.out.println("\ntest passed!\n");
        }
        else{
            System.out.println("Test failed\n actual: "+ Arrays.toString(actual));
            System.out.println("\n output: "+ Arrays.toString(x));
        }

    }
    @org.junit.Test
    public void removeKey() {
        System.out.println("removeKey() test\n");

        Open_Addressing testLinearProbe = new Open_Addressing(5,0,-1);
        int[] keys= new int[]{5,7,8, 29,44,3};
        testLinearProbe.insertKeyArray(keys);

        System.out.println("Before " + Arrays.toString(testLinearProbe.Table));
        int y=testLinearProbe.removeKey(29);
        int[] x=testLinearProbe.searchKey(44);
        System.out.println("after: " + Arrays.toString(testLinearProbe.Table));
        System.out.println("searching for 44 " + Arrays.toString(x));
        System.out.println(y);


    }
    @org.junit.Test
    public void searchKeyOptimized() {
        System.out.println("searchKeyOptimized() test\n");

        Open_Addressing testLinearProbe = new Open_Addressing(5,0,-1);
        int[] keys= new int[]{5,7,8,10, 29};
        testLinearProbe.insertKeyArrayResize(keys);

        System.out.println(Arrays.toString(testLinearProbe.Table));
        testLinearProbe.removeKey(5);
        System.out.println(Arrays.toString(testLinearProbe.Table));

        int[] x=testLinearProbe.searchKeyOptimized(7);
        int[] actual= new int[] {5,0};
        if(Arrays.equals(actual, x)){
            System.out.println("\ntest passed!\n");
        }
        else{
            System.out.println("Test failed\n actual: "+ Arrays.toString(actual));
            System.out.println("\n output: "+ Arrays.toString(x));
        }

    }
}