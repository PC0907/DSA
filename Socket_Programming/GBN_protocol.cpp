#include <iostream>
#include <stdlib.h>
#include <time.h>
#include <stdio.h>
#define window_size 4

using namespace std;

int globalTime = 0;
int receiverAck;
float percentageEff;
int numberPacketsLost;
int sender[window_size][4]; //sender_window = 10, other 4 fields are for 1)pkt_number, 2)gen_time, 3)ack_time, 4)timeout_time
int snd_pkt = 0; //packets sent from the sender
int rcv_pkt = 0; // packets send from the receiver
int transmitTime; //time taken for transmission
int generationTime; //time taken for generation
int timeOut=0;//timeout time ( time taken to for sender to retransmit a packet)

void generate(int genTime, int packetNo) {
  if (packetNo < window_size+1)
    cout << "Packet number " << packetNo << " is generated at time = " << genTime << endl;

  sender[snd_pkt % window_size][0] = packetNo;
  sender[snd_pkt % window_size][1] = genTime;
  sender[snd_pkt % window_size][2] = 0;
  sender[snd_pkt % window_size][3] = 0;

  int randTemp = rand() % 100;
  if (randTemp < numberPacketsLost) {
    if (packetNo < window_size + 1)
      cout << "This packet will be lost in transit" << endl
           << endl;
    sender[snd_pkt % window_size][3] = genTime + timeOut;
  } else {
    if (packetNo < window_size + 1)
      cout << "This packet will reach safely" << endl
           << endl;
    sender[snd_pkt % window_size][2] = genTime + 2 * transmitTime;
  }
  snd_pkt++;
}

void goBackN() {
  int base = 0; // Base of the sliding window
  int nextseqnum = 0; // Next sequence number to be sent

  while(base < 10) { // Assuming 10 packets in total

    // Send packets within the window
    while (nextseqnum < base + window_size && nextseqnum < 10) {
      if (sender[nextseqnum % window_size][2] == 0) { // If packet not sent yet
        generate(globalTime, nextseqnum + 1);
      }
      nextseqnum++;
    }

    int ack_received = -1; // Acknowledgment received for which packet

    // Check acknowledgments within window
    for (int i = base; i < nextseqnum; i++) {
      if (sender[i % window_size][2] <= globalTime + generationTime && sender[i % window_size][2] != 0) // Acknowledgment received
      {
        cout << "Packet Number " << sender[i % window_size][0] << " has been acknowledged at time = " << sender[i % window_size][2] << endl;
        ack_received = i;
      } else if (sender[i % window_size][3] <= globalTime + generationTime && sender[i % window_size][3] != 0) // Timeout occurred
      {
        cout << "!!!Time out in packet number " << sender[i % window_size][0] << " has occurred at time = " << sender[i % window_size][3] << "!!!" << endl;
        sender[i % window_size][2] = 0; // Reset acknowledgment time
      }
    }

    if (ack_received != -1) { // Slide the window
      base = ack_received + 1 % window_size; // Update base considering circular window
    }

    globalTime += generationTime; // Move to the next time slot
  }
}

int main()
{
    cout<<"Enter the times for different network parameters:"<<endl;
    cout<<"Generation Time (ms):";cin>>generationTime;
    cout<<"Transmission Time (ms):";cin>>transmitTime;
    cout<<"TimeOut Time (ms):";cin>>timeOut;cout<<endl;
    cout<<"Enter The percentage efficiency:";cin>>percentageEff;
    numberPacketsLost = 100 -(int)percentageEff;
    cout<<endl<<"Running Go back N"<<endl;
    for(int i = 0; i<10;i++)cout<<"_"; //drawing the pattern
    cout<<endl<<endl;
    goBackN();
    system("pause");
    return 0;
}
