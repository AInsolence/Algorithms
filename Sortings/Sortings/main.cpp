#include <iostream>
#include "MyArray.h"

int main(int argc, char* argv[]) 
{
	MyArray<double>* Array = new MyArray<double>();
	Array->push(101);
	Array->push(99);
	Array->push(125);
	Array->push(37);
	//Array->pop();
	Array->print();
	Array->bubbleSort();
	Array->print();
	std::cout << "\n";
	system("pause");
	return 0;
}