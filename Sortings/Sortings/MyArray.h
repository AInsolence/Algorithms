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

	T operator [](int index)
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
		size_t lenght = size();
		for (size_t j = lenght - 1; j > 0; j--) {
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
		size_t lenght = size();
		size_t maxElementIndex = lenght - 1;
		for (size_t j = lenght - 1; j > 0; j--) {
			maxElementIndex = j;
			for (size_t i = 0; i < j; i++) {
				if((*_vector)[i] >(*_vector)[maxElementIndex]) maxElementIndex = i;
			}
			if ((*_vector)[j] != (*_vector)[maxElementIndex]){
				std::swap((*_vector)[j], (*_vector)[maxElementIndex]);
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
