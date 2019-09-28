from socket import *
import sys
import signal
import os
import threading

class ProxyServer:

    def __init__(self,host):
        self.host = host
        signal.signal(signal.SIGINT, self.shutdown)
        self.start()

    def shutdown(self,sig,frame):
        try:
            print("Close Server")
            self.socket.shutdown(socket.SHUT_RDWR)
        except Exception:
            pass
        sys.exit(0)

    def start(self):
        self.tcpSerSock = socket(AF_INET, SOCK_STREAM)
        print(self.host)
        try:
            self.tcpSerSock.bind((self.host, 8888))
        except Exception:
            print("Bind Exception")
            sys.exit(1)
        print('ready to serves')

        self.tcpSerSock.listen(5)
        while True:
            (client, address) = self.tcpSerSock.accept()
            client.settimeout(60)
            print("Recieved from {addr}".format(addr=address))
            threading.Thread(target=self.my_listen, args=(client, address)).start()


    def my_listen(self,client,address):
        # Strat receiving data from the client

        while True:
            print("Recieved from {addr}".format(addr=address))
            PACKET_SIZE = 999999
            message = client.recv(PACKET_SIZE).decode()
            # get url
            print(message)
            # Extract the filename from the given message
            if not message:
                return
            filename = message.split()[1]
            print(filename)
            fileExist = "false"
            try:
                # Check wether the file exist in the cache
                f = open("./cache" + filename + ".html", "r")
                outputdata = f.read()
                fileExist = "true"
                print(outputdata)
                header = "HTTP/1.0 200 OK\r\nContent-Type:text/html\r\n\r\n"
                data = header+outputdata
                client.send(data.encode())
                print('Read from cache')
            # Error handling for file not found in cache
            except IOError:
                # try:
                #     # png file read (but I can't save)
                #     f = open("./cache"+filename+".png","r")
                #     outputdata = f.read()
                #     fileExist = "true"
                #     print(outputdata)
                #     header = "HTTP/1.0 200 OK\r\nContent-Type:image/png\r\n\r\n"
                #     data = header + outputdata
                #     client.send(data.encode())
                #     print('Read from cache')
                # except:
                #     pass
                print('Fail cache hit')
            if fileExist == "false":
                # Create a socket on the proxyserver
                c = socket(AF_INET, SOCK_STREAM)
                ist = message.split('www.')[1].split("\r\n")[0]
                print(ist)
                message = message.replace("%s:8888"%self.host, "%s:80"%self.host)
                message = message.replace("Referer: http://%s:80/"%self.host, "Referer: https://", 1)
                print(message)
                #hostn = filename.replace("www.", "", 1)

                try:
                    c.connect((ist, 80))
                    c.send(message.encode())
                    data = c.recv(99999999)
                    client.send(data)

                    #save modul fail,  I need help.. I need import requests..ㅜㅜ

                    save = data
                    saveheader = save.split(b'\n\r\n')[0]
                    print(saveheader)
                    if saveheader in b'png':
                        tmpFile = open("./cache/%s.png"%filename, "wb")
                        tmpFile.write(save.split(b'\n\r\n')[1])
                        tmpFile.close()
                        print("save %s.png"%filename)

                    # Connect to the socket to port 80
                    # sendheader = ''
                    # if fn == -1:
                    #     sendheader = "GET / HTTP/1.1\n\n"
                    # else:
                    #     sendheader = "GET " + ist[fn:] + " HTTP/1.1\n\n"
                    # c.send(sendheader.encode())
                    # response = c.recv(999999999)
                    # print(response)
                    #datatype = data.split('Content-Type: ')[1].split('\n')[0]
                    #if datatype =='text/html':
                    #datatype = response.decode()
                    # if sendheader in 'png':
                    # client.send(response)
                    # else :
                    #     index = response.find("<!doctype html>")
                    #     if index == -1:
                    #         pass
                    #     else:
                    #         print('save')
                    #         tmpFile = open("./cache" + filename + ".html", "wb")
                    #         tmpFile.write(response[index:])
                    #         # tmpFile = open("./cache" + filename + ".txt", "wb")
                    #         # tmpFile.write(data)
                    #         while True:
                    #             client.send(response.encode())
                    #         if data in '404':
                    #             os.remove("./cache" + filename)
                except IOError:
                    print("Illegal request")
                c.close()
            else:
                pass
            client.close()
            break
if len(sys.argv) <= 1:
    print('Usage : "python ProxyServer.py server_ip"\n[server_ip : It is the IP Address Of Proxy Server')
    #sys.exit(2)
#ProxyServer(sys.argv[1])
ProxyServer('192.168.0.38')




