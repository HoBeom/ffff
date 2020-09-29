import matplotlib.pyplot as plt
import numpy as np
point_num = 50 # if under 50, draw with scatter 
lenth = 1.3
height = 1
width = 1
R = lenth / (point_num-1)
X = width / (point_num-1)
points = [height+.1 for _ in range(point_num)]

iter = 10000
show_iter = point_num // 10
alpha = 1e-2 / point_num
bending_momentum = 1 / point_num 
flag_end = 0

for step in range(iter):
    distance = []
    for i in range(1, point_num-1):
        x = min(point_num-i-1, i) * X
        y = height - points[i] 
        left = points[i] - points[i-1]
        right = points[i] - points[i+1]
        left, right = left*left, right*right
        r = np.sqrt(X*X + max(left, right))
        distance.append(r)
        theta = np.tan(y/x)
        if r + alpha >= R - (bending_momentum * theta):
            continue
        else:
            points[i] -= alpha
    if step % show_iter == 0:
        plt.cla()
        plt.ylim(.8,1.2)
        if point_num <= 50:
            plt.scatter([i for i in range(point_num)], points)
        plt.plot(points)
        plt.pause(1e-7)
        alpha += 1e-5 if alpha < 1e-3 else 0
    sum_dist = sum(distance)
    print(sum_dist)
    if flag_end == sum_dist:
        print(sum_dist)
        break
    flag_end = sum_dist
plt.show()
