import collections
import sys

# 0. fixed-size, non-directional, densible
IN = list(map(lambda x: list(map(int, x.split())), sys.stdin.readlines()))
N, M = IN[0]

# 1. make graph
# 1-1. adjacency matrix
adjM = [[0 for col in range(N + 1)] for row in range(N + 1)]

# 1-2. non-directional graph
needToVisit = set(range(1, N + 1))
for linkage in IN[1:]:
    nodeA, nodeB = linkage
    adjM[nodeA][nodeB] = adjM[nodeB][nodeA] = 1


# 2. search graph
def bfs(graph, possibles: set):
    Q = collections.deque()
    nComp = 0

    # 2-2. repeat searching for unvisited nodes
    while len(possibles) > 0:
        Q.append(possibles.pop())

        # 2-1. BFS
        while len(Q) > 0:
            startNode = Q.popleft()  # (pl&a | p&al)
            tmp = []
            for node in possibles:
                if graph[startNode][node] == 1:
                    tmp.append(node)
            for node in tmp:
                possibles.remove(node)
                Q.append(node)  # (pl&a | p&al)
            # print(Q)
        nComp += 1
    return nComp


print(bfs(adjM, needToVisit))
