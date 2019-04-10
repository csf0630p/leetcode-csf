class Solution {
public:
int lengthOfLongestSubstring(string s) {
	int i = 0, p = 0, q = 0, temp = 0, temp2 = 0, temp3 = 0, max = 0;
	int tt[256];
    for (i = 0; i < 256; i++) tt[i] = -1;
	while (q < s.length())
	{
		temp = char(s[q]) + 128;
		if (tt[temp] == -1)
		{
			tt[temp] = q;
		}
		else
		{
			temp3 = tt[temp];
			for (i = p; i <= temp3; i++)
			{
				temp2 = char(s[i]) + 128;
				tt[temp2] = -1;
			}
			p = temp3 + 1;
			tt[temp] = q;
		}
		if ((q - p + 1) > max) max = q - p + 1;
		q++;
	}
	return (max);
	}
};