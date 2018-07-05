#include "stdafx.h"
#include "CppUnitTest.h"
#include <random>
#include <iostream>
#include "../Sortings/MyArray.h"

using namespace Microsoft::VisualStudio::CppUnitTestFramework;

namespace UnitTestArray
{
	MyArray<int>* emptyArray = new MyArray<int>();
	MyArray<int>* oneElementArray = new MyArray<int>();
	MyArray<int>* randomArray = new MyArray<int>();
	MyArray<int>* inversedArray = new MyArray<int>();
	MyArray<int>* sortedArray = new MyArray<int>();

	TEST_CLASS(UnitTest1)
	{
	public:

		void setUp()
		{
			oneElementArray->push(0);
			for (size_t i = 0; i < 100; i++) {
					sortedArray->push(i);
					randomArray->push(std::rand());
					inversedArray->push(10 - i);
			}
		}

		void tearDown()
		{

		}
		TEST_METHOD(PrintCreatedArrays)
		{
			emptyArray->print();
			oneElementArray->print();
			randomArray->print();
		}
		TEST_METHOD(bubbleSortTest)
		{
			emptyArray->bubbleSort();
			oneElementArray->bubbleSort();
			randomArray->bubbleSort();
			inversedArray->bubbleSort();
			for (size_t i = 0; i < inversedArray->size(); i++) {
				int expected = i;
				int actual = (*inversedArray)[i];
				Assert::AreEqual(expected, actual);
			}
		}

	};
}