import socket
import signal
import sys
import time
import threading
class GameClient(object):

    def __init__(self):
        signal.signal(signal.SIGINT, self.shutdown)
        self.host = input("서버 IP를 입력해 주세요 : ")
        self.port = 12535
        yn = input(self.host+'로 연결을 시도합니다. Y/N ')
        if yn in 'y' or yn in 'Y':
            self.start()
        else:
            print("종료.")
            sys.exit()

    def shutdown(self, sig, frame):
        try:
            # send logout message to Server
            #logout(self)
            self.wait = False
            # socket close
            self.clientsocket.send(('toServer\nlogout\n').encode())
            self.clientsocket.shutdown(socket.SHUT_RDWR)
        except Exception:
            pass
        print("종료.")
        sys.exit()


    def start(self):
        self.clientsocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        try:
            self.clientsocket.connect((self.host, self.port))

            #login session
            while True:
                id = input('login\nid:')
                import getpass
                ##콘솔환경에서만 동작합니다. getpass
                ##파이참에서 동작하고싶을시에는 그냥 input으로 호출하면됩니다.
                ps = getpass.getpass("password:")
                #ps = input('password:')
                message = 'toServer\nlogin'
                message += '\nid:'+id
                ps1 = ""
                for i in ps:
                    ps1 += chr((ord(i) - 4))
                message += '\nps:'+ps1+'\n\n'
                self.clientsocket.send(message.encode())
                recv_message = self.clientsocket.recv(1024).decode()
                data = recv_message.split('\n')
                if data[0] == 'toClient':
                    if data[1] == 'login':
                        if data[2] == 'fail':
                            print('input correct password ')
                            continue
                        elif data[2] == id:
                            print("Welcome {user}".format(user=id))
                            print(data[5])
                            self.user = id
                            self.wait = True
                            self.level = int(data[3].split('\n')[0])

                            self.exp = int(data[4].split('\n')[0])
                            break;
                        elif data[2] == 'newuser':
                            print("Welcome New User  "+id)
                            print(data[3])
                            self.user = id
                            self.wait = True
                            self.level = 1
                            self.exp = 0
                            break;
                        else:
                            print(id)
                    elif data[2] == 'error':
                        continue
                    else:
                        print(data[1])
                        raise Exception
                else:
                    print(data[0])
                    raise Exception
        except Exception as e:
            print(e)
            print("서버 연결 실패")

        self.clientsocket.send(('toServer\nwait\n\n').encode())
        #threading.Thread(target=self.user_interface()).start()
        while self.wait:
            recv_message = self.clientsocket.recv(1024).decode()
            data = recv_message.split('\n')
            if data[0] == 'toClient':
                if data[1] == 'isAlive':
                    self.clientsocket.send(('toServer\nimAlive\n\n').encode())
                    print('resive isAlive packet')
                elif data[1] == 'shutdown':
                    break
                elif data[1] == 'check':
                    self.level = int(data[2])
                    self.exp = int(data[3])
                elif data[1] == 'show':
                    self.level = int(data[2])
                    self.exp = int(data[3])
                    print("USER_NAME: {user}\nLEVEL: {level}\nEXP: {exp}"
                          .format(user=self.user, level=self.level, exp=self.exp))
            else:
                break
        self.clientsocket.send(('toServer\nlogout\n').encode())
        self.clientsocket.shutdown(socket.SHUT_RDWR)

    def user_interface(self):#동작안함. 제외
        while self.wait:
            print("USER INTERFACE")
            i = int(input("1.정보확인\n2.문제풀기\n3.프로그램 종료\n입력 : "))
            if i == 1:
                self.clientsocket.send(('toServer\ncheck\n\n').encode())
                time.sleep(2)
                print("USER_NAME: {user}\nLEVEL: {level}\nEXP: {exp}"
                      .format(user=self.user, level=self.level, exp=self.exp))
            elif i == 2:
                print("NOT YET..")
            elif i == 3:
                print("exiting..")
                self.wait = False
                time.sleep(3)
                break

print("Press Ctrl+C to shut down client.")
client = GameClient()