import java.io.*;
import java.util.*;

public class Main
{    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int H, W, N;
    static int[][] type; // -1: empty, 0: block, 1: dust, 2: lamp
    static int[][] energy;
    static List<int[]> lampPosList;
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        
        type = new int[H][W];
        energy = new int[H][W];
        
        for (int i = 0; i < H; i++) 
            Arrays.fill(type[i], -1);
        
        // 램프 위치
        lampPosList = new ArrayList<>();
        
        N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++)
        {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            
            if(str.equals("redstone_block"))
            {
                type[x][y] = 0;
                energy[x][y] = 15;
            }
            else if(str.equals("redstone_dust"))
                type[x][y] = 1;
            else
            {
                type[x][y] = 2;
                lampPosList.add(new int[]{x, y});
            }
        }

        boolean changed = true;
        
        while(changed)
        {
            changed = simulate();
            
            boolean allOn = true;
            
            for(int[] pos : lampPosList)
            {
                if(energy[pos[0]][pos[1]] <= 0)
                {
                    allOn = false;
                    break;
                }
            }
            
            if(allOn)
            {
                System.out.println("success");
                return;
            }
        }
        
        System.out.println("failed");
    }

    private static boolean simulate() 
    {
        int[][] newEnergy = new int[H][W];
        
        // 레드스톤 블록 15
        for(int i = 0; i < H; i++)
        {
            for(int j = 0; j < W; j++)
            {
                if(type[i][j] == 0)
                    newEnergy[i][j] = 15;
            }
        }

        for(int i = 0; i < H; i++)
        {
            for(int j = 0; j < W; j++)
            {
                if(type[i][j] == -1 || energy[i][j] == 0)
                    continue;
                
                // 블럭, 가루만
                if(type[i][j] == 0 || type[i][j] == 1)
                {
                    for(int k = 0; k < 4; k++)
                    {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if(nx >= 0 && ny >= 0 && nx < H && ny < W)
                        {
                            if(type[nx][ny] == -1)
                                continue;
                            
                            int transmitEnergy = 0;
                            
                            if(type[i][j] == 0)
                                transmitEnergy = 15;
                            else
                                transmitEnergy = energy[i][j] - 1;
                            
                            if(transmitEnergy > 0)
                                newEnergy[nx][ny] = Math.max(newEnergy[nx][ny], transmitEnergy);
                        }
                    }
                }
            }
        }
        
        boolean changed = false;
        
        for(int i = 0; i < H; i++)
        {
            for(int j = 0; j < W; j++)
            {
                if(energy[i][j] != newEnergy[i][j])
                {
                    changed = true;
                    break;
                }
            }
            
            if(changed) 
            	break;
        }
        
        energy = newEnergy;
        
        return changed;
    }
}
