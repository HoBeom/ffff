/*
Suit: club,diamond,heart,spade
Rank: ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, jcak, queen, king
ace 는 1혹은 14로 사용된다.
5장의 카드로 가장 높은 점수
straight flush (straight이면서 flush)
four cards (4장이 동일한 rank)
full house (하나의 triple과 하나의 pair)
flush (5장 모두 동일한 suit)
straight (5장 모두 연속된 rank)
triple (3장이 동일한 rank)
two pair (pair가 2개)
pair (2장이 동일한 rank)
high card(위의 어떤 경우에도 해당되지 않음.
-사용자가 5장의 카드를 입력하면 어떤 패인지 출력한다.
ex) 2s -> 2 spade, 6h -> 6 heart
rank는 1에서 13까지의 정수로 표현
suit는 0에서 3까지의 정수로 표현
*/
#include <stdio.h>
#define NUM_RANKS 13
#define NUM_SUITS 4
#define NUM_CARDS 5
int num_in_rank[NUM_RANKS+1];
int num_in_suit[NUM_SUITS];
int card_grid[NUM_SUITS][NUM_RANKS+1];
void read_cards(void);
void analyze_hand(void);
void init_data(void);
int check_flush();
int check_straight();
int check_four_cards();
int check_three_cards();
int count_pairs();
enum score
{
	straightflush = 8,
	fourcards = 7,
	fullhouse = 6,
	flush = 5,
	straight = 4,
	triple = 3,
	twopair = 2,
	pair = 1,
	highcard = 0,
};
int main() {
	while (1)
	{
		init_data();
		read_cards();
		analyze_hand();
	}
	return 0;
}
void init_data() {
	int rank, suit;
	int ;
	for (rank = 0; rank < NUM_RANKS; rank++) {
		num_in_rank[rank] = 0;
		for (suit = 0; suit < NUM_SUITS; suit++)
			card_grid[suit][rank] = 0;
	}
	for (suit = 0; suit < NUM_SUITS; suit++)
		num_in_suit[suit] = 0;
}
void read_cards() {
	int rank, suit;
	int cards_read = 0;
	char rank_ch, suit_ch;
	while (cards_read < NUM_CARDS) {
		printf("input:");
		scanf_s(" %c",&rank_ch);
		scanf_s(" %c", &suit_ch);
		switch (rank_ch)
		{
			case 'a': rank = 1; break;
			case '2': rank = 2; break;
			case '3': rank = 3; break;
			case '4': rank = 4; break;
			case '5': rank = 5; break;
			case '6': rank = 6; break;
			case '7': rank = 7; break;
			case '8': rank = 8; break;
			case '9': rank = 9; break;
			case 't': rank = 10; break;
			case 'j': rank = 11; break;
			case 'q': rank = 12; break;
			case 'k': rank = 13; break;
			default: printf("input error\n");
				continue;
		}
		switch (suit_ch)
		{
			case 'c': suit = 0; break;
			case 'd': suit = 1; break;
			case 'h': suit = 2; break;
			case 's': suit = 3; break;
			default: printf("input error\n");
				continue;
		}
		if (card_grid[suit][rank] == 1) {
			printf("alread have\n");
			continue;
		}
		card_grid[suit][rank]++;
		num_in_rank[rank]++;
		num_in_suit[suit]++;
		cards_read++;
	}
}
void analyze_hand() {
	score sc = highcard;
	if (count_pairs() == 1) {
		if (check_three_cards())
			sc = fullhouse;
		else
			sc = pair;
	}
	else if (count_pairs() == 2)
		sc = twopair;
	if (check_three_cards() && sc < triple)
		sc = triple;
	if (check_straight() && sc < straight) {
		if (check_flush())
			sc = straightflush;
		else
			sc = straight;
	}
	if (check_flush() && sc < flush)
		sc = flush;
	if (check_four_cards() && sc < fourcards)
		sc = fourcards;
	switch (sc)
	{
	case straightflush:
		printf("straightflush\n");
		break;
	case fourcards:
		printf("fourcards\n");
		break;
	case fullhouse:
		printf("Full House\n");
		break;
	case flush:
		printf("Flush\n");
		break;
	case straight:
		printf("Straight\n");
		break;
	case triple:
		printf("Triple\n");
		break;
	case twopair:
		printf("Two Pair\n");
		break;
	case pair:
		printf("One Pair\n");
		break;
	case highcard:
		printf("YOU DIE! GO HOME NOW!\n");
		break;
	}
	printf("Your cards :");
	for (int rank = 1; rank < NUM_RANKS+1; rank++) {
		for (int suit = 0; suit < NUM_SUITS; suit++)
			if (card_grid[suit][rank] == 1)
				switch (suit)
				{ 
				case 0: printf(" %dcluber", rank); break;
				case 1: printf(" %ddiamond", rank); break;
				case 2: printf(" %dheart", rank); break;
				case 3: printf(" %dspade", rank); break;
				}
	}printf("\n");
}
int count_pairs() {
	int rank,pair = 0;
	for (rank = 1; rank < NUM_RANKS; rank++) {
		if (num_in_rank[rank] == 2)
			pair++;
	}
	return pair;
}
int check_three_cards() {
	int rank;
	for (rank = 1; rank < NUM_RANKS; rank++) {
		if (num_in_rank[rank] == 3)
			return 1;
	}
	return 0;
}
int check_four_cards() {
	int rank;
	for (rank = 1; rank < NUM_RANKS; rank++) {
		if (num_in_rank[rank] == 4)
			return 1;
	}
	return 0;
}
int check_flush() {
	int suit;
	for (suit = 0; suit < NUM_SUITS; suit++) {
		if (num_in_suit[suit] == 5)
			return 1;
	}
	return 0;
}
int check_straight() {
	int rank,count=0;
	for (int i  = 0; i < NUM_RANKS+4; i++) {
		rank = (i % 13)+1;
		if (num_in_rank[rank] > 1)
			return 0;
		else if (num_in_rank[rank] == 0)
			count = 0;
		else
		{
			count++;
			if (count == 5)
				return 1;
		}
	}
	return 0;
}