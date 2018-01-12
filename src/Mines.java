import java.util.Random;

public class Mines {
    public int [][]mines;
    private int size,mines_number;
    public Mines(int x,int minesnumber){
        size=x;
        mines_number=minesnumber;
        mines= new int[size][size];
        for(int i=0; i<size; i++)
            for(int j=0; j<size; j++) mines[i][j]=0;
        PutMines();
        CountAllFields();
    }

    private void PutMines(){
        Random generator = new Random();
        int x, y;
        for(int i=0; i<mines_number; i++){
            x=generator.nextInt(size);
            y=generator.nextInt(size);
            if(CanPutMine(x,y))
                mines[x][y]=-1;
            else
                i--;
        }
    }
    private boolean CanPutMine(int x,int y){
        if(mines[x][y]==-1) return false;
        int temp=CountField(x,y);
        if(x>0&&y>0&&x<size&&y<size){
            if(temp==8)return false;
        }
        else if((x==0&&y==0)||(x==(size-1)&&y==(size-1))){
            if(temp==3) return false;
        }
        else if((x==0&&y!=0&&y!=(size-1))||(x==(size-1)&&y!=0&&y!=(size-1))||(x!=0&&x!=(size-1)&&y==0)||(x!=0&&x!=(size-1)&&y==(size-1))) {
            if(temp==5) return false;
        }
        return true;
    }
    private int CountField(int i, int j){
        if(mines[i][j]!=-1) {
            for(int x=i-1; x<=i+1; x++)
                    if(x>=0&&x<size)
                        for(int y=j-1; y<=j+1; y++)
                            if(y>=0&&y<size)
                                if(mines[x][y]==-1) {
                                    mines[i][j]++;
                                }
        }
        return mines[i][j];
    }
    private void CountAllFields(){
        for(int i=0; i<size; i++)
            for(int j=0; j<size; j++)
                CountField(i,j);
    }
}
