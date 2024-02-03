def solution(records):
    users = dict()
    answer = list()

    for record in records:
        init_users(users, *record.split())
    for record in records:
        run(answer, users, *record.split())
    return answer

def init_users(users, command, user_id, user_name=None):
    if command == "Enter" or command == "Change":
        users[user_id] = user_name
    pass


def run(answer, users, command, user_id, user_name=None):
    if command == "Enter":
        answer.append(run_enter(users, user_id))
    elif command == "Leave":
        answer.append(run_leave(users, user_id)) 
    elif command == "Change":
        pass
    else: 
        return "Undefined Command Error"

    
def run_enter(users, user_id):
    welcome_message = lambda name: f"{name}님이 들어왔습니다."
    return welcome_message(users[user_id])

def run_leave(users, user_id):
    goodbye_message = lambda name: f"{name}님이 나갔습니다."
    return goodbye_message(users[user_id])

