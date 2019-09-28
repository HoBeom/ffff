
import socket
import time
import signal
import sys
import threading

class WebServer(object):

    def __init__(self, host,port):
        signal.signal(signal.SIGINT, self.shutdown)
        self.host = host
        self.port = int(port)
        print(self.host)
        print(self.port)


    def start(self):
        self.socket = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        try:
           self.socket.bind((self.host,self.port))
        except Exception:
            print("Bind Exception use another IP PORT")
            sys.exit(1)
        print('ready to serves')
        self.my_listen()

    def shutdown(self,sig,frame):
        try:
            print("Close Socket")
            self.socket.shutdown(socket.SHUT_RDWR)
        except Exception:
            pass
        sys.exit(0)

    def make_header(self, response_code, content_type):
        header = ''
        if response_code == 200:
            header += 'HTTP/1.1 200 OK\n'
            if content_type == 'http':
                header += 'Content-Type: text/http\n'
            elif content_type == 'ico':
                header += 'Content-Type image/ico\n'
            elif content_type == 'jpg':
                header += 'Content-Type image/jpg\n'

        elif response_code == 404:
            header += 'HTTP/1.1 404 Not Found\n'
        time_now = time.strftime("%a, %d %b %Y %H:%M:%S", time.localtime())
        header += 'Date: {now}\n'.format(now=time_now)
        header += 'Server: HoBeom Web Server\n'
        header += 'Connection: close\n\n'
        return header

    def my_listen(self):
        self.socket.listen(5)
        while True:
            (client, address) = self.socket.accept()
            client.settimeout(30)
            print("Recieved from {addr}".format(addr=address))
            threading.Thread(target=self.my_handler, args=(client, address)).start()

    def my_handler(self, client, address):
        PACKET_SIZE = 1024
        while True:
            print("CLIENT", client)
            data = client.recv(PACKET_SIZE).decode()

            if not data: break

            request_method = data.split(' ')[0]
            print("Method: {m}".format(m=request_method))
            print("Request Body: {b}".format(b=data))

            if request_method == "GET" or request_method == "HEAD":
                file_request = data.split(' ')[1]
                file_request = file_request.split('?')[0]
                if file_request == "/":
                    file_request = "HelloWorld.html"
                else:
                    file_request = file_request[1:]
                if '.' in file_request:
                    file_type = file_request.split('.')[1]
                else:
                    file_request +='html'
                    file_type = 'html'
                # Load and Serve files content
                try:
                    print(file_request)
                    f = open(file_request, 'rb')
                    if request_method == "GET":  # Read only for GET
                        response_data = f.read()
                    f.close()
                    response_header = self.make_header(200,file_type)

                except IOError:
                    response_header = self.make_header(404,'unknown')
                    print("send 404 page")
                    if request_method == "GET":  # load 404page
                        try:
                            response_data = open("Notfound.html",'rb').read()
                        except IOError:
                            response_data = """<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8">
                            <title>요청하신 페이지를 찾을수 없습니다.</title></head>
                            <body><a href="url"><img src="/404error.png" border="0"></a><h1>Error 404: File not found
                            </h1><p>Go to <a href="/">homepage</a>.</p></body></html>"""
                response = response_header.encode()
                if request_method == "GET":
                    response += response_data

                client.send(response)
                print("{fp} is Done".format(fp=file_request))
                client.close()
                break
            else:
                print("Unknown HTTP request method: {method}".format(method=request_method))

if len(sys.argv) <= 1:
    print('Usage : "python WebServer.py your_ip server_port')
    exit(2)
server = WebServer(sys.argv[1],sys.argv[2])
print("Press Ctrl+C to shut down server.")
server.start()
#Requirement python 3
#Reference
# https://gist.github.com/joncardasis/cc67cfb160fa61a0457d6951eff2aeae

# 2+클라이언트 구현 client.py server_host server_port filePath\filename
