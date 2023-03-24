import os
In = list(map(int,os.read(0,os.fstat(0).st_size).split()))

def solve(edges):
    edges.sort()
    if edges[0] <= 0: return;
    elif edges[0] == edges[-1]: print("Equilateral")
    elif sum(edges[:2]) <= edges[-1]: print("Invalid")
    elif edges[1] == edges[0] or edges[1] == edges[-1]: print("Isosceles")
    else: print("Scalene")

for n in range(0, len(In)//3-1):
    solve(In[n*3:n*3+3])
