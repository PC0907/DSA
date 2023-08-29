#include<iostream>
using namespace std;
void heapify(int arr[],int n,int i)
{
    int largest= i;
    int l=2*i+1;
    int r=2*i+2;
    while(l<n && arr[l]>arr[largest])
    {
        largest=l;
    }
    while(r<n && arr[r]>arr[largest])
    {
        largest=r;
    }
    if(largest!=i)
    {
        swap(arr[i],arr[largest]);
        heapify(arr,n,largest);
    }
}
void heapsort(int arr[],int n)
{
    for(int i=n/2-1;i>=0;i--)
    {
        heapify(arr,n,i);
    }
    for(int i=n-1;i>0;i--)
    {
        swap(arr[0],arr[i]);
        heapify(arr,i,0);
    }
}
int main()
{
    int arr[20]={1,2,9,3,21,3};
    int size=6;
    heapsort(arr,size);
    for(int i=0;i<6;i++)
    {
        cout<<arr[i]<<ends;
    }
}
