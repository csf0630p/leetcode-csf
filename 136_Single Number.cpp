class Solution {
public:
    int singleNumber(vector<int>& nums) 
    {
        int s = 0;
        for (int i = 0; i < nums.size(); i++)
        {
            s ^= nums[i];
        }
        return s;
    }
};

//xor 满足交换律 结合律 自反律