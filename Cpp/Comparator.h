#include <bits/stdc++.h>
using namespace std;

class Comparator
{ // comparator which sorts on basis of Price and Name
public:
    Comparator(int index);
    bool operator()(vector<string> v1, vector<string> v2);

private:
    int index; // data member
};