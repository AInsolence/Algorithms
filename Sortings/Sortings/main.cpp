#include <iostream>
#include "MyArray.h"

int main(int argc, char* argv[]) 
{
	MyArray<double>* Array = new MyArray<double>();
	MyArray<double>* Array2 = new MyArray<double>();
	Array2->push(0);
	Array->push(101);
	Array->push(99);
	Array->push(125);
	Array->push(37);
	Array->push(37);
	Array->push(35);
	//Array->pop();
	Array->print();
	//Array->bubbleSort();
	//Array->selectionSort();
	Array->insertionSort();
	Array2->insertionSort();
	Array->print();
	Array2->print();
	std::cout << "\n";
	system("pause");
	return 0;
}