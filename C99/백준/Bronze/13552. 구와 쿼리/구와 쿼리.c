#include <stdio.h>
#include <stdlib.h>

typedef struct { int x, y, z; } Point;

int main(void) {
    int nPoints;
    if (scanf("%d", &nPoints) != 1) return 0;

    Point *points = NULL;
    if (nPoints > 0) {
        points = (Point*)malloc(sizeof(Point) * (size_t)nPoints);
        if (!points) return 1;
        for (int i = 0; i < nPoints; ++i) {
            if (scanf("%d %d %d", &points[i].x, &points[i].y, &points[i].z) != 3) {
                free(points);
                return 1;
            }
        }
    }

    int nQueries;
    if (scanf("%d", &nQueries) != 1) {
        free(points);
        return 0;
    }

    for (int qi = 0; qi < nQueries; ++qi) {
        int qx, qy, qz, r;
        if (scanf("%d %d %d %d", &qx, &qy, &qz, &r) != 4) break;
        long long r2 = (long long)r * (long long)r;
        int count = 0;
        for (int i = 0; i < nPoints; ++i) {
            long long dx = (long long)points[i].x - qx;
            long long dy = (long long)points[i].y - qy;
            long long dz = (long long)points[i].z - qz;
            long long dist2 = dx*dx + dy*dy + dz*dz;
            if (dist2 <= r2) ++count;
        }
        printf("%d\n", count);
    }

    free(points);
    return 0;
}