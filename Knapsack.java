import java.util.*;

public class Knapsack {
    public static int max(int a, int b) {
        if(a>b)
        return a;
        else
        return b;
    }

    public static void PrintKnapsack(int k[][], int items, int capacity)
    {
        for (int i = 0;i < items;i++)
        {
            for (int j = 0;j <= capacity;j++)
            {
                System.out.print(k[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void bottomup(int k[][], int Weight[], int Profit[], int items, int capacity, boolean selectedItems[][]) {
        for (int i = 0;i < items;i++)
        {
            for (int j = 0;j<=capacity;j++) 
            {
                if (i==0||j==0)
                {
                    k[i][j] = 0;
                } 
                else if (Weight[i]<=j) 
                {
                    k[i][j] = max(Profit[i]+k[i - 1][j-Weight[i]],k[i - 1][j]);
                    if (k[i][j]!=k[i-1][j])
                    //(Profit[i]+k[i - 1][j-Weight[i]]>k[i - 1][j]) alternate.
                    {
                        selectedItems[i][j]=true;
                    }
                } 
                else
                {
                    k[i][j]=k[i - 1][j];
                }
            }
        }
    }

    public static void printSelectedItems(boolean selectedItems[][], int[] Weight, int capacity,int Profit[]) {
        int i = selectedItems.length - 1;
        int j = capacity;

        System.out.println("Selected items in the knapsack:");
        while (i>0 && j>0)
        {
            if (selectedItems[i][j])
            {
                System.out.println("Item " + i + ": Weight = " + Weight[i] + ", Value = " + (Profit[i]));
                j -= Weight[i];
            }
            i--;
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the capacity of knapsack: ");
        int C = sc.nextInt();
        System.out.println("Enter the number of items");
        int n = sc.nextInt();
        int Weight[] = new int[n + 1]; 
        int Profit[] = new int[n + 1];
        int k[][] = new int[n + 1][C + 1];
        boolean selectedItems[][] = new boolean[n + 1][C + 1];

        for (int i = 1; i <= n; i++) {
            System.out.println("Enter the weight of item " + i + " :");
            Weight[i] = sc.nextInt();
            System.out.println("Enter profit associated with the of item " + i + " :");
            Profit[i] = sc.nextInt();
        }
        bottomup(k, Weight, Profit, n + 1, C, selectedItems);
        // System.out.println("The knapsack table:");
        // PrintKnapsack(k, n + 1, C);
        System.out.print("The maximum profit achieved is: "+k[n][C]);
        printSelectedItems(selectedItems, Weight, C,Profit);
    }
}
