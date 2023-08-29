#include<iostream>
#include<stdlib.h>
#include<time.h>
using namespace std;
void swap(int *a,int *b)
{
    int temp;
    temp=*a;
    *a=*b;
    *b=temp;
}
void printarray(int arr[],int size)
{
    for(int i=0;i<size;i++)
    {
        cout<<arr[i]<<" ";
    }
    cout<<endl;
}
int partition(int arr[],int start,int end)
{
    int x = arr[end];
    int i = start - 1;
    for (int j = start;j <=end - 1;j++)
    {
        if (arr[j] <= x) 
        {
            i++;
            swap(&arr[i],&arr[j]);
        }
    }
    swap(&arr[i + 1],&arr[end]);
    return i + 1;
}
int hoare_partition(int arr[],int start,int end)
{
    int x=arr[start];
    int i=start-1;
    int j=end+1;
    while(true)
    {
        do
        {
            j--;
        } while (arr[j]>x);
        do
        {
            i++;        
        } while(arr[i]<x);
        if(i<j)
        {
            swap(&arr[i],&arr[j]);
        }
        else
        {
            return j;
        }
    }
}
int randomized_partition(int arr[],int start,int end)
{
    
    srand(time(NULL));
    int size = end - start + 1;
    int random = rand() % size;
    int i = start + random;
    
    cout<<"The random index taken is: "<<i<<" and the element in the array to be taken as the pivot is: "<<arr[i]<<endl;
    
    
    cout<<endl;
    swap(&arr[end],&arr[i]);
    return hoare_partition(arr, start, end);
}
void quicksort(int arr[],int start,int end, int size)
{
    if (start<end)
    {
        // int size=end-start+1
        int q=randomized_partition(arr,start,end);
        cout << "Intermediate Array: ";
        printarray(arr, size);
        quicksort(arr,start,q-1, size);
        quicksort(arr,q+1,end, size);
    }
}
int main()
{
    int arr[100],size;
    cout<<"Enter the size of the array: ";
    cin>>size;
    for(int i=0;i<size;i++)
    {
        cout<<"Enter the array element: ";
        cin>>arr[i];
    }
    cout<<endl;
    cout<<"Unsorted Array : "<<endl;
    printarray(arr,size);
    quicksort(arr,0,size-1, size);
    cout<<"Final Sorted Array: "<<endl;
    printarray(arr,size);
    return 0;
}
