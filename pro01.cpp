
int main()
{
	/*5번
	int n,p0, p1,p2,p3=2;
	p0 = p1 = p2 = 1;	
	scanf_s("%d", &n);
	for (; n > 2;n--) {
		p3 = p0 + p1;
		p0 = p1;
		p1 = p2;
		p2 = p3;
	}
	printf("%d", p3);
	*/
	/*7번
	int n;
	scanf_s("%d", &n);
	double result = 1.0;
	int denom = 1;
	for (int i = 1; i <= n; i++)
	{
		denom *= -2;
		result += 1.0 / denom;
	}
	printf("%lf\n", result);
	return 0;
	*/
	/*11번
	int n;
	scanf_s("%d", &n);
	while (n > 1) {
		n = n / 2;
		printf("%d\n", n);
	}
	*/
	/*12번
	int input;
	double sum = 0.0;
	for (int i = 1; i <= 10; i++) {
		scanf_s("%d ", &input);
		sum += input;
		printf("%lf  ", sum / i);
	}
	*/
	/*13번
	double rate, deposit, balance = 0.0;
	scanf_s("%lf", &rate);
	for (int i = 1; i <= 12; i++) {
		scanf_s("%lf", &deposit);
		balance = balance+deposit;
		balance += balance * rate;
		printf("%lf\n", balance);
	}
	*/
	//14번
	double x;
	scanf_s("%lf", &x);
	double sin = 0.0, cos =1.0, val=1;
	for (int i = 1; i <= 200; i++) {
		val *= x / i;
		sin += val;
		val *= x / ++i * -1;
		cos += val;
	}
	printf("%lf  %lf", sin, cos);
	
	
	
	getchar();
	getchar();

}	
