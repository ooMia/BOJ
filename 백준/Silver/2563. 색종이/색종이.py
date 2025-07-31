import sys


def paint_square(canvas, from_x, from_y, length):
    for y in range(length):
        for x in range(length):
            if canvas[from_y + y][1 + from_x + x] == 0:
                canvas[from_y + y][0] += 1
                canvas[from_y + y][1 + from_x + x] = 1


I = sys.stdin.readline
canvas = [[0 for _ in range(1 + 100)] for _ in range(100)]

for _ in range(int(I())):
    from_x, from_y = map(int, I().split())
    paint_square(canvas, from_x, from_y, 10)

print(sum([row[0] for row in canvas]))