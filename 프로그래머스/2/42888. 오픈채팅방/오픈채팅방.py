def solution(records):
    answer = list()
    [new_records, users] = init(records)
    for record in new_records:
        answer.append(run(users, *record.split()))
    return answer


def init(records):
    [new_records, users] = [list(), dict()]
    for record in records:
        init_user(new_records, users, *record.split())        
    return [new_records, users]


def init_user(new_records, users, command, user_id, user_name=None):
    if command == "Enter":
        users[user_id] = user_name
        new_records.append(f"{command} {user_id}")
    elif command == "Change":
        users[user_id] = user_name
    elif command == "Leave":
        new_records.append(f"{command} {user_id}")
    pass


def run(users, command, user_id):
    welcome_message = lambda name: f"{name}님이 들어왔습니다."
    goodbye_message = lambda name: f"{name}님이 나갔습니다."
    
    if command == "Enter":
        return welcome_message(users[user_id])
    elif command == "Leave":
        return goodbye_message(users[user_id])
    else: 
        return "Undefined Command Error"
