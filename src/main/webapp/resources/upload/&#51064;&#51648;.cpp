#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>

#define SIDE		3
#define SIZE	  	SIDE * SIDE			// board size




/*

일단 세 가지 경우 모두 최적의 경로를 탐색하게 됩니다.
BFS 의 경우 어떤 노드에서 골까지의 휴리스틱값(h) 적용이 불가능하게 됩니다.
Start 노드에서 지금 상태의 노드까지의 거리인 g 값만을 가지고 경로를 찾게 되기 때문에 search cost 가 많이 듭니다.
h1을 구하여 적용 했을 경우에는 적용하지 않았던 BFS 보다 훨씬 적은 search cost가 들었고 그만큼 빠르게 최적 경로를 탐색했습니다.
h2을 구하여 적용 했을 경우에는 h1을 적용했을 때 보다 적은 search cost가 들었고 빠르게 최적 경로를 탐색했습니다.
각 경우의 h 값에 대하여
BFS에서는 h0 =0이고,  h1과 h2의 경우 h1<=h2 <=h*입니다.  h*는 현 노드에서 골까지의 실 거리입니다.  이 경우를 다시 정리하면 h0<=h1<=h2=<=h* 이 됩니다.
h의 값이 h*값에 가까이 갈수록 실제 더 빠르게 정확한 탐색을 할수 있습니다.


*/



// data structure for states
struct listnode {
	struct listnode		*parent;		// parent state
	char				board[SIZE];	// board arrangement
	int					g;				// cost from Start to this state
	int					h;				// estimated cost from this state to Goal
	int					f;				// f = g + h
	struct listnode		*next;
};
typedef struct listnode STATE;

// Global variables for states and state pointers
STATE OPEN, CLOSED;						// dummy head for OPEN and CLOSED list
STATE STARTSTATE, GOALSTATE;				// Start and Goal states

STATE *open_ptr = &OPEN, *closed_ptr = &CLOSED;
STATE *start = &STARTSTATE, *goal = &GOALSTATE, *current, *child[4];

// functions
void generate_children(STATE *s);
int compute_h(STATE *s);
int check_open(STATE *s);
int check_closed(STATE *s);
int is_same(STATE *s1, STATE *s2);
void insert(STATE *l, STATE *s);
void print_board(STATE *s);
void print_solution(STATE *s);

void main()
{
	char str[10];
	int i, state_count = 0;

	// input start state (ex> 1238x4765) 
	printf("\n\n Start position : ");
	scanf("%s", str);
	for (i = 0; i < SIZE; i++) start->board[i] = str[i];

	// input goal state 
	printf("\n Goal position :  ");
	scanf("%s", str);
	for (i = 0; i < SIZE; i++) goal->board[i] = str[i];

	start->parent = NULL;
	start->g = 0;
	start->h = compute_h(start);
	start->f = start->g + start->h;

	// A* search 
	insert(open_ptr, start);
	while (open_ptr->next != NULL) {
		//printf(".");
		state_count++;

		// choose
		current = open_ptr->next;
		open_ptr->next = open_ptr->next->next;

		// goal test
		if (is_same(current, goal)) break;

		// expand
		else {
			generate_children(current);
			for (i = 0; i < 4; i++) {
				if (child[i] != NULL)
					if (!check_open(child[i]))
						if (!check_closed(child[i]))
							insert(open_ptr, child[i]);
			}
			insert(closed_ptr, current);
		}
	}

	// display search cost and path cost
	printf("\n search cost = %d", state_count);
	printf("\n path cost   = %d \n\n", current->g);
	getchar();

	// display solution path
	print_solution(current);
	getchar();

}

// generate next states (child[i]) of s
void generate_children(STATE *s)
{
	int i, blank, row, col;

	for (i = 0; i < 4; i++) child[i] = NULL;

	for (i = 0; i < SIZE; i++)
		if (s->board[i] == 'x') blank = i;
	row = blank / SIDE;
	col = blank % SIDE;

	// down 
	if (row != SIDE - 1) {
		child[0] = (STATE *)malloc(sizeof(STATE));
		*child[0] = *s;
		child[0]->board[blank] = child[0]->board[blank + SIDE];
		child[0]->board[blank + SIDE] = 'x';
	}
	// right 
	if (col != SIDE - 1) {
		child[1] = (STATE *)malloc(sizeof(STATE));
		*child[1] = *s;
		child[1]->board[blank] = child[1]->board[blank + 1];
		child[1]->board[blank + 1] = 'x';
	}
	// up 
	if (row != 0) {
		child[2] = (STATE *)malloc(sizeof(STATE));
		*child[2] = *s;
		child[2]->board[blank] = child[2]->board[blank - SIDE];
		child[2]->board[blank - SIDE] = 'x';
	}
	// left 
	if (col != 0) {
		child[3] = (STATE *)malloc(sizeof(STATE));
		*child[3] = *s;
		child[3]->board[blank] = child[3]->board[blank - 1];
		child[3]->board[blank - 1] = 'x';
	}

	for (i = 0; i < 4; i++) {
		if (child[i]) {
			if ((s->parent != NULL) && (is_same(child[i], s->parent))) {
				free(child[i]);
				child[i] = NULL;
			}
			else {
				child[i]->parent = s;
				child[i]->g = s->g + 1;
				child[i]->h = compute_h(child[i]);
				child[i]->f = child[i]->g + child[i]->h;
			}
		}
	}
}

