import copy

edges = {'a': [('a','b',2), ('a','c',1)],
         'b': [('b','a',2), ('b','g',4), ('b','f',2), ('b','e',3)],
         'c': [('c','a',1), ('c','d',3), ('c','e',2)],
         'd': [('d','c',3), ('d','e',2)],
         'e': [('e','b',3), ('e','c',2), ('e','d',2)],
         'f': [('f','b',2), ('f','g',1)],
         'g': [('g','b',4), ('g','f',1)]
        }

def printList(lst):
    for x in lst:
        l = removeDuplicates(lst[x])
        print (x + " = " + str(len(l)))
        l.sort(key=lambda tup: tup[0])
        for y in l:
            print(y)

def removeDuplicates(lst):
    return [t for t in (set(tuple(i) for i in lst))]

def firstIteration():
    edges2 = copy.deepcopy(edges)
    for key in edges:
        # vizinhos
        res_list = [x[1] for x in edges.get(key, [])]
        for vizinho in res_list:
            edges2[vizinho] = edges2.get(vizinho,[]) + edges.get(key,[])
    return edges2

def secondIteration(edges2):
    for key in edges:
        # vizinhos
        res_list = [x[1] for x in edges.get(key, [])]
        for v in res_list:
            new_list = [x[1] for x in edges.get(v,[])]
            for vizinho in new_list:
                edges2[vizinho] = edges2.get(vizinho,[]) + edges.get(key,[])
    return edges2

def thirdIteration(edges2):
    for key in edges:
        # vizinhos
        res_list = [x[1] for x in edges.get(key, [])]
        for v in res_list:
            new_list = [x[1] for x in edges.get(v,[])]
            for z in new_list:
                new_list2 = [x[1] for x in edges.get(z,[])]
                for vizinho in new_list2:
                    edges2[vizinho] = edges2.get(vizinho,[]) + edges.get(key,[])
    return edges2


list1 = firstIteration()
list2 = secondIteration(list1)
#list3 = thirdIteration(list2)
#printList(list1)
printList(list2)
#printList(list3)