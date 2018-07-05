#include "stdafx.h"
#include "CppUnitTest.h"
#include <random>
#include <iostream>
#include "../Sortings/MyArray.h"

using namespace Microsoft::VisualStudio::CppUnitTestFramework;

namespace UnitTestArray
{
	MyArray<int>* emptyArray = new MyArray<int>();
	constexpr int MAX_ELEMENT = 99;

	TEST_CLASS(UnitTest1)
	{
	public:

		void setUp()
		{
			
		}

		void tearDown()
		{

		}
		TEST_METHOD(bubbleSortTest)
		{
			MyArray<int>* inversedArray = new MyArray<int>();
			fillArray(inversedArray);
			inversedArray->bubbleSort();
			checkArray(inversedArray);
		}
		TEST_METHOD(selectionSortTest)
		{
			MyArray<int>* inversedArray = new MyArray<int>();
			fillArray(inversedArray);
			inversedArray->selectionSort();
			checkArray(inversedArray);
		}
		TEST_METHOD(insertionSortTest)
		{
			MyArray<int>* inversedArray = new MyArray<int>();
			fillArray(inversedArray);
			inversedArray->insertionSort();
			checkArray(inversedArray);
		}

	private:
		void fillArray(MyArray<int>* _array) {
			for (int i = MAX_ELEMENT; i > -1; i--) {
				_array->push(i);
			}
		}
		void checkArray(MyArray<int>* _array)
		{
			for (int i = 0; i < _array->size(); i++) {
				int expected = i;
				int actual = (*_array)[i];
				Assert::AreEqual(expected, actual);
			}
		}
	};
}