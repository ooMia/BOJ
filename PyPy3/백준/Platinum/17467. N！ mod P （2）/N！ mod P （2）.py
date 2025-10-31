n,p=map(int,input().split())
r=1
if p-n<n:
    for i in range(n+1,p):r=r*i%p
    r=(p-1)*pow(r,p-2,p)%p
else:
    for i in range(1,n+1):r=r*i%p
print(r)