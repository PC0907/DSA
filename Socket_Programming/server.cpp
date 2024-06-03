#include <iostream>
#include <cstring>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>
#include <arpa/inet.h>

int main() {
    char hostname[1024];
    hostname[1023] = '\0';
    gethostname(hostname, 1023);

    struct hostent *host_info;
    host_info = gethostbyname(hostname);

    if (host_info == nullptr) {
        std::cerr << "Error getting host information" << std::endl;
        return 1;
    }

    struct in_addr **addr_list = reinterpret_cast<struct in_addr **>(host_info->h_addr_list);

    for (int i = 0; addr_list[i] != nullptr; i++) {
        std::cout << "IP Address: " << inet_ntoa(*addr_list[i]) << std::endl;
    }

    return 0;
}
