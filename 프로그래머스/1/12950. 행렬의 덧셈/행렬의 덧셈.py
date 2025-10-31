def solution(arr1, arr2):
    for (row1, row2) in zip(arr1, arr2):
        for i in range(len(row1)):
            row1[i] += row2[i]
    return arr1