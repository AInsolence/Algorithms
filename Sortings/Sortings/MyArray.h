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

	int size()
	{
		return _vector->size();
	}

	void bubbleSort()
	{
		for (int j = size(); j >= 0; j--) {
			for (int i = 0; i < j - 1; i++) {
				if ((*_vector)[i] > (*_vector)[i + 1])
				{
					std::swap((*_vector)[i], (*_vector)[i + 1]);
				}
			}
		}
	}

	void print()
	{
		for (int i = 0; i < size(); i++)
		{
			std::cout << (*_vector)[i] << " ";
		}
		std::cout << "\n";
	}

private:
	std::vector<T>* _vector;
};

