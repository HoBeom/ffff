from socket import *
def Web_get(host,port):
    filename = host
    clientsocket = socket(AF_INET,SOCK_STREAM)

    #hostn = filename.replace("www.","",1)
    #print(hostn)

    clientsocket.connect((filename, int(port)))
    index = filename[1:].find('/')
    if index == -1:
        res = ''
    else:
        res = filename[index:]
    print(res)
    clientsocket.send(("GET /" + res + "  HTTP/1.1\n\n").encode())
    data = clientsocket.recv(9999999999)
    print(data)
    index = data.decode().find("<!doctype html>")
    if index==-1:
        pass
    else:
        print('save')
        tmpFile = open("./cache/" + filename + ".html", "wb")
        tmpFile.write(data[index:])
        tmpFile = open("./cache/" + filename + ".txt", "wb")
        tmpFile.write(data)
Web_get('www.naver.com',80)