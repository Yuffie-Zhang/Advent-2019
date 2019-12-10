import java.util.*;

class Main {
  static HashSet<int[]> permutation=new HashSet<int[]>();
  public static void main(String[] args){
    int[] input={3,8,1001,8,10,8,105,1,0,0,21,42,67,76,89,110,191,272,353,434,99999,3,9,102,2,9,9,1001,9,2,9,1002,9,2,9,1001,9,2,9,4,9,99,3,9,1001,9,4,9,102,4,9,9,101,3,9,9,1002,9,2,9,1001,9,4,9,4,9,99,3,9,102,5,9,9,4,9,99,3,9,1001,9,3,9,1002,9,3,9,4,9,99,3,9,102,3,9,9,101,2,9,9,1002,9,3,9,101,5,9,9,4,9,99,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,99,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,99,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,99,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,99};
    int[] nums={0,1,2,3,4};
    int lastoutput=0;
    int res=Integer.MIN_VALUE;
    List<List<Integer>> permuatations=getpermutation(nums);
    for(List<Integer> permuatation: permuatations){
      lastoutput=0;
      for(int i:permuatation ){
        int[] inputcopy=new int[input.length];
        for(int j=0;j<input.length;j++){
          inputcopy[j]=input[j];
        }
        int out = IntCode(inputcopy,i,lastoutput);
        lastoutput=out;
      }
      res=res<lastoutput?lastoutput:res;
    }
    System.out.print(res);


  }

  public static List<List<Integer>> getpermutation(int[] nums) {
        List<List<Integer>> res=new LinkedList<List<Integer>>();
        List<Integer> tmp=new LinkedList<Integer>();        
            dfs(res,tmp,nums);
        return res;
    }
    public static void dfs(List<List<Integer>> res, List<Integer> tmp,int[] nums){
        if(tmp.size()==nums.length){
            res.add(new LinkedList<Integer>(tmp));
            //return;
        }
        
        for(int i=0;i<nums.length;i++){
            if(tmp.contains(nums[i])) continue;
            tmp.add(nums[i]);
            dfs(res,tmp,nums);
            tmp.remove(tmp.size()-1);
        }
        
    }
  public static int IntCode(int[] in,int signal,int lastoutput) {
    int[] input=in;
    int diagnostic = Integer.MAX_VALUE;
    int sig=signal;
    int lastout=lastoutput;
    boolean signalused=false;
    int m=0;
    int n=0;
    for(int i=0;i<input.length;){
      if(input[i]==99){
        break;
      }
      int[] digit=getdigits(input[i]);
      switch(digit[0]){
        case 1:
        m= digit[1]==0?input[input[i+1]]:input[i+1];
        n= digit[2]==0?input[input[i+2]]:input[i+2];
        input[input[i+3]]=m+n;
        i+=4;
        break;
        case 2:
        m= digit[1]==0?input[input[i+1]]:input[i+1];
        n= digit[2]==0?input[input[i+2]]:input[i+2];
        input[input[i+3]]=m*n;
        i+=4;
        break;
        case 3:
        if(!signalused){
          m=sig;
          signalused=true;
        }
        else{
          m=lastout;
        }
        input[input[i+1]]=m;
        i+=2;
        break;
        case 4:
        m= digit[1]==0?input[input[i+1]]:input[i+1];
        diagnostic=m;
        i+=2;
        break;
        case 5:
        m= digit[1]==0?input[input[i+1]]:input[i+1];
        n= digit[2]==0?input[input[i+2]]:input[i+2];
        if(m!=0){  
          i+=n;       
        }
        else{i+=3;}
        break;
        case 6:
        m= digit[1]==0?input[input[i+1]]:input[i+1];
        n= digit[2]==0?input[input[i+2]]:input[i+2];
        if(m==0){  
          i+=n;       
        }
        else{i+=3;}
        break;
        case 7:
        m= digit[1]==0?input[input[i+1]]:input[i+1];
        n= digit[2]==0?input[input[i+2]]:input[i+2];
        input[input[i+3]]=m<n?1:0;
        i+=4;
        break;
        case 8:
        m= digit[1]==0?input[input[i+1]]:input[i+1];
        n= digit[2]==0?input[input[i+2]]:input[i+2];
        input[input[i+3]]=m==n?1:0;
        i+=4;
        break;
      }
    }
    return diagnostic;
  }
  private static int[] getdigits(int input){
    int instruction = input<100?input:input%100;
    int[] res=new int[4];
    res[0]=instruction;
    input=input/100;
    int index=1;
    while(input>0){
      res[index++]=input<10?input:input%10;
      input/=10;
    }
    return res;
  }
}
