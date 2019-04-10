// ConsoleApplication1.cpp : 定义控制台应用程序的入口点。
//

#include "stdafx.h"
#include <iostream>
#include <string>  
#include <vector>  
using namespace std;

int lengthOfLongestSubstring(string s) {
	int i = 0, p = 0, q = 0, temp = 0, temp2 = 0, temp3 = 0, max = 0;
	vector <int> table(256, -1);
	while (q < s.length())
	{
		temp = char(s[q]) + 128;
		if (table[temp] == -1)
		{
			table[temp] = q;
		}
		else
		{
			temp3 = table[temp];
			for (i = p; i <= temp3; i++)
			{
				temp2 = char(s[i]) + 128;
				table[temp2] = -1;
			}
			p = temp3 + 1;
			table[temp] = q;
		}
		if ((q - p) > max) max = q - p;
		q++;
	}
	return (max + 1);
	}


int _tmain(int argc, _TCHAR* argv[])
{
	string s = "abcabcbb";
	cout << lengthOfLongestSubstring(s);
	return 0;
}

