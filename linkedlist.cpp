#include<iostream>
using namespace std;
struct node
{
    int val;
    node* next;
};
struct node *insertatbeg(node *head,int x)
{
    struct node *ptr=new node;
    ptr->val=x;
    ptr->next=head;
    head=ptr;
    return ptr;
}
struct node *traverse(node *head)
{
    while(head!=NULL)
    {
        cout<<head->val<<"->"<<ends;
        head=head->next;
    }
}
struct node *create(int x)
{
    struct node *head=new node;
    head->val=x;
    head->next=NULL;
    return head;
}
struct node *insertatvalue(node *head,int x,int y)
{
    struct node *ptr=new node;
    struct node *p=new node;
    p=head;
    while(p!=NULL && p->val!=y)
    {
        p=p->next;
    }
    if(p->val==y)
    {
        ptr->val=x;
        ptr->next=p->next;
        p->next=ptr;
        return head;
    }
    else{
        return 0;
    }
}
struct node * insertatend(node *head,int x)
{
    struct node *ptr=new node;
    struct node *p=new node;
    p=head;
    while(p->next!=NULL)
    {
        p=p->next;
    }
    p->next=ptr;
    ptr->next=NULL;
    ptr->val=x;
    return head;
}
// struct node *add(node *head,int x)
// {
//     struct node *ptr=new node;
//     head->next=ptr;
//     ptr->val=x;
//     ptr->next=NULL;
//     return head;
// }
struct node *del_beg(node *head)
{
    node *ptr=new node;
    ptr=head;
    head=head->next;
    delete ptr;
    return head;
}
struct node *del_any(node *head,int key)
{
    node *ptr=new node;
    node *p=new node;
    p=head;
    ptr=head->next;
    if(p->val==key)
    {
        head=del_beg(head);
        return head;
    }
    while(ptr!=NULL && ptr->val!=key)
    {
        p=p->next;
        ptr=ptr->next;
    }
    if(ptr->val==key)
    {
    p->next=ptr->next;
    delete ptr;
    return head;
    }
}
void printList(node *head)
{
    node *temp = head;
    if (head != NULL)
    {
        do
        {
            cout << temp->val << " ";
            temp = temp->next;
        }
        while (temp != head);
    }
}
int main()
{
    struct node *head=new node;
    head=create(7);
    // head=add(head,10);
    head=insertatbeg(head,11);
    head=insertatend(head,76);
    head=insertatvalue(head,101,11);
    traverse(head);
    cout<<endl;
    traverse(head);
}