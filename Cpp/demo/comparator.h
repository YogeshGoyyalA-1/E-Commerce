#include <bits/stdc++.h>
using namespace std;
#ifndef COMPARATOR_H
#define COMPARATOR_H

class Comparator
{ // comparator which sorts on basis of Price and Name
private:
    int index; // data member
public:
    Comparator(int index);
    bool operator()(vector<string> v1, vector<string> v2);
};
#endif