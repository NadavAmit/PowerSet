import java.util.*;

//Written By: Amit Nadav

public class main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] result = new int[0];
        boolean wrongInputFlag=true;

        //reading input from user
        //In case of blank input retrives the empty set
        // in case of not supported inputs loops until correct format inserted
        //For example:  1 2 3 4
        while(wrongInputFlag) {
            try {
                System.out.println("Enter a set of your choice in the following format: 1 2 3");
                String input = scanner.nextLine();

                //Checks for Empty Set
                if(input.isBlank()){
                     System.out.println(new ArrayList());
                     return;
                }

                String[] splittedString = input.split(" ");
                result = new int[splittedString.length];

                for(int i=0;i<splittedString.length;i++){
                    result[i]=Integer.parseInt(splittedString[i]);
                }
            } catch (Exception exception) {
                System.out.println("Wrong input,Please insert a sequence of integers");
                continue;
            }
            wrongInputFlag=false;
        }

        //creating indicators arrayList
        int indicatorsArr[]= new int[result.length];
        ArrayList list = new ArrayList();
        getAllSubsetsIndicators(indicatorsArr,0,list);

        //Transforming indicators array to the actual power-set
        List<List<Integer>> powerSet = new ArrayList<>();
        for (Object e:list) {
            powerSet.add(restoreSubset((int[]) e,result));
        }

        //sorts List of Subsets by size
        List<List<Integer>> SortedPowerSet = sort(powerSet);

        //Prints power-Set sorted
        System.out.println(SortedPowerSet);
    }

    //Recursively creates an array of the power-set represented by Indicators
    public static void getAllSubsetsIndicators(int[] arr, int index, ArrayList list){
        if(index == arr.length){
            list.add(arr);
        }
        else{
            // not taken
            getAllSubsetsIndicators(arr,index+1, list);

            //taken
            int indexesArr[]= new int[arr.length];
            indexesArr = arr.clone();
            indexesArr[index]=1;
            getAllSubsetsIndicators(indexesArr,index+1,list);
        }
    }
    //Transforming indicators arrays into actutal subsets
    public static List<Integer> restoreSubset(int[] inidicatorsArray,int[] originalArray){
        List<Integer> set = new ArrayList<Integer>();

        for(int i= 0;i<originalArray.length;i++){
            if(inidicatorsArray[i]==1){
                set.add(originalArray[i]);
            }
        }
        return set;
    }

    public static <T> List<List<T>> sort(List<List<T>> list) {
        list.sort((x, y) -> x.size() - y.size());
        return list;
    }

}
