
ps = input('password:')
ps1 = ""
for i in ps:
    ps1 += chr((ord(i)-4))
print(ps1)