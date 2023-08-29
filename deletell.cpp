#include<iostream>
using namespace std;
struct node
{
    int val;
    node* prev;
    node *next;
};
struct node *insert_at_beg(node * head)
{
    //same as singly linked list
}
void trav(node *head)
{
    while(head!=NULL)
    {
        cout<<head->val<<ends;
        head=head->next;
    }
}
void rev_trav(node *head)
{

}
struct node *insert_at_val(node *head,int x,int y)
{
    node *ptr=new node;
    node *p=new node;
    p=head;
    while(p!=NULL && p->val!=y)
    {
        p=p->next;
    }
    if(p->val==y)
    {
        ptr->next=p->next;
        p->next=ptr;
        ptr->prev=p;
        ptr->val=x;
        return head;
    }
}
// struct node *delete_val(node *head,int y)
// {
//     node *ptr=new node;
//     node *p=new node;
//     p=head;
//     while(p!=NULL && p->val==y)
//     {
//         p=p->next;
//     }
//     if(p->val==y)
//     {
//         ptr=p;
//         p->prev=p->next;
//         p->next=p->next->prev;
//         delete ptr;
//         return head;
//     }
// }
int main()
{
    node *head=new node;
    head->val=23;
    head->next=NULL;
    head->prev=NULL;
    insert_at_val(head,12,23);
    insert_at_val(head,10,12);
    insert_at_val(head,100,23);
    insert_at_val(head,121,23);
    // head=delete_val(head,12);
    trav(head);
}
