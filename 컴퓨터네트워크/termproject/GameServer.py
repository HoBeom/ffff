import socket
import time
import signal
import sys
import threading
import os

class GameServer(object):

    def __init__(self):
        signal.signal(signal.SIGINT, self.shutdown)
        self.host = input("host IP :")
        self.port = 12535
        print(self.host)
        print(self.port)
        if not os.path.isdir('./user'):
            os.mkdir('./user')

    def start(self):
        self.socket = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        try:
           self.socket.bind((self.host,self.port))
        except Exception:
            print("Bind Exception use another IP(hostname) or check permission of port 12535")
            sys.exit(1)
        self.my_listen()

    def shutdown(self,sig,frame):
        try:
            print("Close Socket")
            #boradcast shutdown message to client

            #socket close
            self.socket.shutdown(socket.SHUT_RDWR)
        except Exception:
            pass
        sys.exit(0)

    def my_listen(self):
        self.socket.listen(10)
        while True:
            print('ready to serves')
            (client, address) = self.socket.accept()
            client.settimeout(350)
            print("Recieved from {addr}".format(addr=address))
            threading.Thread(target=self.my_handler, args=(client, address)).start()

    def my_handler(self, client, address):
        PACKET_SIZE = 1024
        str_level=''
        str_exp=''
        is_login = False
        login_time = time.strftime("%a, %d %b %Y %H:%M:%S", time.localtime())
        while True:
            data = client.recv(PACKET_SIZE).decode()
            data = data.split('\n')
            if data[0] == 'toServer':
                if data[1] == 'login':
                    try:
                        id = data[2].split(':')[1].split('\n')[0]
                        ps = data[3]
                        ##로그인
                        if os.path.isfile('./user/'+id+'.txt'):
                            print('path exist')
                            logfile = open('./user/'+id+'.txt','r')
                            ps_infile = logfile.readline().split('\n')[0]
                            if ps_infile == ps:
                                str_level = logfile.readline().split(':')[1]
                                str_exp = logfile.readline().split(':')[1]
                                message = 'toClient\nlogin\n'+id+'\n'+str_level+str_exp+login_time+'\n\n'
                                #print(message)
                                client.send(message.encode())
                                print("login:"+id+': '+login_time)
                                logfile.close()
                                is_login = True
                                break
                            else:
                                ##비밀번호 오류!
                                client.send(('toClient\nlogin\nfail\n\n').encode())
                                logfile.close()
                        else:
                            ##자동회원가입
                            str_level = 'level:1\n'
                            str_exp = 'exp:0\n'
                            logfile = open('./user/' + id + '.txt', 'wt')
                            logfile.write(ps + '\n')
                            logfile.write(str_level)
                            logfile.write(str_exp)
                            client.send(('toClient\nlogin\nnewuser\n' + login_time + '\n\n').encode())
                            logfile.close()
                            str_level = str_level.split(':')[1]
                            str_exp = str_exp.split(':')[1]
                            is_login = True
                            break
                    except FileExistsError as e:
                        print(e.args)
                        print("File read error")
                        client.send(('toClient\nerror\n' + login_time + '\n\n').encode())
                        break
            else:
                print("Not found toServer header")
                client.send(('toClient\nerror\n' + login_time + '\n\n').encode())
                break
        exp = int(str_exp.split('\n')[0])
        level = int(str_level.split('\n')[0])
        #로그인 성공시
        while is_login:
            recv_message = client.recv(PACKET_SIZE).decode()
            data = recv_message.split('\n')

            if data[0] == 'toServer':
                if data[1] == 'logout':
                    print(id+" logout")
                    is_login = False
                elif data[1] == 'wait':
                    threading.Thread(target=self.alive_sender, args=(client,id)).start()
                elif data[1] == 'imAlive':
                    exp+=10
                    if exp == level*level*10+10:
                        level+=1
                        exp=0
                    print('resive imAlive packet :'+id+' level:'+str(level)+'  exp:'+str(exp))
                    try:
                        logfile = open('./user/' + id + '.txt', 'w')
                        logfile.write(ps + '\n')
                        logfile.write('level:'+str(level)+'\n')
                        logfile.write('exp:'+str(exp)+"\n")
                        logfile.close()
                        print('file rewrite done')
                    except:
                        print('file write error')
                        logfile.close()
                        pass
                    client.send(("toClient\nshow\n{level}\n{exp}\n\n".format(level=level,exp=exp)).encode())
                elif data[1] == 'check':
                    print('check call')
                    client.send(("toClient\ncheck\n{level}\n{exp}\n\n".format(level=level,exp=exp)).encode())
            else:
                print('User error with : '+id)
                break

    def alive_sender(self,client,id):
        isAlive = True
        time.sleep(3)
        while isAlive:
            print('Send isAlive packet to ' + id)
            try:
                client.send(('toClient\nisAlive\n\n').encode())
            except:
                break
            time.sleep(30)

if __name__ == '__main__':
    server = GameServer()
    server.start()