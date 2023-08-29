//In queue , deletion at front, insertion at rear
#include<iostream>
using namespace std;
#define size 2
int queue[100];
int front=-1;
int rear=-1;
void enqueue(int x)
{
    if((rear+1)%size==front)
    {
        cout<<"Overflow";
    }
    else if(front=-1&&rear==-1)
    {
        rear++;
        front++;
        queue[rear]=x;
    }
    else
    {
        rear=(rear+1)%size;
        queue[rear]=x;
    }
}
void deque()
{
    if(front==-1&&rear==-1)
    {
        cout<<"Underflow";
    }
    else if(front==rear)
    {
        front=-1;
        rear=-1;
    }
    else
    {
        front=(front+1)%10;
    }
}
int display()
{
    if(front==-1&&rear==-1)
    {
        cout<<"Queue is empty!";
    }
    for(int i=front;i<rear+1;i++)
    {
        cout<<queue[i]<<ends;
    }
}
int main()
{
    enqueue(10);
    enqueue(11);
    display();
    enqueue(1);
    deque();
    display();
    enqueue(1);
    display();
}