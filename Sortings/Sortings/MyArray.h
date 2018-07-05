#pragma once
#include <vector>
#include <algorithm>

template<class T> class MyArray
{
public:
	MyArray()
	{
		_vector = new std::vector<T>();
	}

	~MyArray()
	{
		delete _vector;
	}

	void push(T element)
	{
		_vector->push_back(element);
	}

	T operator [](size_t index)
	{
		return (*_vector)[index];
	}

	void pop()
	{
		_vector->pop_back();
	}

	size_t size()
	{
		return _vector->size();
	}

	void bubbleSort()
	{
		size_t length = size();
		if (!length) return; // if array is empty
		for (size_t j = length - 1; j > 0; j--) {
			for (size_t i = 0; i < j; i++) {
				if ((*_vector)[i] > (*_vector)[i + 1])
				{
					std::swap((*_vector)[i], (*_vector)[i + 1]);
				}
			}
		}
	}

	void selectionSort()
	{
		size_t length = size();
		if (!length) return; // if array is empty
		size_t maxElementIndex = length - 1;
		for (size_t j = length - 1; j > 0; j--) {
			maxElementIndex = j;
			for (size_t i = 0; i < j; i++) {
				if((*_vector)[i] >(*_vector)[maxElementIndex]) maxElementIndex = i;
			}
			if ((*_vector)[j] != (*_vector)[maxElementIndex]){
				std::swap((*_vector)[j], (*_vector)[maxElementIndex]);
			}
		}
	}

	void insertionSort()
	{
		size_t length = size();
		if (!length) return; // if array is empty
		for (size_t i = 0; i < length - 1; i++) {
			for (size_t j = i + 1; j > 0; j--) {
				if ((*_vector)[j] < (*_vector)[j - 1]) {
					std::swap((*_vector)[j], (*_vector)[j - 1]);
				}
				else break;
			}
		}
	}

	void print()
	{
		for (size_t i = 0; i < size(); i++)
		{
			std::cout << (*_vector)[i] << " ";
		}
		std::cout << "\n";
	}

private:
	std::vector<T>* _vector;
};
