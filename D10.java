import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

class Main {
  public static void main(String[] args) {
    String inputraw=".###.#...#.#.##.#.####.. .#....#####...#.######.. #.#.###.###.#.....#.#### ##.###..##..####.#.####. ###########.#######.##.# ##########.#########.##. .#.##.########.##...###. ###.#.##.#####.#.###.### ##.#####.##..###.#.##.#. .#.#.#####.####.#..##### .###.#####.#..#..##.#.## ########.##.#...######## .####..##..#.###.###.#.# ....######.##.#.######.# ###.####.######.#....### ############.#.#.##.#### ##...##..####.####.#..## .###.#########.###..#.## #.##.#.#...##...#####..# ##.#..###############.## ##.###.#####.##.######.. ##.#####.#.#.##..####### ...#######.######...#### #....#.#.#.####.#.#.#.##";
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
    System.out.println(part2(map,resi,resj));
  }
  public static int part2(char[][] map, int i, int j){
    int m=0;
    int n=0;
    Queue<point> queue= new PriorityQueue<point>(i*j,new Comparator<Node>() {
    public int compare(point p1, point p2) {
        return Double.compare(p1.k, p2.k);
    }
});
    for(m=0;m<map.length;m++){
      for(n=0;n<map[0].length;n++){
        if(map[m][n]=='.'){
          continue;
        }
        else{
          int[][] position={{m,n}};
          double pi=Math.PI;
          double anta=0.5*pi-Math.atan2(n, m);
          double adjusted=anta<0?anta+2*pi:anta;
          double mooded=adjusted%(2*pi);
          float distance=Math.abs(m-i)+Math.abs(n-j);
          point p=new point(position,mooded,distance);
          queue.add(p);
        }
      }
    }
    double cur=queue.poll().k;
    for(int index=1;index<200;){
      for(point p:queue){
        if(p.position[0][0]==i+1){
          continue;
        }
        else{
          if(p.k>cur){
            m=p.position[0][0];
            n=p.position[0][1];
            p.position[0][0]=i+1;
            cur=k;
            i++;
          }

        }
      }
    }
    return m*100+n;

  }
}
class point{
  int[][] position;
  double k;
  float distance;
  public point(int[][] p, double atan, float dis){
    position=p;
    k=atan;
    distance=dis;
  }
}
