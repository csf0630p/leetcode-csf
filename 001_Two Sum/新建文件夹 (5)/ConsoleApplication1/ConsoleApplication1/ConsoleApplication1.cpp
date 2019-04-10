// ConsoleApplication1.cpp : 定义控制台应用程序的入口点。
//

#include "stdafx.h"
#include<algorithm>
#include<vector>
#include<iostream>
using namespace std;

bool compare(pair<int, int> lh, pair<int, int> rh)
{
	return lh.first < rh.first;
}

class Solution {
public:
	vector<int> twoSum(vector<int>& nums, int target)
	{
		vector<pair<int,int>> viTable;
		int i;
		for (i = 0; i < nums.size(); i++)
		{
			viTable.push_back(make_pair(nums[i], i+1));
		}
		sort(viTable.begin(), viTable.end(), compare);
		int p, q, s, ans1, ans2;
		p = q = s = ans1 = ans2 = 0;
		q = viTable.size() - 1;
		while (p < q)
		{
			s = viTable[p].first + viTable[q].first;
			if (s < target)
			{
				p++;
			}
			else if (s > target)
			{
				q--;
			}
			else if (s == target)
			{
				ans1 = viTable[p].second;
				ans2 = viTable[q].second;
				return (ans1 < ans2) ? (vector<int>{ans1, ans2}) : (vector<int>{ans2, ans1});
			}
		}
	}
};

int _tmain(int argc, _TCHAR* argv[])
{
	vector<int> a, b;
	Solution ret;
	a = vector<int>{3, 2, 4};
	b = ret.twoSum(a, 6);
	return 0;
}

