class Queue:
    def __init__(self):
        self._queue = []

    def run(self, command_line: str):
        commands = command_line.split()
        match commands:
            case ["push", value, *_]:
                self.__push(int(value))
                return None
            case ["pop", *_]:
                return self.__pop()
            case ["size", *_]:
                return self.__size()
            case ["empty", *_]:
                return self.__empty()
            case ["front", *_]:
                return self.__front()
            case ["back", *_]:
                return self.__back()
            case _:
                return None

    def __push(self, x):
        self._queue.append(x)

    def __pop(self):
        if self._queue:
            return self._queue.pop(0)
        else:
            return -1

    def __size(self):
        return len(self._queue)

    def __empty(self):
        return 1 if not self._queue else 0

    def __front(self):
        if self._queue:
            return self._queue[0]
        else:
            return -1

    def __back(self):
        if self._queue:
            return self._queue[-1]
        else:
            return -1


from sys import stdin


lines = stdin.readlines()
q = Queue()
for line in lines:
    res = q.run(line.strip())
    if res is not None:
        print(res)
