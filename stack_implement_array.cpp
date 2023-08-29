#include<iostream>
#define size 10
using namespace std;
int top=-1;
int stack[100];
int push(int x)
{
    if(top==size-1)
    {
        cout<<"overflow";
    }
    else
    {
        top++;
        stack[top]==x;
    }
    return top;
}
int pop()
{
    if(top==-1)
    {
        cout<<"UNDERFLOW";
    }
    else
    {
        top--;
    }
    return top;
}
void display()
{
    while(top!=-1)
    {
        cout<<stack[top]<<endl;
        top--;
    }
}
int main()
{
    int ch;
    while(ch!=4)
    {
    cout<<"Enter choice: ";
    cin>>ch;
    switch(ch)
    {
        case 1:
        int x;
        cout<<"eNTER VALUE: ";
        cin>>x;
        push(x);
        break;
        case 2:
        cout<<"POP Operation";
        pop();
        break;
        case 3:
        display();
        break;
        case 4:
        cout<<"EXIT";
        break;
    }
    }

}