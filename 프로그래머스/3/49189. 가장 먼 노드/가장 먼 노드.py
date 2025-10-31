def solution(n, edges):
    graph = init_graph(edges)
    
    visited_nodes = set()
    current_nodes = set()
    next_nodes = set([1])
    
    while not is_all_leaf(graph, next_nodes):
        
        current_nodes = next_nodes
        next_nodes = set()
        
        for current_node in current_nodes:
            visited_nodes.add(current_node)
            next_nodes |= graph[current_node]
        next_nodes -= visited_nodes
    
    return len(current_nodes)


def is_all_leaf(graph, nodes):
    for node in nodes:
        if node in graph.keys():
            return False
    return True


def init_graph(edges):
    graph = dict()
    for edge in edges:
        [node1, node2] = edge
        if node1 not in graph.keys():
            graph[node1] = set()
        if node2 not in graph.keys():
            graph[node2] = set()
        graph[node1].add(node2)
        graph[node2].add(node1)
    return graph
