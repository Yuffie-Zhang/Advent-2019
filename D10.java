import java.util.HashMap;

class Main {
  public static void main(String[] args) {
    String inputraw=".###.#...#.#.##.#.####.. .#....#####...#.######.. #.#.###.###.#.....#.#### ##.###..##..####.#.####. ###########.#######.##.# ##########.#########.##. .#.##.########.##...###. ###.#.##.#####.#.###.### ##.#####.##..###.#.##.#. .#.#.#####.####.#..##### .###.#####.#..#..##.#.## ########.##.#...######## .####..##..#.###.###.#.# ....######.##.#.######.# ###.####.######.#....### ############.#.#.##.#### ##...##..####.####.#..## .###.#########.###..#.## #.##.#.#...##...#####..# ##.#..###############.## ##.###.#####.##.######.. ##.#####.#.#.##..####### ...#######.######...#### #....#.#.#.####.#.#.#.##";
    String raw2=".#..# ..... ##### ....# ...##";
    String[] input=inputraw.split(" ");
    char[][] map=new char[input.length][input[0].length()];
    for(int i=0;i<map.length;i++){
      for(int j=0;j<map[0].length;j++){
        map[i][j]=input[i].charAt(j);
      }
    }
    int resi=0;
    int resj=0;
    int res=Integer.MIN_VALUE;
    int[][] count=new int[map.length][map[0].length];
    for(int i=0;i<map.length;i++){
      for(int j=0;j<map[0].length;j++){
        if(map[i][j]=='.'){
          continue;
        }
        else{
          HashMap<int[][], Float> kmap=new HashMap<int[][], Float>();
          for(int m=i;m<map.length;m++){
            for(int n=0;n<map[0].length;n++){
              if(m*map[0].length+n<=i*map[0].length+j || map[m][n]=='.'){
                continue;
              }
              else{
                boolean flag=false;
                for(int[][] position: kmap.keySet()){
                  
                  float kvalue=(n-position[0][1])==0?Float.MAX_VALUE:((float)m-position[0][0])/((float)n-position[0][1]);
                  float positionk=kmap.get(position);
                  if(Float.compare(kvalue,positionk)==0){
                    flag=true;
                    break;
                  }
                }
                if(!flag){
                  int[][] position = {{m,n}};
                  float k=(n-j)==0?Float.MAX_VALUE:((float)m-i)/((float)n-j);
                  kmap.put(position,k);
                  count[i][j]++;
                  count[m][n]++;
                  res=res<count[i][j]?count[i][j]:res;
                  resi=i;
                  resj=j;
                }
                
                
              }
            }
          }

        }
      }
    }
    System.out.println(res);
    System.out.println(resi);
    System.out.println(resj);
  }
}
