// O(n)/O(n)
class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        // Arrays.sort(bookings, (a, b) -> a[0] - b[0]);
        int[] change = new int[n + 1], res = new int[n];
        for (int i = 0; i < bookings.length; i++) {
            change[bookings[i][0] - 1] += bookings[i][2];
            change[bookings[i][1]] -= bookings[i][2];
        }
        res[0] = change[0];
        for (int i = 1; i < n; i++)
            res[i] = res[i - 1] + change[i];
        return res;
    }
}
