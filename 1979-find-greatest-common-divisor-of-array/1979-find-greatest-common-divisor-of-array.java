class Solution {
    public int findGCD(int[] nums) {
        int sm = nums[0];
        int lg = nums[0];
        for(int i=0; i<nums.length; i++){
            if(nums[i]<sm){
                sm = nums[i];
            } 
            if(nums[i]>lg){
                lg = nums[i];
            }
        }
        while(lg >0 && sm> 0){
            if(lg>sm){
                lg= lg%sm;
            } else{
            sm = sm%lg;
            }
            if (lg == 0) return sm;
            if (sm == 0) return lg;
           
        }
         return Math.max(lg, sm);
        }
 
    }
