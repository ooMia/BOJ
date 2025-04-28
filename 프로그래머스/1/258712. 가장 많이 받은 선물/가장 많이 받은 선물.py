def solution(
    friends,
    gifts
) -> int:
    friends_dict = dict()
    for (i, friend) in enumerate(friends):
        friends_dict[friend] = i
    
    mat = make_matrix_from_to(friends_dict, gifts)
    dict_map = make_dict_map_gave_took_met(friends_dict, mat)
    answer = calc_prediction(friends_dict, mat, dict_map)
    return answer

def make_matrix_from_to(
    friends_dict,
    gifts
) -> list:
    l = friends_dict.keys()
    res = [[0 for _ in l] for _ in l]
    
    for gift in gifts:
        (giver, taker) = gift.split()
        from_= friends_dict[giver]
        to_  = friends_dict[taker]
        res[from_][to_] += 1
    
    return res

def make_dict_map_gave_took_met(
    friends_dict,
    matrix_from_to
) -> dict:
    
    sum_i_row = lambda mat, i: sum(mat[i])
    sum_i_col = lambda mat, i: list(map(sum, zip(*mat)))[i]
    
    res = dict()
    for key in friends_dict.keys():
        i = friends_dict[key]
        gave = sum_i_row(matrix_from_to, i)
        took = sum_i_col(matrix_from_to, i)
        met = gave - took
        res[key] = [gave, took, met]
        
    return res

    
def calc_prediction(
    friends_dict,
    matrix_from_to,
    dict_map_gave_took_met
) -> int:
    pred = [0 for _ in friends_dict.keys()]

    for key in friends_dict.keys():
        key_i = friends_dict[key]
        for target_key in friends_dict.keys():
            target_key_i = friends_dict[target_key]
            if key_i == target_key_i:
                continue
                
            gave = matrix_from_to[key_i][target_key_i]
            took = matrix_from_to[target_key_i][key_i]
            


            met_giver = dict_map_gave_took_met[key][-1]
            met_taker = dict_map_gave_took_met[target_key][-1]
            
            if gave < took or (gave == took and met_taker == met_giver):
                continue
                
            if took < gave or met_taker < met_giver:
                pred[key_i] += 1
                
    return max(pred)