#include <stdio.h>

int stack[10001], sp = -1;

void push(int X) { stack[++sp] = X; }
int size() { return sp + 1; }
int empty() { return (sp == -1); }
int top() { return empty() ? -1 : stack[sp]; }
int pop() { return empty() ? -1 : stack[sp--]; }

int main()
{
    int argc, temp;
    char argv[20];

    scanf(" %d", &argc);

    for (int i = argc; i > 0; --i)
    {
        scanf(" %s", argv);

        switch (argv[0])
        {
        case 'p':
            if (argv[1] == 'u') { scanf(" %d", &temp); push(temp); break; }
            printf("%d\n", pop()); break;
        case 's': printf("%d\n", size()); break;
        case 'e': printf("%d\n", empty()); break;
        case 't': printf("%d\n", top()); break;
        default: break;
        }
    }
    return 0;
}