// compute heuristic value h of state s
int compute_h(STATE *s)
{
	////////////////////////////////////////
	//
	// h = estimated distance from s to Goal	
	// write your code to compute various h
	//

	/*

일단 세 가지 경우 모두 최적의 경로를 탐색하게 됩니다.
BFS 의 경우 어떤 노드에서 골까지의 휴리스틱값(h) 적용이 불가능하게 됩니다.
Start 노드에서 지금 상태의 노드까지의 거리인 g 값만을 가지고 경로를 찾게 되기 때문에 search cost 가 많이 듭니다.
h1을 구하여 적용 했을 경우에는 적용하지 않았던 BFS 보다 훨씬 적은 search cost가 들었고 그만큼 빠르게 최적 경로를 탐색했습니다.
h2을 구하여 적용 했을 경우에는 h1을 적용했을 때 보다 적은 search cost가 들었고 빠르게 최적 경로를 탐색했습니다.
각 경우의 h 값에 대하여
BFS에서는 h0 =0이고,  h1과 h2의 경우 h1<=h2 <=h*입니다.  h*는 현 노드에서 골까지의 실 거리입니다.  이 경우를 다시 정리하면 h0<=h1<=h2=<=h* 이 됩니다.
h의 값이 h*값에 가까이 갈수록 실제 더 빠르게 정확한 탐색을 할수 있습니다.


*/


	int k = 0, k2, k3, k4;

	for (int i = 0; i < SIZE; i++)
	{
		if (s->board[i] != goal->board[i]) { // 인덱스를 이용해 같은 인덱스에 있는 값이 다르면
			int c = s->board[i];             // 현 노드의 값을 저장하고
			for (int i = 0; i < SIZE; i++) { // goal의 어느 인덱스의 값과 같은지를 찾습니다
				if (c == goal->board[i]) {
					k2 = i;
					break;
				}
			}
			if (k2 > i) {                 // 차를 구하는데 양수가 나오도록 if/else를 사용했습니다
				k3 = k2 - i; 
				k4 = k3 / 3 + k3 % 3;    // 인덱스의 차이를 3으로 나눈 몫과 3으로 나눈 나머지의 합은 가장 짧게 그 state로 이동할수 있는 거리가 됩니다
				k += k4;
			}
			else {
				k3 = i - k2;
				k4 = k3 / 3 + k3 % 3;
				k += k4;
			}

		}

	}

	int h=0;
	for (int i = 0; i < SIZE; i++) {
		if (s->board[i] != goal->board[i]) { // 현 인덱스에 저장된 값과 goal 인덱스에 저장된 값을 비교하고 틀린값이 있을때 h값을 증가시킵니다
			h++;
		}

	}

	return h; 
	//return k;
	// h 를 리턴하게 되면 number of tiles out of place 를 리턴하게 되는 것입니다
	//k를 리턴하게 되면 sum of distances out of place를 리턴하게 되는 것 입니다.

}

// check whether s is in OPEN and f(s) is smaller
// if so, change f(s) value and return TRUE
int check_open(STATE *s)
{
	STATE *l = open_ptr;

	while (l->next != NULL) {
		l = l->next;
		if (is_same(s, l) && (s->f < l->f)) {
			l->f = s->f;
			free(s);
			return(1);
		}
	}
	return(0);
}

// check whether s is in CLOSED and f(s) is smaller
// if so, change f(s) value and insert s back to OPEN
int check_closed(STATE *s)
{
	STATE *l = closed_ptr, *temp;

	while (l->next != NULL) {
		if (is_same(s, l->next) && (s->f < l->next->f)) {
			temp = l->next;
			l->next = l->next->next;
			free(temp);
			insert(open_ptr, s);
			return(1);
		}
		l = l->next;
	}
	return(0);
}

// TRUE if s1 and s2 are same
int is_same(STATE *s1, STATE *s2)
{
	int i;

	for (i = 0; i < SIZE; i++) {
		if (s1->board[i] != s2->board[i])
			return (0);
	}
	return (1);
}

// insert state s to list l. list l is in sorted order
void insert(STATE *l, STATE *s)
{
	while (l->next != NULL) {
		if (s->f < l->next->f) break;
		l = l->next;
	}
	s->next = l->next;
	l->next = s;
}

// print solution by display states sequentially from Start to Goal
void print_solution(STATE *s)
{
	if (s == NULL) return;
	print_solution(s->parent);
	getchar();
	print_board(s);
}

// display 3 x 3 board of state s
void print_board(STATE *s)
{
	int i;

	//	clrscr();
	system("cls");
	printf("\n\n");
	for (i = 0; i < SIZE; i++) {
		if (i%SIDE == 0) printf("\n");
		if (s->board[i] == 'x') printf("  %c", ' ');
		else printf("  %c", s->board[i]);
	}
	printf("\n\n");
}

