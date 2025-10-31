#include <stdio.h>

short arr[501][501];
int m, n, res[501][501];

int func(int x, int y){
	if(res[x][y]!=-1){
		return res[x][y];
	}
	
	res[x][y]=0;
	if(x<m && arr[x][y]>arr[x+1][y]){
		res[x][y]+=func(x+1, y);
	}

	if(y<n && arr[x][y]>arr[x][y+1]){
		res[x][y]+=func(x, y+1);
	}

	if(x-1 && arr[x][y]>arr[x-1][y]){
		res[x][y]+=func(x-1, y);
	}

	if(y-1 && arr[x][y]>arr[x][y-1]){
		res[x][y]+=func(x, y-1);
	}

	return res[x][y];
}

int main(void){
	int i, j;
	scanf("%d %d",  &m, &n);

	for(i=1; i<=m; ++i){
		for(j=1; j<=n; ++j){
			res[i][j]=-1;
			scanf("%d", &arr[i][j]);
		}
	}
	res[m][n]=1;
	printf("%d", func(1, 1));

	return 0;
}